/*
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
 * http://www.gammanalytics.com/
 * --------------------------------------------------------------------------------------------
 * The software in this package is published under the terms of the EUL (End User license)
 * agreement a copy of which has been included with this distribution in the license.txt file.
 */

package com.gamma.dexter.console.draft;

import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2/27/2016.
 */
public class Tests {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/snmp";
    static final String USER = "root";
    static final String PASS = "root";

    private Connection conn = null;

    public Tests() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println(" Tests connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Tests getResults = new Tests();
        Map<String, List<String>> softwareTestResult = getResults.softwareUpdateTest("2016-02-26 03:15:28", "2016-03-26 03:17:22");
        Map<String, List<String>> driveTestResult = getResults.driveSpaceTest("2016-02-26 03:15:28", "2016-03-26 03:17:22", 10261803008.0);
        Map<String, List<String>> ramTestResults = getResults.RAMTest(40707031.0);

        Map<String, List<String>> processTestResults = getResults.processTest("2016-02-26 03:15:28", "2016-03-26 03:17:22", "chrome.exe");
        JSONObject json = new JSONObject();
        json.putAll(processTestResults);

        Map<String, List<String>> sysUpTimeTestResults = getResults.sysUpTimeTest("2016-02-26 03:15:28", "2016-03-26 03:17:22", 73248);

        Map<String, List<String>> pendriveTestResults = getResults.pendriveTest("2016-03-10 00:00:00", "2016-03-10 23:00:00");

        Map<String, List<String>> ramPerformanceTestResults = getResults.ramPerformance("2016-01-10 00:00:00", "2016-5-10 23:00:00");

        Map<String, List<String>> printerTestResults = getResults.printerTest("2016-02-26 03:15:28", "2016-02-26 03:17:22");

        Map<String, List<String>> downloadResults = getResults.downloadTest("2016-01-15 14:10:24", "2016-04-15 16:10:24", 0);

        Map<String, List<String>> uploadResults = getResults.upLoadTest("2016-01-15 14:10:24", "2016-04-15 16:10:24", 0);

        Map<String, List<String>> duplicateSystemTestResults = getResults.duplicateSystemTest("2016-02-26 03:15:28", "2016-03-26 03:17:22");

        System.out.println(getResults.addSystemDetailsToTestResults(downloadResults));
    }

    public Map<String, List<String>> driveSpaceTest(String startTime, String endTime, double freeSize) {

        Map<String, List<String>> largerDriveSpaceDetected = new LinkedHashMap<>();
        List<String> drives;

        try {
            Statement stmt = conn.createStatement();

            String sql = " select DISTINCT mac_add,description from storage_map where type=4 and free > " + freeSize +
                    " and time BETWEEN" + "'" + startTime + "'" + "AND" + "'" + endTime + "'" + " ORDER BY time";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

//                String dbIp = rs.getString("ip");
                String dbMac = rs.getString("mac_add");
                String desc = rs.getString("description");
                desc = desc.substring(0, 1);

                if (!largerDriveSpaceDetected.containsKey(dbMac)) {
                    drives = new ArrayList<>();
                    largerDriveSpaceDetected.put(dbMac, drives);
                } else {
                    drives = largerDriveSpaceDetected.get(dbMac);
                }
                drives.add(desc);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@driveSpaceTest test createStatementError" + e);
        }

//        System.out.println(largerDriveSpaceDetected);
        return largerDriveSpaceDetected;
    }

