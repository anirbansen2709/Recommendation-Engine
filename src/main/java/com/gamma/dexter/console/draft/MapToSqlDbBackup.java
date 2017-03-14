/*
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
 * http://www.gammanalytics.com/
 * --------------------------------------------------------------------------------------------
 * The software in this package is published under the terms of the EUL (End User license)
 * agreement a copy of which has been included with this distribution in the license.txt file.
 */

package com.gamma.dexter.console.draft;/*
package com.college.snmp; */
/**
 * Created by lenovo on 2/4/2016.
 *//*


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MapToSqlDbBackup {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/snmp";
    static final String USER = "root";
    static final String PASS = "1234";

    private Connection conn = null;

    public MapToSqlDbBackup() {
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        MapToSqlDbBackup db = new MapToSqlDbBackup();
        //     db.inputMapToDb("systemMap");
        //     db.inputMapToDb("deviceMap");
        //     db.inputMapToDb("SWMap");
        //     db.inputMapToDb("interfaceMap");
        //     db.inputMapToDb("127.0.0.1", "public","storageMap");


    //    Map<String, String> deviceMap =  db.InputDeviceMapToDb("127.0.0.1", "public");
//        if(deviceMap == null)
////             System.out.println(getDevices+"received");
//          System.out.println(deviceMap +" in main");

//        Map<String, String> getDevices =  db.getAllDevices();
//        if(getDevices == null)
//             System.out.println(getDevices+"in main");

        Map<String, String> getDevices =  db.deleteDevice("127.0.0.19");
            System.out.println(getDevices+"in main");
    }

    public Map<String,String> deleteDevice(String ip) {
        Map<String,String> newDeviceMap = null;
        try {
            Statement stmt = conn.createStatement();

            String sql = "DELETE from devices where ip =  '"+ip+"' ";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            newDeviceMap = new HashMap<>();
            newDeviceMap = getAllDevices();
            stmt.close();
        } catch (Exception e) {
            System.out.println("error in deleting device with ip = "+ip+"....."+ e);
        }

        return newDeviceMap;
    }

    public Map<String,String> getAllDevices() {
        Map<String,String> deviceMap = null;
        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT ip,community FROM devices";
            ResultSet rs = stmt.executeQuery(sql);
            deviceMap = new HashMap<>();

            int counter = 0;
            while(rs.next()){

                counter++;
                String dbIp  = rs.getString("ip");
                String dbCommunity = rs.getString("community");

                deviceMap.put("Ip" + counter, dbIp);
                deviceMap.put("Community" + counter, dbCommunity);
            }

            rs.close();
            stmt.close();

            if(counter == 0)
                return null;
        } catch (Exception e) {

            System.out.println("createStatementError in getAllDevices()"+ e);
        }

        return deviceMap;
    }

    public Map<String,String> InputDeviceMapToDb(String ip, String community) {

        try {

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = dateFormat.format(date).toString();

            Statement stmt = conn.createStatement();

            try {
                String sysRow = "INSERT  INTO devices " +
                        "VALUES ('" + ip + "','" + community + "','"  + time + "')";
                System.out.println(sysRow);
                stmt.executeUpdate(sysRow);
                stmt.close();
            }
            catch (Exception e)
            {
                System.out.println("Inside InputDeviceMapToDb(String ip, String community) :-duplicateDevice");
                return null;
            }

        } catch (Exception e) {

            System.out.println("createStatementError");
        }

        Map<String,String> getDevices = getAllDevices();
            return getDevices;
    }

    public String inputMapToDb(String ip, String community, String map) {

        String status = null;
        try {
            Statement stmt = conn.createStatement();

            SnmpUtility client = new SnmpUtility(ip, community);
            Map<String, String> snmpDataMap = client.snmpWalk(".");
            SnmpDataClassifier classifier = new SnmpDataClassifier();

            Map<String, Map<String, String>> snmpInterfaceData = classifier.getInterfaceMap(snmpDataMap);
            Map<String, String> ifMap = snmpInterfaceData.get("11");
            String mac = DateConvertHex.filterHex(ifMap.get("ifPhysAddress"));

            System.out.println(mac);

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(dateFormat.format(date));
            String time = dateFormat.format(date).toString();

            try {
                String sysRow = "INSERT INTO devices " +
                        "VALUES ('" + ip + "','" + community + "','" + mac + "','" + time + "')";
                stmt.executeUpdate(sysRow);
            } catch (Exception e) {
                status = "DuplicateDevice";
            }

            if (map.equalsIgnoreCase("processMap")) {

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

                Map<String, Map<String, String>> snmpStorageData = classifier.getStorageMap(snmpDataMap);
                for (String storageId : snmpStorageData.keySet()) {

                    Map<String, String> storageMap = snmpStorageData.get(storageId);

                    String type = storageMap.get("type");
                    String description = storageMap.get("description").replace("\\", "/");
                    String allocation_units = storageMap.get("allocation units");
                    String size = storageMap.get("size");
                    String used = storageMap.get("used");

                    String storageRow = "INSERT INTO storage_map " +
                            "VALUES ('" + ip + "','" + mac + "','" + time + "','" + storageId + "','" + type + "','" + description + "','" +
                            allocation_units + "','" + size + "','" + used + "')";
                    stmt.executeUpdate(storageRow);

                }
            } else if (map.equalsIgnoreCase("SWMap")) {

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

                Map<String, Map<String, String>> snmpDeviceData = classifier.getDeviceMap(snmpDataMap);
                for (String deviceId : snmpDeviceData.keySet()) {

                    Map<String, String> deviceMap = snmpDeviceData.get(deviceId);

                    String hrDeviceDescr = deviceMap.get("hrDeviceDescr").replace("\\", "/");
                    String hrDeviceStatus = deviceMap.get("hrDeviceStatus");
                    String hrDeviceErrors = deviceMap.get("hrDeviceErrors");

                    String deviceRow = "INSERT INTO device_map " +
                            "VALUES ('" + ip + "','" + mac + "','" + time + "','" + deviceId + "','" + hrDeviceDescr + "','" +
                            hrDeviceStatus + "','" + hrDeviceErrors + "')";
                    System.out.println(deviceRow);
                    stmt.executeUpdate(deviceRow);

                }

            } else if (map.equalsIgnoreCase("interfaceMap")) {

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
                    String ifInOctets = interfaceMap.get("ifInOctets");
                    String ifOutOctets = interfaceMap.get("ifOutOctets");


                    String interfaceRow = "INSERT INTO interface_map " +
                            "VALUES ('" + ip + "','" + mac + "','" + time + "','" + interfaceId + "','" + ifDescr + "','" +
                            ifType + "','" + ifMtu + "','" + ifSpeed + "','" + ifPhysAddress + "','" + ifAdminStatus +
                            "','" + ifOperStatus + "','" + ifLastChange + "','" + ifInOctets + "','" + ifOutOctets + "')";
                    System.out.println(interfaceRow);
                    stmt.executeUpdate(interfaceRow);

                }

            } else if (map.equalsIgnoreCase("systemMap")) {

                Map<String, String> snmpSystemData = classifier.getSystemMap(snmpDataMap);

                String ipOutRequests = snmpSystemData.get("ipOutRequests");
                String hrSystemNumUsers = snmpSystemData.get("hrSystemNumUsers");
                String ipDefaultTTL = snmpSystemData.get("ipDefaultTTL");
                String ipAdEntIfIndex = snmpSystemData.get("ipAdEntIfIndex");
                String hrMemorySize = snmpSystemData.get("hrMemorySize");
                String hrSWInstalledLastChange = snmpSystemData.get("hrSWInstalledLastChange");
                String ipAdEntNetMask = snmpSystemData.get("ipAdEntNetMask");
                String hrSystemUptime = snmpSystemData.get("hrSystemUptime");
                String sysName = snmpSystemData.get("sysName");
                String sysServices = snmpSystemData.get("sysServices");
                String ifNumber = snmpSystemData.get("ifNumber");
                String sysUpTimeInstance = snmpSystemData.get("sysUpTimeInstance");
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
                System.out.println(systemRow);
                stmt.executeUpdate(systemRow);


            }

            conn.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }




}
*/
