package com.gamma.dexter.console.draft;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * Created by lenovo on 3/15/2016.
 */
public class Charts {


    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/snmp";
    static final String USER = "root";
    static final String PASS = "root";

    private Connection conn = null;

    public Charts() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println(" Charts connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        Charts db = new Charts();


        JSONObject uptime = db.upTimeAnalysis();
        JSONObject freeSize = db.freeStorageAnalysis();
        JSONObject totSize = db.storageSizeAnalysis();
        JSONObject downDytes = db.downloadAnalysis();
        JSONObject upBytes = db.uploadAnalysis();
        JSONObject avgRamAnal = db.avgRamAnalysis();
        JSONObject externeviceFrequency = db.externalDeviceFrequencyAnalysis(20);
        JSONObject processFrequency = db.processFrequencyAnalysis("chrome.exe", 3);


//        System.out.println(processFrequency);
        System.out.println("down: "+downDytes);
        System.out.println("up: "+upBytes);


//        System.out.println("MS: "+db.msUpDownLoadAnalysis());
//        System.out.println(avgRamAnal);
//        System.out.println(totSize);
//        JSONArray msupdownloadanalysis = db.msUpDownLoadAnalysis();
//        JSONObject msupdownloadanalysis = JSONObject.fromObject(db.msUpDownLoadAnalysis());
//        System.out.println("ms1 "+msupdownloadanalysis);
//        System.out.println("ms2 "+msupdownloadanalysis.toString());
    }

    private static JSONObject convertToJson(Map<String, Integer> sorted) {
        JSONArray array = new JSONArray();

        for (String mac : sorted.keySet()) {

            int value = sorted.get(mac);
            JSONObject json = new JSONObject();
            json.put("label", mac);
            json.put("value", value);
            array.add(json);
        }
        JSONObject mainObj = new JSONObject();
        mainObj.put("data", array);

        return mainObj;

    }

    private static JSONObject convertToJsonDouble(Map<String, Double> sorted) {
        JSONArray array = new JSONArray();

        for (String mac : sorted.keySet()) {

            Double value = sorted.get(mac);
            JSONObject json = new JSONObject();
            json.put("label", mac);
            json.put("value", value);
            array.add(json);
        }
        JSONObject mainObj = new JSONObject();
        mainObj.put("data", array);

        return mainObj;

    }

    public Double roundDoubleGB(Double value){
        value = value /1024.0/1024.0/1024.0;
        value = (Math.round(value*10.0)/10.0);
        return value;
    }

    public Double roundDoubleMB(Double value){
        value = value /1024.0/1024.0;
        value = (Math.round(value*10.0)/10.0);
        return value;
    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {

        // Convert Map to List
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // Convert sorted map back to a Map
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        int i = 0;
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            if (++i <= 10) {
                Map.Entry<String, Integer> entry = it.next();
                sortedMap.put(entry.getKey(), entry.getValue());
            }
        }
        return sortedMap;
    }

    private static Map<String, Double> sortByComparatorDouble(Map<String, Double> unsortMap) {

        // Convert Map to List
        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // Convert sorted map back to a Map
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        int i = 0;
        for (Iterator<Map.Entry<String, Double>> it = list.iterator(); it.hasNext(); ) {
            if (++i <= 10) {
                Map.Entry<String, Double> entry = it.next();
                sortedMap.put(entry.getKey(), entry.getValue());
            }
        }
        return sortedMap;
    }

