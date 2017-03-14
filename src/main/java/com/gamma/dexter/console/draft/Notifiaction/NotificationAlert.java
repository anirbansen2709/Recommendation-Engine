package com.gamma.dexter.console.draft.Notifiaction;

import com.gamma.dexter.console.draft.DateConvertHex;
import com.gamma.dexter.console.draft.SnmpDataClassifier;
import com.gamma.dexter.console.draft.SnmpUtility;
import com.gamma.dexter.console.draft.Tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lenovo on 4/21/2016.
 */
public class NotificationAlert {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/snmp";
    static final String USER = "root";
    static final String PASS = "root";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Map<String, List<String>> pcDetected = new HashMap<>();
    private Map<String, String> dataMap;
    private String mac;
    private Connection conn = null;

    public NotificationAlert() {
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println(" NotificationAlert connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Map<String, List<String>> getPcDetectedList(Map<String, String> dataMap, NotificationPojo rule) {

        this.dataMap = dataMap;

        for (String oid : dataMap.keySet()) {
            if (oid.startsWith("1.3.6.1.2.1.55.1.5.1.8.")) {
                mac = DateConvertHex.filterHex(dataMap.get(oid));
                if (mac.length() == 12) {
                    //    System.out.println("mac = " + mac + "\n" + "oid = "+ oid);
                    break;
                }
            }
        }

        notificationAlertChecker(rule);

        return pcDetected;
    }

    public void notificationAlertChecker(NotificationPojo rule) {

        if (rule.isDownloadDetectionEnabled()==1) {
            performDownloadDetection(rule.getDownloadDataLimit());
        }
        if (rule.isFreeSpaceDetectionEnabled()==1) {
            performFreeSpaceDetection(rule.getFreeSpaceLimit());
        }
        if (rule.isPendriveDetectionEnabled()==1) {
            performPendriveDetection();
        }
        if (rule.isIpChangeDetectionEnabled()==1) {
            performIpChangeDetection();
        }
        if (rule.isProcessDetectionEnabled()==1) {
            performProcessDetection(rule.getProcessName());
        }
        if (rule.isRamDetectionEnabled()==1) {
            performRamDetection(rule.getRamLimit());
        }
        if (rule.isUploadDetectionEnabled()==1) {
            performUploadDetection(rule.getUploadDataLimit());
        }
        if (rule.isUptimeDetectionEnabled()==1) {
            performUptimeDetection(rule.getUptimeLimit());
        }
    }

    public void performDownloadDetection(double downloadLimit) {
        Date currentDate = new Date();
        long daysToseconds = 7 * 24 * 3600 * 1000l;
        String endDate = dateFormat.format(currentDate);
        String startDate = dateFormat.format(currentDate.getTime() - daysToseconds);
        System.out.println(startDate);
        List<String> macList;

        Map<String, List<String>> downloadResults = new Tests().downloadTest(startDate, endDate, downloadLimit);

        for (String dbMac : downloadResults.keySet()) {

            if (!pcDetected.containsKey("downloadDetection")) {
                macList = new ArrayList<>();
                pcDetected.put("downloadDetection", macList);
            } else {
                macList = pcDetected.get("downloadDetection");
            }
            if (!macList.contains(dbMac))
                macList.add(dbMac);
        }

    }

    public void performUploadDetection(double uploadLimit) {
        Date currentDate = new Date();
        long daysToseconds = 7 * 24 * 3600 * 1000l;
        String endDate = dateFormat.format(currentDate);
        String startDate = dateFormat.format(currentDate.getTime() - daysToseconds);
        System.out.println(startDate);
        List<String> macList;

        Map<String, List<String>> uploadResults = new Tests().upLoadTest(startDate, endDate, uploadLimit);

        for (String dbMac : uploadResults.keySet()) {

            if (!pcDetected.containsKey("uploadDetection")) {
                macList = new ArrayList<>();
                pcDetected.put("uploadDetection", macList);
            } else {
                macList = pcDetected.get("uploadDetection");
            }
            if (!macList.contains(dbMac))
                macList.add(dbMac);
        }


    }

    public void performUptimeDetection(double upTimeLimit) {

        Map<String, String> snmpSystemData = new SnmpDataClassifier().getSystemMap(dataMap);
        List<String> macList;

        if (Integer.parseInt(snmpSystemData.get("sysUpTimeInstance")) > upTimeLimit) {
            if (!pcDetected.containsKey("upDetection")) {
                macList = new ArrayList<>();
                pcDetected.put("uptimeDetection", macList);
            } else {
                macList = pcDetected.get("uptimeDetection");
            }
            if (!macList.contains(mac))
                macList.add(mac);
        }


    }

    public void performFreeSpaceDetection(double freeSpaceLimit) {

        Map<String, Map<String, String>> snmpStorageData = new SnmpDataClassifier().getStorageMap(dataMap);

        long sum = 0;

        for (String key : snmpStorageData.keySet()) {

            if (snmpStorageData.get(key).get("type").equals("4")) {

                sum += Long.parseLong(snmpStorageData.get(key).get("free"));

                if (sum < freeSpaceLimit) {

                    List<String> macList;
                    if (!pcDetected.containsKey("freeSpaceDetection")) {
                        macList = new ArrayList<>();
                        pcDetected.put("freeSpaceDetection", macList);
                    } else {
                        macList = pcDetected.get("freeSpaceDetection");
                    }

                    if (!macList.contains(mac))
                        macList.add(mac);

                    System.out.println("@freeSpaceDetection " + sum + " ---------------> " + freeSpaceLimit);
                }
            }

        }

    }

    public void performPendriveDetection() {

        Map<String, Map<String, String>> snmpStorageData = new SnmpDataClassifier().getStorageMap(dataMap);
        List<String> macList;

        for (String key : snmpStorageData.keySet()) {
            if (snmpStorageData.get(key).get("type").equals("5")) {

                if (!pcDetected.containsKey("pendriveDetection")) {
                    macList = new ArrayList<>();
                    pcDetected.put("pendriveDetection", macList);
                } else {
                    macList = pcDetected.get("pendriveDetection");
                }
                if (!macList.contains(mac))
                    macList.add(mac);
            }
        }

    }

    public void performIpChangeDetection() {

        Map<String, String> systemData = new SnmpDataClassifier().getSystemMap(dataMap);
        String currentIp = systemData.get("ipAdEntAddr");
        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT distinct ip,mac_add from system_map ORDER BY time";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            List<String> macList;

            while (rs.next()) {

                String dbIp = rs.getString("ip");
                String dbMac = rs.getString("mac_add");
                if (dbMac.equals(mac)) {
                    if (dbIp.equals(currentIp)) {
                        continue;
                    } else {
                        if (!pcDetected.containsKey("ipChangeDetection")) {
                            macList = new ArrayList<>();
                            pcDetected.put("ipChangeDetection", macList);
                        } else {
                            macList = pcDetected.get("ipChangeDetection");
                        }
                        if (!macList.contains(mac)) {
                            macList.add(mac);
                        }
                    }

                }
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@performIpChangeDetection test createStatementError" + e);
        }

    }

    public void performProcessDetection(String processName) {

        Map<String, Map<String, String>> snmpProcessData = new SnmpDataClassifier().getRunningProcessMap(dataMap);
        List<String> macList;
        List<String> processList = Arrays.asList(processName.split(","));

        boolean flag = false;

        for (String process : processList) {
            if(flag){
                break;
            }
            for (String key : snmpProcessData.keySet()) {
                if (snmpProcessData.get(key).get("name").equals(process)) {
                    if (!pcDetected.containsKey("processDetection")) {
                        macList = new ArrayList<>();
                        pcDetected.put("processDetection", macList);
                    } else {
                        macList = pcDetected.get("processDetection");
                    }
                    if (!macList.contains(mac)) {
                        macList.add(mac);
                        flag = true;
                        break;
                    }
                }
            }
        }
    }

    public void performRamDetection(int ramUsageLimit) {

        Map<String, Map<String, String>> snmpStorageData = new SnmpDataClassifier().getStorageMap(dataMap);

        double ramUtilization = 0;
        double freeRam = 0;
        double totRam = 0;

        for (String key : snmpStorageData.keySet()) {

            if (snmpStorageData.get(key).get("type").equals("2")) {

                freeRam = Long.parseLong(snmpStorageData.get(key).get("free"));
                totRam = Long.parseLong(snmpStorageData.get(key).get("size"));
                ramUtilization = (freeRam * 100 / totRam);

                if (ramUtilization > ramUsageLimit) {
                    List<String> macList;
                    if (!pcDetected.containsKey("ramUsageDetection")) {
                        macList = new ArrayList<>();
                        pcDetected.put("ramUsageDetection", macList);
                    } else {
                        macList = pcDetected.get("ramUsageDetection");
                    }

                    if (!macList.contains(mac))
                        macList.add(mac);

                    System.out.println("@ramUsageDetection" + ramUsageLimit + " ---------------> " + ramUsageLimit);

                }
            }

        }
    }

    public static void main(String[] args) throws Exception {

        SnmpUtility client = new SnmpUtility("127.0.0.1", "public");
        Map<String, String> snmpDataMap = client.snmpWalk(".");

        Map<String, List<String>> pcDetected = new HashMap<>();
//        NotificationAlert test = new NotificationAlert(snmpDataMap, pcDetected);
//        test.performUptimeDetection(46550);
//        System.out.println(test.pcDetected);
    }

}
