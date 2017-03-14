package com.gamma.dexter.console.draft.threadManager;

import com.gamma.dexter.console.draft.MapToSqlDb;
import com.gamma.dexter.console.draft.Notifiaction.NotificationAlert;
import com.gamma.dexter.console.draft.Notifiaction.NotificationDBHandler;
import com.gamma.dexter.console.draft.Notifiaction.NotificationPojo;
import com.gamma.dexter.console.draft.SnmpDataClassifier;
import com.gamma.dexter.console.draft.SnmpUtility;
import com.gamma.dexter.console.draft.mail.JavaGmailSSL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 4/26/2016.
 */

public class NotificationWorkerThread implements Runnable {

    String ip, community, threadName;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public NotificationWorkerThread(String ip, String community) {
        this.ip = ip;
        this.community = community;
    }

    @Override
    public void run() {
        Date startTime = new Date();
        threadName = Thread.currentThread().getName() + "  Thread Started at  " + dateFormat.format(startTime) + ip +" "+community;
        processCommand();
        Date endTime = new Date();
        System.out.println(Thread.currentThread().getName() + " Thread work done at " + dateFormat.format(endTime)+ ip +" "+community);
    }

    private Map<String, String> getSnmpData() {

        MapToSqlDb db = new MapToSqlDb();
        Map<String, String> dataMap = new HashMap<>();

        try {
            SnmpUtility client = new SnmpUtility(ip, community);
            dataMap = client.snmpWalk(".");

            Map<String, String> testSystemMap = new SnmpDataClassifier().getSystemMap(dataMap);

            db.inputMapToDb("processMap", dataMap);
            db.inputMapToDb("storageMap", dataMap);
            db.inputMapToDb("SWMap", dataMap);
            db.inputMapToDb("deviceMap", dataMap);
            db.inputMapToDb("interfaceMap", dataMap);
            db.inputMapToDb("systemMap", dataMap);
            System.out.println(ip + community + " dataWrite done");


        } catch (Exception e) {
            System.out.println(ip + community + " not in the network by" + threadName);
        }
        return dataMap;
    }

    private void processCommand() {
        try {

            Map<String, String> dataMap = getSnmpData();

            if (dataMap != null) {
                List<NotificationPojo> notificationPojos = new NotificationDBHandler().extractNotificationParamsToPojo();

                for (NotificationPojo rule : notificationPojos) {

                    NotificationAlert notificationAlert = new NotificationAlert();
                    Map<String, List<String>> pcDetected = notificationAlert.getPcDetectedList(dataMap, rule);
                    System.out.println("pcDetected List = " + pcDetected);
                    JavaGmailSSL sendMail = new JavaGmailSSL();
                    sendMail.prepareMail(pcDetected, rule.getEmail());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}