    public JSONObject freeStorageAnalysis() {

        Map<String, Double> freeSizeMap = new LinkedHashMap<>();
        List<String> driveList = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();

            String sql = "select mac_add,description,free from storage_map where type = 4 ORDER BY mac_add,time DESC";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {

                String dbMac1 = rs.getString("mac_add");
                Double free1 = rs.getDouble("free");
                String desc1 = rs.getString("description");
                desc1 = desc1.substring(0, 1);

                driveList.add(desc1);
                Double sum = free1;

                while (rs.next()) {

                    String dbMac2 = rs.getString("mac_add");
                    Double free2 = rs.getDouble("free");
                    String desc2 = rs.getString("description");
                    desc2 = desc2.substring(0, 1);

                    if (dbMac1.equals(dbMac2) && !driveList.contains(desc2)) {
//                        System.out.println(sum + " + " + free2);
                        sum += free2;
                        driveList.add(desc2);
                    } else if (!dbMac1.equals(dbMac2) && !freeSizeMap.containsKey(dbMac1)) {

//                            System.out.println(dbMac1 + "        ,           " + sum + "   " + driveList);
                        freeSizeMap.put(dbMac1, roundDoubleGB(sum));
                        driveList.clear();
//                            System.out.println(driveList);
                        sum = free2;
                        driveList.add(desc2);
                    }
                    dbMac1 = dbMac2;
                }
                if (!freeSizeMap.containsKey(dbMac1)) {
                    freeSizeMap.put(dbMac1, roundDoubleGB(sum));
                    System.out.println("Free Strorage " +sum);
//                    System.out.println(dbMac1 + "        ,           " + sum + "   " + driveList);
                }
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@freeStorageAnalysis  createStatementError" + e);
        }

        Map<String, Double> sortedMap = sortByComparatorDouble(freeSizeMap);
        JSONObject freeSizeInJson = convertToJsonDouble(sortedMap);

        return freeSizeInJson;
    }

    public JSONObject storageSizeAnalysis() {

        Map<String, Double> total = new LinkedHashMap<>();
        List<String> driveList = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();

            String sql = "select mac_add,description,size from storage_map where type = 4 ORDER BY mac_add,time DESC";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {

                String dbMac1 = rs.getString("mac_add");
                Double size1 = rs.getDouble("size");
                String desc1 = rs.getString("description");
                desc1 = desc1.substring(0, 1);

                driveList.add(desc1);
                Double sum = size1;

                while (rs.next()) {

                    String dbMac2 = rs.getString("mac_add");
                    Double size2 = rs.getDouble("size");
                    String desc2 = rs.getString("description");
                    desc2 = desc2.substring(0, 1);

                    if (dbMac1.equals(dbMac2) && !driveList.contains(desc2)) {
//                        System.out.println(sum + " + " + size2);
                        sum += size2;
                        driveList.add(desc2);
                    } else if (!dbMac1.equals(dbMac2) && !total.containsKey(dbMac1)) {

//                            System.out.println(dbMac1 + "        ,           " + sum + "   " + driveList);
                        total.put(dbMac1, roundDoubleGB(sum));
                        driveList.clear();
                        System.out.println(driveList);
                        sum = size2;
                        driveList.add(desc2);
                    }
                    dbMac1 = dbMac2;
                }
//                sum = sum/(1024*1024*1024);
                if (!total.containsKey(dbMac1)) {
                    total.put(dbMac1,roundDoubleGB(sum));
//                    System.out.println(dbMac1 + "        ,           " + sum + "   " + driveList);
                }
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@storageSizeAnalysis  createStatementError" + e);
        }

        Map<String, Double> sortedMap = sortByComparatorDouble(total);
        JSONObject totalInJson = convertToJsonDouble(sortedMap);

        return totalInJson;
    }

