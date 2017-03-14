/*
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
 * http://www.gammanalytics.com/
 * --------------------------------------------------------------------------------------------
 * The software in this package is published under the terms of the EUL (End User license)
 * agreement a copy of which has been included with this distribution in the license.txt file.
 */

package com.gamma.dexter.console.draft; /**
 * Created by lenovo on 2/4/2016.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MapToSqlDb {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/snmp";
    static final String USER = "root";
    static final String PASS = "root";

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Connection conn = null;

    public MapToSqlDb() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println(" MaptoSqlDb connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        MapToSqlDb db = new MapToSqlDb();

        SnmpUtility client = new SnmpUtility("127.0.0.1", "public");
        Map<String, String> dataMap = client.snmpWalk(".");
        db.inputMapToDb("systemMap", dataMap);

    }

    public Map<String, String> deleteDevice(String ip) {
        Map<String, String> newDeviceMap = null;
        try {
            Statement stmt = conn.createStatement();

            String sql = "DELETE from devices where ip =  '" + ip + "' ";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            newDeviceMap = new HashMap<>();
            newDeviceMap = getAllDevices();
            stmt.close();
        } catch (Exception e) {
            System.out.println("error in deleting device with ip = " + ip + "....." + e);
        }

        return newDeviceMap;
    }

    public Map<String, String> getAllDevices() {
        Map<String, String> deviceMap = null;
        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT ip,community,mac_add FROM devices";
            ResultSet rs = stmt.executeQuery(sql);
            deviceMap = new LinkedHashMap<>();

            int counter = 0;
            while (rs.next()) {

                counter++;
                String dbIp = rs.getString("ip");
                String dbCommunity = rs.getString("community");
                String dbmac = rs.getString("mac_add");

                deviceMap.put("Ip" + counter, dbIp);
                deviceMap.put("Community" + counter, dbCommunity);
                deviceMap.put("mac" + counter, dbmac);
            }

            rs.close();
            stmt.close();

            if (counter == 0)
                return null;
        } catch (Exception e) {

            System.out.println("createStatementError in getAllDevices()" + e);
        }

        return deviceMap;
    }

    public Map<String, String> bulkDeviceAddition(String bulkDeviceInfo) {

        try {

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = dateFormat.format(date).toString();

            List<String> ipCommList = Arrays.asList(bulkDeviceInfo.split(","));
            System.out.println(ipCommList);

            Statement stmt = conn.createStatement();

            try {
                for (int pos = 0; pos < ipCommList.toArray().length; pos = pos + 2) {

                    String sysRow = "INSERT  INTO devices " +
                            "VALUES ('" + ipCommList.get(pos) + "','" + ipCommList.get(pos + 1) + "','" + time + "','"
                            + "NA" + "')";
                    System.out.println(sysRow);
                    stmt.executeUpdate(sysRow);
                }
                stmt.close();
            } catch (Exception e) {
                System.out.println("Inside InputDeviceMapToDb(String ip, String community) :-duplicateDevice " + e);
                return null;
            }

        } catch (Exception e) {

            System.out.println("createStatementError");
        }

        Map<String, String> getDevices = getAllDevices();
        return getDevices;
    }

    public Map<String, String> InputDeviceMapToDb(String ip, String community) {

        try {

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = dateFormat.format(date).toString();

            Statement stmt = conn.createStatement();

            try {
                String sysRow = "INSERT  INTO devices " +
                        "VALUES ('" + ip + "','" + community + "','" + time + "','" + "NA" + "')";
                System.out.println(sysRow);
                stmt.executeUpdate(sysRow);
                stmt.close();
            } catch (Exception e) {
                System.out.println("Inside InputDeviceMapToDb(String ip, String community) :-duplicateDevice");
                return null;
            }

        } catch (Exception e) {

            System.out.println("createStatementError");
        }

        Map<String, String> getDevices = getAllDevices();
        return getDevices;
    }

    public void inputMapToDb(String map, Map<String, String> snmpDataMap) {
        try {
            Statement stmt = conn.createStatement();
            SnmpDataClassifier classifier = new SnmpDataClassifier();

            Map<String, Map<String, String>> snmpInterfaceData = classifier.getInterfaceMap(snmpDataMap);

            String mac = null;

            for (String oid : snmpDataMap.keySet()) {
                if (oid.startsWith("1.3.6.1.2.1.55.1.5.1.8.")) {
                    mac = DateConvertHex.filterHex(snmpDataMap.get(oid));
                    if (mac.length() == 12) {
                        //   System.out.println("mac = " + mac + "\n" + "oid = "+ oid);
                        break;
                    }
                }
            }

            Map<String, String> snmpSystemData = classifier.getSystemMap(snmpDataMap);
            String ip = snmpSystemData.get("ipAdEntAddr");
//            System.out.println("ip = "+ip);


            if (map.equalsIgnoreCase("processMap")) {
                String time = getCuttentTime();

                Map<String, Map<String, String>> snmpProcessData = classifier.getRunningProcessMap(snmpDataMap);
                for (String processId : snmpProcessData.keySet()) {

                    Map<String, String> processMap = snmpProcessData.get(processId);

                    String name = processMap.get("name");
                    String runPath = processMap.get("runPath").replace("\\", "/");
                    String runParameters = processMap.get("runParameters");
                    String runType = processMap.get("runType");
                    String runStatus = processMap.get("runStatus");
                    String cpuPerformance = processMap.get("cpuPerformance");
                    String memory = processMap.get("memory");

                    String processRow = "INSERT INTO process_map " +
                            "VALUES ('" + ip + "','" + mac + "','" + time + "','" + processId + "','" + name + "','" + runPath +
                            "','" + runParameters + "','" + runType + "','" + runStatus + "','" + cpuPerformance +
                            "','" + memory + "')";
                    stmt.executeUpdate(processRow);

                }
            } else if (map.equalsIgnoreCase("storageMap")) {
                String time = getCuttentTime();

                Map<String, Map<String, String>> snmpStorageData = classifier.getStorageMap(snmpDataMap);
                for (String storageId : snmpStorageData.keySet()) {

                    Map<String, String> storageMap = snmpStorageData.get(storageId);

                    String type = storageMap.get("type");
                    String description = storageMap.get("description").replace("\\", "/");
                    String allocation_units = storageMap.get("allocation units");
                    String size = storageMap.get("size");
                    String used = storageMap.get("used");
                    String free = storageMap.get("free");


                    String storageRow = "INSERT INTO storage_map " +
                            "VALUES ('" + ip + "','" + mac + "','" + time + "','" + storageId + "','" + type + "','" + description + "','" +
                            allocation_units + "','" + size + "','" + used + "','" + free + "')";
                    stmt.executeUpdate(storageRow);

                }
            } else if (map.equalsIgnoreCase("SWMap")) {
                String time = getCuttentTime();
                Map<String, Map<String, String>> snmpSWData = classifier.getSWInstalledMap(snmpDataMap);
                for (String swId : snmpSWData.keySet()) {

                    Map<String, String> swMap = snmpSWData.get(swId);

                    String swInstalledName = swMap.get("swInstalledName");
                    String swInstalledDate = swMap.get("swInstalledDate");

                    String swRow = "INSERT INTO SW_map " +
                            "VALUES ('" + ip + "','" + mac + "','" + time + "','" + swId + "','" + swInstalledName + "','" + swInstalledDate + "')";
                    stmt.executeUpdate(swRow);

                }

            } else if (map.equalsIgnoreCase("deviceMap")) {
                String time = getCuttentTime();

                Map<String, Map<String, String>> snmpDeviceData = classifier.getDeviceMap(snmpDataMap);
                for (String deviceId : snmpDeviceData.keySet()) {

                    Map<String, String> deviceMap = snmpDeviceData.get(deviceId);

                    String hrDeviceDescr = deviceMap.get("hrDeviceDescr").replace("\\", "/");
                    String hrDeviceStatus = deviceMap.get("hrDeviceStatus");
                    String hrDeviceErrors = deviceMap.get("hrDeviceErrors");

                    String deviceRow = "INSERT INTO device_map " +
                            "VALUES ('" + ip + "','" + mac + "','" + time + "','" + deviceId + "','" + hrDeviceDescr + "','" +
                            hrDeviceStatus + "','" + hrDeviceErrors + "')";
                    stmt.executeUpdate(deviceRow);

                }

            } else if (map.equalsIgnoreCase("interfaceMap")) {
                String time = getCuttentTime();

                for (String interfaceId : snmpInterfaceData.keySet()) {

                    Map<String, String> interfaceMap = snmpInterfaceData.get(interfaceId);

                    String ifDescr = interfaceMap.get("ifDescr").replace("\\", "/");
                    String ifType = interfaceMap.get("ifType");
                    String ifMtu = interfaceMap.get("ifMtu");
                    String ifSpeed = interfaceMap.get("ifSpeed");
                    String ifPhysAddress = interfaceMap.get("ifPhysAddress");
                    String ifAdminStatus = interfaceMap.get("ifAdminStatus");
                    String ifOperStatus = interfaceMap.get("ifOperStatus");
                    String ifLastChange = interfaceMap.get("ifLastChange");
                    Double ifInOctets = Double.parseDouble(interfaceMap.get("ifInOctets"));
                    Double ifOutOctets = Double.parseDouble(interfaceMap.get("ifOutOctets"));


                    String interfaceRow = "INSERT INTO interface_map " +
                            "VALUES ('" + ip + "','" + mac + "','" + time + "','" + interfaceId + "','" + ifDescr + "','" +
                            ifType + "','" + ifMtu + "','" + ifSpeed + "','" + ifPhysAddress + "','" + ifAdminStatus +
                            "','" + ifOperStatus + "','" + ifLastChange + "','" + ifInOctets + "','" + ifOutOctets + "')";
                    stmt.executeUpdate(interfaceRow);

                }

            } else if (map.equalsIgnoreCase("systemMap")) {

                String time = getCuttentTime();

                String ipOutRequests = snmpSystemData.get("ipOutRequests");
                String hrSystemNumUsers = snmpSystemData.get("hrSystemNumUsers");
                String ipDefaultTTL = snmpSystemData.get("ipDefaultTTL");
                String ipAdEntIfIndex = snmpSystemData.get("ipAdEntIfIndex");
                String hrMemorySize = snmpSystemData.get("hrMemorySize");
                int hrSWInstalledLastChange = Integer.parseInt(snmpSystemData.get("hrSWInstalledLastChange"));
                String ipAdEntNetMask = snmpSystemData.get("ipAdEntNetMask");
                int hrSystemUptime = Integer.parseInt(snmpSystemData.get("hrSystemUptime"));
                String sysName = snmpSystemData.get("sysName");
                String sysServices = snmpSystemData.get("sysServices");
                String ifNumber = snmpSystemData.get("ifNumber");
                int sysUpTimeInstance = Integer.parseInt(snmpSystemData.get("sysUpTimeInstance"));
                String sysContact = snmpSystemData.get("sysContact");
                String ipOutDiscards = snmpSystemData.get("ipOutDiscards");
                String sysDescr = snmpSystemData.get("sysDescr").replace("\\", "/");
                String hrSystemProcesses = snmpSystemData.get("hrSystemProcesses");
                String ipForwarding = snmpSystemData.get("ipForwarding");
                String ipOutNoRoutes = snmpSystemData.get("ipOutNoRoutes");
                String sysObjectID = snmpSystemData.get("sysObjectID");
                String sysLocation = snmpSystemData.get("sysLocation");
                String ipInDiscards = snmpSystemData.get("ipInDiscards");
                String ipAdEntAddr = snmpSystemData.get("ipAdEntAddr");
                String hrPrinterStatus = snmpSystemData.get("hrPrinterStatus");
                String ipInDelivers = snmpSystemData.get("ipInDelivers");
                String ipInReceives = snmpSystemData.get("ipInReceives");

                String systemRow = "INSERT INTO system_map " +
                        "VALUES ('" + ip + "','" + mac + "','" + time + "','" + ipOutRequests + "','" + hrSystemNumUsers + "','" +
                        ipDefaultTTL + "','" + ipAdEntIfIndex + "','" + hrMemorySize + "','" + hrSWInstalledLastChange + "','" + ipAdEntNetMask +
                        "','" + hrSystemUptime + "','" + sysName + "','" + sysServices + "','" + ifNumber +
                        "','" + sysUpTimeInstance + "','" + sysContact + "','" + ipOutDiscards + "','" + sysDescr +
                        "','" + hrSystemProcesses + "','" + ipForwarding + "','" + ipOutNoRoutes + "','" + sysObjectID +
                        "','" + sysLocation + "','" + ipInDiscards + "','" + ipAdEntAddr + "','" + hrPrinterStatus +
                        "','" + ipInDelivers + "','" + ipInReceives + "')";
                stmt.executeUpdate(systemRow);

                String devRow = "UPDATE devices SET mac_add =" + "'" + mac + "'" + " WHERE ip =" + "'" + ip + "'";
                ;
//                System.out.println(devRow);
                stmt.executeUpdate(devRow);
            }

            if (map.equalsIgnoreCase("systemMap")) {
                conn.close();
                System.out.println("conn closed");
                stmt.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCuttentTime() {
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

}