    public Map<String, List<String>> upLoadTest(String startTime, String endTime, double limit) {

        Map<String, List<String>> uploadDetected = new LinkedHashMap<>();
        List<String> uploadDetails;
        try {

            Statement stmt = conn.createStatement();
            String sql = "SELECT mac_add,if_out_octets  from interface_map where  if_type='71' and time BETWEEN" +
                    "'" + startTime + "'" + "AND" + "'" + endTime + "'" + " and if_in_octets>0 order by mac_add,time desc";

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {

                String dbMac1 = rs.getString("mac_add");
                Double octets1 = rs.getDouble("if_out_octets");

                Double sum = 0.0;

                while (rs.next()) {

                    String dbMac2 = rs.getString("mac_add");
                    Double octets2 = rs.getDouble("if_out_octets");


                    if (dbMac1.equals(dbMac2)) {

                        if (octets1 >= octets2) {

                            sum += (octets1 - octets2);
//                                System.out.println(sum);
                                octets1 = octets2;
                        } else {

                            sum += octets1;
                            octets1 = octets2;
                        }
                    } else {

                        if (sum > limit) {
                            if (!uploadDetected.containsKey(dbMac1)) {
                                uploadDetails = new ArrayList<>();
                                uploadDetected.put(dbMac1, uploadDetails);
                            } else {
                                uploadDetails = uploadDetected.get(dbMac1);
                            }
                            uploadDetails.add(sum.toString());

                        }
                        dbMac1 = dbMac2;
                        sum = 0.0;
                    }
                }
                if (sum > limit) {
                    if (!uploadDetected.containsKey(dbMac1)) {
                        uploadDetails = new ArrayList<>();
                        uploadDetected.put(dbMac1, uploadDetails);
                    } else {
                        uploadDetails = uploadDetected.get(dbMac1);
                    }
                    System.out.println(dbMac1 +"@uploadTest, sum = "+sum +" ,upLoad Limit = "+ limit + "  " + startTime + "-->" +endTime);
                    uploadDetails.add(sum.toString());
                }

            }

            stmt.close();
            rs.close();

        } catch (Exception e) {
            System.out.println("@upload test createStatementError" + e);
        }
        return uploadDetected;
    }

    public Map<String, List<String>> downloadTest(String startTime, String endTime, double limit) {

        Map<String, List<String>> downloadDetected = new LinkedHashMap<>();
        List<String> downloadDetails;
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT mac_add,if_in_octets  from interface_map where  if_type='71' and time BETWEEN" +
                    "'" + startTime + "'" + "AND" + "'" + endTime + "'" + " and if_in_octets>0 order by mac_add,time desc";

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {

                String dbMac1 = rs.getString("mac_add");
                Double octets1 = rs.getDouble("if_in_octets");

                Double sum = 0.0;

                while (rs.next()) {

                    String dbMac2 = rs.getString("mac_add");
                    Double octets2 = rs.getDouble("if_in_octets");


                    if (dbMac1.equals(dbMac2)) {

                        if (octets1 >= octets2) {

                            sum += (octets1 - octets2);
//                                System.out.println(sum);
                            octets1 = octets2;
                        } else {

                            sum += octets1;
                            octets1 = octets2;
                        }
                    } else {

                        if (sum > limit) {
                            if (!downloadDetected.containsKey(dbMac1)) {
                                downloadDetails = new ArrayList<>();
                                downloadDetected.put(dbMac1, downloadDetails);
                            } else {
                                downloadDetails = downloadDetected.get(dbMac1);
                            }
                            downloadDetails.add(sum.toString());

                        }
                        dbMac1 = dbMac2;
                        sum = 0.0;
                    }
                }
                if (sum > limit) {
                    if (!downloadDetected.containsKey(dbMac1)) {
                        downloadDetails = new ArrayList<>();
                        downloadDetected.put(dbMac1, downloadDetails);
                    } else {
                        downloadDetails = downloadDetected.get(dbMac1);
                    }
                    downloadDetails.add(sum.toString());
                    System.out.println(dbMac1 +"@downloadTest, sum = "+sum +" ,downLoad Limit = "+ limit + startTime + "-->" +endTime);
                }

            }

            stmt.close();
            rs.close();

        } catch (Exception e) {
            System.out.println("@download test createStatementError" + e);
        }

//        System.out.println(downloadDetected);
        return downloadDetected;
    }

    public Map<String, List<String>> softwareUpdateTest(String startTime, String endTime) {

        Map<String, List<String>> softwareUpdateDetected = new LinkedHashMap<>();
        List<String> macDetails;

        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT distinct mac_add from system_map where (sys_up_time_instance - hr_sw_installed_last_change)>30" +
                    " and time BETWEEN" + "'" + startTime + "'" + "AND" + "'" + endTime + "'" + " ORDER BY time";


//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

//                String dbIp = rs.getString("ip");
                String dbMac = rs.getString("mac_add");

                if (!softwareUpdateDetected.containsKey(dbMac)) {
                    macDetails = new ArrayList<>();
                    softwareUpdateDetected.put(dbMac, macDetails);
                }
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@softwareUpdateTest test createStatementError" + e);
        }