    public JSONObject downloadAnalysis() {

        Map<String, Double> download = new LinkedHashMap<>();

        try {
            Statement stmt = conn.createStatement();

            String sql = "select  mac_add,if_in_octets from interface_map where if_type='71' and" +
                    " if_in_octets>0 order by mac_add,time desc";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {

                String dbMac1 = rs.getString("mac_add");
                Double max = rs.getDouble("if_in_octets");

                Double sum = max;

                while (rs.next()) {

                    String dbMac2 = rs.getString("mac_add");
                    Double octets2 = rs.getDouble("if_in_octets");

                    if (dbMac1.equals(dbMac2)) {
                        if (max < octets2) {
//                                System.out.println(sum + "+" + octets2+ " = ");
                            sum += octets2;
//                                System.out.println(sum);
                        }
                        max = octets2;
                    } else {
                        if (!download.containsKey(dbMac1)) {
                            download.put(dbMac1, roundDoubleGB(sum));
//                                System.out.println(dbMac1 + "--" + dbMac2);
                        }
                        dbMac1 = dbMac2;
                        sum = octets2;
                    }
                }
//                sum = sum/(1024*1024);
                if (!download.containsKey(dbMac1)) {
                    download.put(dbMac1, roundDoubleGB(sum));
                }

            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@downloadAnalysis  createStatementError" + e);
        }

        Map<String, Double> sortedMap = sortByComparatorDouble(download);
        JSONObject sortedJson = convertToJsonDouble(sortedMap);
        return sortedJson;
    }

    public JSONObject uploadAnalysis() {

        Map<String, Double> upload = new LinkedHashMap<>();

        try {
            Statement stmt = conn.createStatement();

            String sql = "select  mac_add,if_out_octets from interface_map where if_type='71' and" +
                    " if_out_octets>0 order by mac_add,time desc;";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {

                String dbMac1 = rs.getString("mac_add");
                Double max = rs.getDouble("if_out_octets");

                Double sum = max;

                while (rs.next()) {

                    String dbMac2 = rs.getString("mac_add");
                    Double octets2 = rs.getDouble("if_out_octets");

                    if (dbMac1.equals(dbMac2)) {
                        if (max < octets2) {
//                                System.out.println(sum + "+" + octets2+ " = ");
                            sum += octets2;
//                                System.out.println(sum);
                        }
                        max = octets2;
                    } else {
                        if (!upload.containsKey(dbMac1)) {
                            upload.put(dbMac1, roundDoubleGB(sum));
//                                System.out.println(dbMac1 + "--" + dbMac2);
                        }
                        dbMac1 = dbMac2;
                        sum = octets2;
                    }
                }
                sum = sum/(1024*1024);
                if (!upload.containsKey(dbMac1)) {
                    upload.put(dbMac1, roundDoubleGB(sum));
                }

            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@uploadAnalysis  createStatementError" + e);
        }

        Map<String, Double> sortedMap = sortByComparatorDouble(upload);
        JSONObject sortedJson = convertToJsonDouble(sortedMap);

        return sortedJson;
    }

    public JSONArray msUpDownLoadAnalysis() {

        Map<String, Double> download = new LinkedHashMap<>();
        Map<String, Double> total = new LinkedHashMap<>();

        try {
            Statement stmt = conn.createStatement();

            String sql = "select  mac_add,if_in_octets from interface_map where if_type='71' and" +
                    " if_in_octets>0 order by mac_add,time desc";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {

                String dbMac1 = rs.getString("mac_add");
                Double max = rs.getDouble("if_in_octets");

                Double sum = max;

                while (rs.next()) {

                    String dbMac2 = rs.getString("mac_add");
                    Double octets2 = rs.getDouble("if_in_octets");

                    if (dbMac1.equals(dbMac2)) {
                        if (max < octets2) {
//                                System.out.println(sum + "+" + octets2+ " = ");
                            sum += octets2;
//                                System.out.println(sum);
                        }
                        max = octets2;
                    } else {
                        if (!download.containsKey(dbMac1)) {
                            download.put(dbMac1, sum);
//                                System.out.println(dbMac1 + "--" + dbMac2);
                        }
                        dbMac1 = dbMac2;
                        sum = octets2;
                    }
                }
                sum =sum/(1024*1024);
                if (!download.containsKey(dbMac1)) {
                    download.put(dbMac1, sum);
                }

            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@downloadAnalysis  createStatementError" + e);
        }

        Map<String, Double> upload = new LinkedHashMap<>();
        try {
            Statement stmt = conn.createStatement();

            String sql = "select  mac_add,if_out_octets from interface_map where if_type='71' and" +
                    " if_out_octets>0 order by mac_add,time desc;";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {

                String dbMac1 = rs.getString("mac_add");
                Double max = rs.getDouble("if_out_octets");

                Double sum = max;

                while (rs.next()) {

                    String dbMac2 = rs.getString("mac_add");
                    Double octets2 = rs.getDouble("if_out_octets");

                    if (dbMac1.equals(dbMac2)) {
                        if (max < octets2) {
//                                System.out.println(sum + "+" + octets2+ " = ");
                            sum += octets2;
//                                System.out.println(sum);
                        }
                        max = octets2;
                    } else {
                        if (!upload.containsKey(dbMac1)) {
                            upload.put(dbMac1, sum);
//                                System.out.println(dbMac1 + "--" + dbMac2);
                        }
                        dbMac1 = dbMac2;
                        sum = octets2;
                    }
                }
                sum = sum / (1024*1024);
                if (!upload.containsKey(dbMac1)) {
                    upload.put(dbMac1, sum);
                }

            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@MultiSeries data usage Analysis  createStatementError" + e);
        }

//        Map<String, Double> sortedDownloadMap = sortByComparatorDouble(download);

        JSONArray labelarray = new JSONArray();
        JSONArray upArray = new JSONArray();
        JSONArray downArray = new JSONArray();

        //test
        for (String mac : download.keySet()) {
            Double totalValue = download.get(mac) + upload.get(mac);
            total.put(mac, totalValue);
        }

        Map<String, Double> sortedMap = sortByComparatorDouble(total);

        for (String mac : sortedMap.keySet()) {

            Double downValue = download.get(mac);
            Double upValue = upload.get(mac);

            JSONObject labelJson = new JSONObject();
            JSONObject upJson = new JSONObject();
            JSONObject downJson = new JSONObject();

            labelJson.put("label", mac);
            upJson.put("value", upValue);
            downJson.put("value", downValue);

            labelarray.add(labelJson);
            upArray.add(upJson);
            downArray.add(downJson);
        }
        JSONObject labelObj = new JSONObject();
        labelObj.put("category", labelarray);

        JSONObject upObj = new JSONObject();
        upObj.put("data1", upArray);

        JSONObject downObj = new JSONObject();
        downObj.put("data2", downArray);

        JSONArray mainArray = new JSONArray();
        mainArray.add(labelObj);
        mainArray.add(upObj);
        mainArray.add(downObj);
        return mainArray;
    }

    public JSONObject upTimeAnalysis() {

        Map<String, Integer> upTime = new LinkedHashMap<>();

        try {
            Statement stmt = conn.createStatement();

            String sql = "select distinct mac_add,sys_up_time_instance from system_map ORDER BY time DESC";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String dbMac = rs.getString("mac_add");
                int dbUptime = rs.getInt("sys_up_time_instance");

                if (!upTime.containsKey(dbMac)) {
                    upTime.put(dbMac, dbUptime /3600);
                }
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@upTimeAnalysis  createStatementError" + e);
        }

        Map<String, Integer> sortedUptime = sortByComparator(upTime);
        JSONObject sortedJson = convertToJson(sortedUptime);

        return sortedJson;
    }

    public JSONObject externalDeviceFrequencyAnalysis(int minutes) {

        Map<String, Integer> externalDeviceFrequency = new LinkedHashMap<>();
        Date tempDate;

        try {
            Statement stmt = conn.createStatement();

            String sql = "select distinct mac_add, time from storage_map where type = 5 " +
                    "order by mac_add,time desc ;";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);


            if (rs.next()) {

                String tempMac = rs.getString("mac_add");
                tempDate = rs.getTimestamp("time");

                int count = 1;

                while (rs.next()) {
                    String dbMac = rs.getString("mac_add");
                    Date dbDate = rs.getTimestamp("time");

//                    System.out.println(Math.abs(tempDate.getTime() - dbDate.getTime())/60000);

                    if (dbMac.equals(tempMac) && Math.abs(tempDate.getTime() - dbDate.getTime()) / 60000 > minutes) {
                        count++;
                    } else {
                        if (!externalDeviceFrequency.containsKey(tempMac)) {
                            externalDeviceFrequency.put(tempMac, count);
                            count = 1;
                        }
                    }
                    tempMac = dbMac;
                    tempDate = dbDate;
                }
                if (!externalDeviceFrequency.containsKey(tempMac)) {
                    externalDeviceFrequency.put(tempMac, count);
                }
            }

            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@externalDeviceFrequency  createStatementError" + e);
        }

        Map<String, Integer> sortedUptime = sortByComparator(externalDeviceFrequency);
        JSONObject sortedJson = convertToJson(sortedUptime);

        return sortedJson;
    }

    public JSONObject processFrequencyAnalysis(String processName, int minutes) {

        Map<String, Integer> processFrequency = new LinkedHashMap<>();
        Date tempDate;

        try {
            Statement stmt = conn.createStatement();

            String sql = "select distinct mac_add, time, name from process_map where name = " + "'" + processName + "'" +
                    "order by mac_add,time desc ;";

//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);


            if (rs.next()) {

                String tempMac = rs.getString("mac_add");
                tempDate = rs.getTimestamp("time");

                int count = 1;

                while (rs.next()) {
                    String dbMac = rs.getString("mac_add");
                    Date dbDate = rs.getTimestamp("time");

//                    System.out.println(Math.abs(tempDate.getTime() - dbDate.getTime())/60000);

                    if (dbMac.equals(tempMac) && Math.abs(tempDate.getTime() - dbDate.getTime()) / 60000 > minutes) {
                        count++;
                    } else {
                        if (!processFrequency.containsKey(tempMac)) {
                            processFrequency.put(tempMac, count);
                            count = 1;
                        }
                    }
                    tempMac = dbMac;
                    tempDate = dbDate;
                }
                if (!processFrequency.containsKey(tempMac)) {
                    processFrequency.put(tempMac, count);
                }
            }

            stmt.close();
            rs.close();
        } catch (Exception e) {

            System.out.println("@externalDeviceFrequency  createStatementError" + e);
        }

        Map<String, Integer> sortedUptime = sortByComparator(processFrequency);
        JSONObject sortedJson = convertToJson(sortedUptime);

        return sortedJson;
    }

    public JSONObject avgRamAnalysis() {

        Map<String, Double> avgRam = new LinkedHashMap<>();

        double  avgMem = 0;

        try {
            Statement stmt = conn.createStatement();

            String sql = "select DISTINCT mac_add,sum(used),count(mac_add),size from storage_map where type = 2 " +
                    "GROUP BY mac_add order by mac_add,time desc";

            ResultSet rs = stmt.executeQuery(sql);


                while (rs.next()) {

                    String dbMac = rs.getString("mac_add");
                    double sumOfMemory = Double.parseDouble(rs.getString("sum(used)"));
                    int count = Integer.parseInt(rs.getString("count(mac_add)"));
                    double size = Double.parseDouble(rs.getString("size"));

                    if (!avgRam.containsKey(dbMac)) {
                            avgMem = sumOfMemory / (0.01 * count * size) ;
                            System.out.println(avgMem + "," + sumOfMemory + "," + count);
                            avgRam.put(dbMac, avgMem);
                        }
                }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("@avgRamAnalysis createStatementError" + e);
        }

        Map<String, Double> sortedRsmResults = sortByComparatorDouble(avgRam);
        JSONObject sortedJson = convertToJsonDouble(sortedRsmResults);

        return sortedJson;
    }

// select DISTINCT mac_add, time, memory from process_map order by mac_add,time desc;

//    public JSONObject downloadAnalysis() {
//
//        Map<String, Double> download = new LinkedHashMap<>();
//
//        try {
//            Statement stmt = conn.createStatement();
//
//            String sql = "select  mac_add,if_in_octets,if_descr,time from interface_map where if_type='71' and" +
//                    " if_in_octets>0 order by mac_add,time desc;";
//
////            System.out.println(sql);
//            ResultSet rs = stmt.executeQuery(sql);
//
//            if (rs.next()) {
//
//                String dbMac1 = rs.getString("mac_add");
//                Double octets1 = rs.getDouble("if_in_octets");
//
//                Double  sum = 0.0;
//
//                while (rs.next()) {
//
//                    String dbMac2 = rs.getString("mac_add");
//                    Double octets2 = rs.getDouble("if_in_octets");
//                    Double sumTemp = sum;
//
//                    if(dbMac1.equals(dbMac2)) {
//                        if (octets1 >= octets2) {
//                            sum += (octets1 - octets2);
//                            if(sum>sumTemp)
//                                System.out.println(sum);
//                            octets1 = octets2;
//                        } else {
//                            sum += octets1;
//                            octets1 = octets2;
//                        }
//                    }else{
//                        if (!download.containsKey(dbMac1) && download.size() < 10) {
//                            download.put(dbMac1, sum);
//                            System.out.println(dbMac1 + "--" + dbMac2);
//                        }
//                        dbMac1 = dbMac2;
//                        sum = 0.0;
//                    }
//                }
//                if (!download.containsKey(dbMac1) && download.size() < 10) {
//                    download.put(dbMac1, sum);
//                }

//
//            }
//            stmt.close();
//            rs.close();
//        } catch (Exception e) {
//
//            System.out.println("@downloadAnalysis  createStatementError" + e);
//        }
//
//        JSONObject sortedJson = convertToJsonDouble(download);
//        return sortedJson;
//    }

}
