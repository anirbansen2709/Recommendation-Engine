/*
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
 * http://www.gammanalytics.com/
 * --------------------------------------------------------------------------------------------
 * The software in this package is published under the terms of the EUL (End User license)
 * agreement a copy of which has been included with this distribution in the license.txt file.
 */

package com.gamma.dexter.console.draft.dataCollector;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.sql.PreparedStatement;

public class SnmpDataCollector {

    Snmp snmp;
    String ip, community;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/snmpdata";
    static final String USER = "root";
    static final String PASS = "root";

    private Connection conn = null;
    PreparedStatement preparedStatement = null;

    public SnmpDataCollector(String ip, String community) throws IOException {

        this.ip = ip;
        this.community = community;

        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        transport.listen();

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println(" SnmpDataCollecctor connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void performRawSnmpDataDump(){

        Map<String, String> snmpDataMap = null;
        try {
            snmpDataMap = snmpWalk(".");
        } catch (IOException e) {
            System.out.println("error in retrieving snmp data from the system" + e);
        }
        try {
//            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);

            Date dateStart = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeStart = dateFormat.format(dateStart).toString();
            System.out.println(timeStart);

            String snmpRawDataTableRow = "INSERT INTO oid_value " +
                    "VALUES (?,?)";
            preparedStatement = conn.prepareStatement(snmpRawDataTableRow);

            for (String key : snmpDataMap.keySet()) {

                String value = snmpDataMap.get(key);

                if(value.length()<250 && key.length()<250){

//                    stmt.executeUpdate(snmpRawDataTableRow);
//                    preparedStatement.executeUpdate();
                    preparedStatement.setString(1,key);
                    preparedStatement.setString(2,value);
                    preparedStatement.addBatch();
                }
            }
            int[] count = preparedStatement.executeBatch();
            System.out.println( count);
            conn.commit();

            Date dateEnd = new Date();
            String timeEnd = dateFormat.format(dateEnd).toString();
            System.out.println(timeEnd);

        }catch (Exception e){
                System.out.println("sql error  " + e );
            }
        }


    public Map<String, String> snmpWalk(String startOid) throws IOException{

        String oid = startOid;
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

        while(true) {
            PDU pdu = getVariableBinding(new OID(oid), PDU.GETNEXT);
            if (pdu == null || pdu.size() == 0) return null;

            VariableBinding var = pdu.get(0);
            if (var == null) return null;

            oid = var.getOid().toString();
            if ((var.getVariable().toString()).compareTo("endOfMibView")!=0) {
                map.put(oid, var.getVariable().toString());
            } else {
                break;
            }
        }
        return  map;
    }

    private PDU getVariableBinding(OID oid, int type) throws IOException {

        ResponseEvent event = get(new OID[] { oid }, type);

        if (event == null || event.getResponse() == null) {
            return null;
        }
        return event.getResponse();
    }

    private ResponseEvent get(OID oids[], int type) throws IOException {

        PDU pdu = new PDU();
        for (OID oi : oids) {
            pdu.add(new VariableBinding(oi));
        }
        pdu.setType(type);

        ResponseEvent event = snmp.send(pdu, getTarget(), null);
        if (event != null) {
            return event;
        }
        throw new RuntimeException("GET timed out");
    }

    private Target getTarget() {

        Address targetAddress = GenericAddress.parse("udp:" + ip + "/161");
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(3000);
        target.setVersion(SnmpConstants.version2c);
        return target;
    }

    public static void main(String[] args) throws IOException {

        SnmpDataCollector client = new SnmpDataCollector("192.168.1.15", "public");
        client.performRawSnmpDataDump();

        Map<String, String> snmpDataMap = client.snmpWalk(".");

        for (String key : snmpDataMap.keySet()) {
            System.out.println(key + " = " + snmpDataMap.get(key));
        }
    }
}