//          System.out.println(softwareUpdateDetected);
        return softwareUpdateDetected;
    }

    public Map<String, List<String>> RAMTest(double limit) {

        Map<String, List<String>> largerRamDetected = new LinkedHashMap<>();
        List<String> macDetails;

        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT distinct mac_add,size from storage_map where type =2  and size>" +
                    limit + " ORDER BY time";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String dbSize = rs.getString("size");
                String dbMac = rs.getString("mac_add");
                if (!largerRamDetected.containsKey(dbMac)) {
                    macDetails = new ArrayList<>();
                    largerRamDetected.put(dbMac, macDetails);
                } else {
                    macDetails = largerRamDetected.get(dbMac);
                }
                macDetails.add(dbSize);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {

            System.out.println("@largerRamDetected test createStatementError" + e);
        }

//        System.out.println(largerRamDetected);
        return largerRamDetected;
    }

    public Map<String, List<String>> sysUpTimeTest(String startTime, String endTime, double limit) {

        Map<String, List<String>> systemUptimeDeteced = new LinkedHashMap<>();
        List<String> macDetails;
        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT distinct mac_add from system_map where sys_up_time_instance > " + "'" + limit + "'" +
                    " and time BETWEEN" + "'" + startTime + "'" + "AND" + "'" + endTime + "'" + " ORDER BY time";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

//                String dbIp = rs.getString("ip");
                String dbMac = rs.getString("mac_add");
                if (!systemUptimeDeteced.containsKey(dbMac)) {
                    macDetails = new ArrayList<>();
                    systemUptimeDeteced.put(dbMac, macDetails);
                }
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {

            System.out.println("@systemDetected test createStatementError" + e);
        }

//            System.out.println(systemUptimeDeteced);
        return systemUptimeDeteced;

    }

    public Map<String, List<String>> processTest(String startTime, String endTime, String processName) {

        Map<String, List<String>> processDetected = new LinkedHashMap<>();

        List<String> timeOfDetection = null;
        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT distinct mac_add,time from process_map where name =" + "'" + processName + "'" + " and time BETWEEN" +
                    "'" + startTime + "'" + "AND" + "'" + endTime + "'" + " ORDER BY time";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

//                String dbIp = rs.getString("ip");
                String dbMac = rs.getString("mac_add");
                String dbTime = rs.getString("time");


                if (!processDetected.containsKey(dbMac)) {
                    timeOfDetection = new ArrayList<>();
                    processDetected.put(dbMac, timeOfDetection);
                } else {
                    timeOfDetection = processDetected.get(dbMac);
                }
                timeOfDetection.add(dbTime);
            }
        } catch (Exception e) {

            System.out.println("@processDetected test createStatementError" + e);
        }

        //      System.out.println(processDetected);
        return processDetected;
    }

    public Map<String, List<String>> pendriveTest(String startTime, String endTime) {

        Map<String, List<String>> pendriveDetected = new LinkedHashMap<>();
        List<String> timeOfDetection = null;
        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT distinct mac_add,time,description from storage_map where type = 5 and time BETWEEN" +
                    "'" + startTime + "'" + "AND" + "'" + endTime + "'" + " ORDER BY time";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String dbMac = rs.getString("mac_add");
                String dbTime = rs.getString("time");
                String description = rs.getString("description");
                description = description.substring(description.lastIndexOf(":") + 1);//,description.indexOf("S")-2

                if(pendriveDetected.containsKey(dbMac)){
                    timeOfDetection = pendriveDetected.get(dbMac);
                }
                else {
                    timeOfDetection = new ArrayList<>();
                }
                timeOfDetection.add(description);
                timeOfDetection.add(dbTime);

                pendriveDetected.put(dbMac, timeOfDetection);
            }
            System.out.println("\n  " + pendriveDetected);
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@pendrive test createStatementError" + e);
        }

