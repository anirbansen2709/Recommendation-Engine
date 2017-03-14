package com.gamma.dexter.console.draft.Notifiaction;

import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationDBHandler {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/snmp";
    static final String USER = "root";
    static final String PASS = "root";
    private Connection conn = null;

    public NotificationDBHandler() {
        // get user id
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println(" NotificationDBHandler connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String ,String> getIdandEmail(String username){

        Map<String,String> idEmailMap = new HashMap<>();
        try {

            Statement stmt = conn.createStatement();
            String sql = "SELECT id,email from users where username = " + "'" +username + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                idEmailMap.put("id",rs.getString("id"));
                idEmailMap.put("email",rs.getString("email"));
            }
            stmt.close();
            rs.close();

        } catch (Exception e) {
            System.out.println("@ inputNotificationParametersToDb , " + e);

        }
        return idEmailMap;
    }

    public String inputNotificationParametersToDb(NotificationPojo params) {

        String status = "OK";

        Map <String, String> idEmailMap = getIdandEmail(params.getUserName());
        String userId = idEmailMap.get("id");
        String email = idEmailMap.get("email");;

        try {
            Statement stmt = conn.createStatement();

            String searchID = "select user_id from notification_param where user_id = " + userId;
            System.out.println(searchID);
            ResultSet rs = stmt.executeQuery(searchID);

            if (!rs.next()) {

                String insertValues = "INSERT INTO notification_param " +
                        "VALUES ('" + userId + "','" + params.isPendriveDetectionEnabled() + "','" + params.isRamDetectionEnabled() + "','"
                        + params.isFreeSpaceDetectionEnabled() + "','" + params.isUploadDetectionEnabled() + "','" + params.isDownloadDetectionEnabled() +
                        "','" + params.isIpChangeDetectionEnabled() + "','" + params.isProcessDetectionEnabled() + "','" + params.isUptimeDetectionEnabled() +
                        "','" + params.getRamLimit() + "','" + params.getFreeSpaceLimit() + "','" + params.getUploadDataLimit()
                        + "','" + params.getDownloadDataLimit() + "','" + params.getUptimeLimit() + "','" + params.getProcessName()
                        + "','" + email + "')";
                System.out.println(insertValues);
                stmt.executeUpdate(insertValues);
            } else{
                System.out.println("user " + params.getUserName() + " exists");
                System.out.println("updating notification settings for " + params.getUserName());
                String update = "UPDATE notification_param set "+
                        "pendrive_detection_status ="+ params.isPendriveDetectionEnabled() +   "," +
                        "ram_detection_status =" + params.isRamDetectionEnabled() + "," +
                        "upload_detection_status ="+ params.isUploadDetectionEnabled() + "," +
                        "download_detection_status =" + params.isDownloadDetectionEnabled() + "," +
                        "ip_change_detection_status =" + params.isIpChangeDetectionEnabled() + "," +
                        "free_space_detection_status =" + params.isFreeSpaceDetectionEnabled() + "," +
                        "process_detection_status =" + params.isProcessDetectionEnabled() + "," +
                        "uptime_detection_status ="+ params.isUptimeDetectionEnabled() + "," +
                        "ram_limit ="+   params.getRamLimit() + "," +
                        "free_space_limit ="+ params.getFreeSpaceLimit() + "," +
                        "upload_limit ="+   params.getUploadDataLimit() + "," +
                        "download_limit ="+   params.getDownloadDataLimit() + "," +
                        "uptime_limit ="+   params.getUptimeLimit() + "," +
                        "process_name ="+  "'" + params.getProcessName() + "'" + "," +
                        "email ="+ "'" +  params.getEmail() + "'"+
                        "WHERE user_id="+userId;
                System.out.println(update);
                stmt.executeUpdate(update);
            }
            stmt.close();

        } catch (Exception e) {
            status = "NOK";
            System.out.println("@ inputNotificationParametersToDb -- " + "error in createStatement = " + e);
        }
        return status;
    }

    public List<NotificationPojo> extractNotificationParamsToPojo() {

        List<NotificationPojo> pojos = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();

            String sql = "Select * from notification_param";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                NotificationPojo paramFromDb = new NotificationPojo();

                String userId = rs.getString("user_id");
                int pendriveDetectionStatus = rs.getInt("pendrive_detection_status");
                paramFromDb.setDownloadDetectionStatus(pendriveDetectionStatus);
                paramFromDb.setUserId(userId);

                int ramDetectionStatus = rs.getInt("ram_detection_status");
                int ramLimit = rs.getInt("ram_limit");
                paramFromDb.setRamDetectionStatus(ramDetectionStatus);
                paramFromDb.setRamLimit(ramLimit);

                int freeSpaceDetectionStatus = rs.getInt("free_space_detection_status");
                double freeSpaceLimit = rs.getDouble("free_space_limit");
                paramFromDb.setFreeSpaceDetectionStatus(freeSpaceDetectionStatus);
                paramFromDb.setFreeSpaceLimit(freeSpaceLimit);

                int uploadDetectionStatus = rs.getInt("upload_detection_status");
                double uploadDataLimit = rs.getDouble("upload_limit");
                paramFromDb.setUploadDetectionStatus(uploadDetectionStatus);
                paramFromDb.setUploadDataLimit(uploadDataLimit);

                int downloadDetectionStatus = rs.getInt("download_detection_status");
                double downloadDataLimit = rs.getDouble("download_limit");
                paramFromDb.setDownloadDetectionStatus(downloadDetectionStatus);
                paramFromDb.setDownloadDataLimit(downloadDataLimit);

                int uptimeDetectionStatus = rs.getInt("uptime_detection_status");
                int uptimeLimit = rs.getInt("uptime_limit");
                paramFromDb.setUptimeDetectionStatus(uptimeDetectionStatus);
                paramFromDb.setUptimeLimit(uptimeLimit);


                int ipChangeDetectionStatus = rs.getInt("ip_change_detection_status");
                paramFromDb.setIpChangeDetectionStatus(ipChangeDetectionStatus);

                int processDetectionStatus = rs.getInt("process_detection_status");
                String processName = rs.getString("process_name");
                paramFromDb.setProcessDetectionStatus(processDetectionStatus);
                paramFromDb.setProcessName(processName);

                String mail = rs.getString("email");
                paramFromDb.setEmail(mail);

                pojos.add(paramFromDb);
            }
            stmt.close();

        } catch (Exception e) {
            System.out.println("error in extractNotificationParamsToPojo " + e);
        }


        return pojos;
    }

    public JSONObject extractNotificationSettingFromUserName(String userName) {

        Map<String, String> notificationMap = new HashMap<>();
        String userId = getIdandEmail(userName).get("id");

        try {
            Statement stmt = conn.createStatement();

            String sql = "Select * from notification_param where user_id = "+ "'"+ userId +"'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                notificationMap.put("user_id",rs.getString("user_id"));

                notificationMap.put("pendrive_detection_status",String.valueOf(rs.getInt("pendrive_detection_status")));
                notificationMap.put("ram_detection_status",String.valueOf(rs.getInt("ram_detection_status")));
                notificationMap.put("free_space_detection_status",String.valueOf(rs.getInt("free_space_detection_status")));
                notificationMap.put("upload_detection_status",String.valueOf(rs.getInt("upload_detection_status")));
                notificationMap.put("download_detection_status",String.valueOf(rs.getInt("download_detection_status")));
                notificationMap.put("ip_change_detection_status",String.valueOf(rs.getInt("ip_change_detection_status")));
                notificationMap.put("process_detection_status",String.valueOf(rs.getInt("process_detection_status")));
                notificationMap.put("uptime_detection_status",String.valueOf(rs.getInt("uptime_detection_status")));

                notificationMap.put("ram_limit",String.valueOf(rs.getInt("ram_limit")));

                notificationMap.put("free_space_limit",String.valueOf(rs.getDouble("free_space_limit")));
                notificationMap.put("upload_limit",String.valueOf(rs.getDouble("upload_limit")));
                notificationMap.put("download_limit",String.valueOf(rs.getDouble("download_limit")));

                notificationMap.put("uptime_limit",String.valueOf(rs.getInt("uptime_limit")));

                notificationMap.put("process_name",rs.getString("process_name"));
                notificationMap.put("email",rs.getString("email"));
            }
            stmt.close();
            stmt.close();
        } catch (Exception e) {

        }
        JSONObject json = new JSONObject();
        json.putAll(notificationMap);

        return json;
    }

    public static void main(String args[]){

        NotificationDBHandler notificationDBHandler = new NotificationDBHandler();
        System.out.println(notificationDBHandler.extractNotificationSettingFromUserName("RK"));
    }
}