//        System.out.println(pendriveDetected);
        return pendriveDetected;
    }

    public Map<String, List<String>> ramPerformance(String startTime, String endTime) {

        Map<String, List<String>> ramUsageDetails = new LinkedHashMap<>();
        List<String> ramUsage = null;
        double avgMem = 0;
        try {
            Statement stmt = conn.createStatement();

            String sql = "select DISTINCT mac_add,sum(used),count(mac_add),size from storage_map where type = 2 " +
                    "and time BETWEEN" + "'" + startTime + "'" + "AND" + "'" + endTime + "'"
                    + "GROUP BY mac_add order by mac_add,time desc";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String dbMac = rs.getString("mac_add");
                double sumOfMemory = Double.parseDouble(rs.getString("sum(used)"));
                int count = Integer.parseInt(rs.getString("count(mac_add)"));
                double size = Double.parseDouble(rs.getString("size"));

                if(ramUsageDetails.containsKey(dbMac)){
                    ramUsage = ramUsageDetails.get(dbMac);
                }
                else {
                    ramUsage = new ArrayList<>();
                }
                avgMem = sumOfMemory / (0.01 * count * size) ;
                ramUsage.add(String.valueOf(avgMem));

                ramUsageDetails.put(dbMac, ramUsage);
            }
//            System.out.println("\n  " + ramUsageDetails);
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@ramPerformance test createStatementError" + e);
        }

//        System.out.println(pendriveDetected);
        return ramUsageDetails;
    }

    public Map<String, List<String>> printerTest(String startTime, String endTime) {
        Map<String, List<String>> printerDetected = new LinkedHashMap<>();
        List<String> timeOfDetection = null;
        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT distinct mac_add,time from system_map where hr_printer_status = 4 and time BETWEEN" +
                    "'" + startTime + "'" + "AND" + "'" + endTime + "'" + " ORDER BY time";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {


//                String dbIp = rs.getString("ip");
                String dbMac = rs.getString("mac_add");
                String dbTime = rs.getString("time");

                if (!printerDetected.containsKey(dbMac)) {
                    timeOfDetection = new ArrayList<>();
                    printerDetected.put(dbMac, timeOfDetection);
                } else {
                    timeOfDetection = printerDetected.get(dbMac);
                }
                timeOfDetection.add(dbTime);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@printer test createStatementError" + e);
        }

//        System.out.println(printerDetected);
        return printerDetected;
    }

    public Map<String, List<String>> duplicateSystemTest(String startTime, String endTime) {

        Map<String, List<String>> duplicateSystemDetected = new LinkedHashMap<>();
        List<String> ipList = null;
        List<String> uniqueIpList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT distinct ip,mac_add from system_map where time BETWEEN" +
                    "'" + startTime + "'" + "AND" + "'" + endTime + "'" + " ORDER BY time";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String dbIp = rs.getString("ip");
                String dbMac = rs.getString("mac_add");

                if (!uniqueIpList.contains(dbMac)) {
                    uniqueIpList.add(dbMac);

                } else {
                    if (!duplicateSystemDetected.containsKey(dbMac)) {
                        ipList = new ArrayList<>();
                        duplicateSystemDetected.put(dbMac, ipList);
                    } else {
                        ipList = duplicateSystemDetected.get(dbMac);
                    }
                    ipList.add(dbIp);
                }
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@duplicateSystemTest test createStatementError" + e);
        }

//        System.out.println(printerDetected);
        return duplicateSystemDetected;
    }

    public Map<String, List<String>> addSystemDetailsToTestResults(Map<String, List<String>> testResults) {

        for (String mac : testResults.keySet()) {
            List macDetails = testResults.get(mac);
            try {
                Statement stmt = conn.createStatement();

                String sql = "SELECT distinct ip_ad_ent_addr,sys_name,sys_contact,sys_location from system_map where  mac_add =" +
                        "'" + mac + "'";

                ResultSet rs = stmt.executeQuery(sql);

                if (rs.next()) {
                    String ip = rs.getString("ip_ad_ent_addr");
                    String sysName = rs.getString("sys_name");
                    String sysContact = rs.getString("sys_contact");
                    String sysLocation = rs.getString("sys_location");
                    macDetails.add(0, ip);
                    macDetails.add(1, sysName);
                    macDetails.add(2, sysContact);
                    macDetails.add(3, sysLocation);
                }

            } catch (Exception e) {
                System.out.println("@addSystemDetailsToTestResults : " + e);
            }
        }
        return testResults;
    }
}