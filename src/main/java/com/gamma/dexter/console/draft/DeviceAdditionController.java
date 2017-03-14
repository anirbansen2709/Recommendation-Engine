/*
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
 * http://www.gammanalytics.com/
 * --------------------------------------------------------------------------------------------
 * The software in this package is published under the terms of the EUL (End User license)
 * agreement a copy of which has been included with this distribution in the license.txt file.
 */

package com.gamma.dexter.console.draft;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Rushil Mahaan on 2/12/2016.
 */
@Controller
public class DeviceAdditionController {

    String ipGlobal;
    String communityGlobal;

    @RequestMapping("/csvIpCommunity")
    public
    @ResponseBody
    String csvIpCommunity(@RequestParam(value = "ipCommunityBulkString") String ipCommunityBulkString) {

        System.out.println("@csvIpCommunity = " + ipCommunityBulkString);

        JSONObject json = null;
        Map<String, String> deviceMap;

        MapToSqlDb db = new MapToSqlDb();

            deviceMap = db.bulkDeviceAddition(ipCommunityBulkString);


        if (deviceMap == null) {
            System.out.println("duplicateDevice send fromm controller");
            return "duplicateDevice";
        } else {
            json = new JSONObject();
            json.putAll(deviceMap);
            return json.toString();
        }
    }


    @RequestMapping("/loginAjax")
    public
    @ResponseBody
    String loginAjax(@RequestParam(value = "ip") String ip,
                     @RequestParam(value = "community") String community) {
        ipGlobal = ip;
        communityGlobal = community;

        System.out.println(ipGlobal + " @ loginAjax controller");
        System.out.println(communityGlobal + " @ loginAjax controller");

        JSONObject json = null;
        Map<String, String> deviceMap;

        MapToSqlDb db = new MapToSqlDb();
        if (ip.equals("") && community.equals("")) {
            deviceMap = db.getAllDevices();
        } else {
            deviceMap = db.InputDeviceMapToDb(ip, community);
        }

        if (deviceMap == null) {
            System.out.println("duplicateDevice send fromm controller");
            return "duplicateDevice";
        } else {
            json = new JSONObject();
            json.putAll(deviceMap);
            return json.toString();
        }

    }

    @RequestMapping("/table")
    public
    @ResponseBody
    void home(@RequestParam(value = "ip") String live_ip,
              @RequestParam(value = "community") String live_community) {
        ipGlobal = live_ip;
        communityGlobal = live_community;
        System.out.println(ipGlobal);
        System.out.println(communityGlobal);
    }

    @RequestMapping("/deleteDevice")
    public
    @ResponseBody
    String deleteDevice(@RequestParam(value = "ip") String ip) {
        System.out.println(ip + " @ deleteDevice controller");
        JSONObject json = null;
        Map<String, String> deviceMap;

        MapToSqlDb db = new MapToSqlDb();
        deviceMap = db.deleteDevice(ip);
        if (deviceMap == null) {
            System.out.println("noDevice send fromm controller");
            return "noDevice";
        } else {
            json = new JSONObject();
            json.putAll(deviceMap);
            return json.toString();
        }

    }

    @RequestMapping("/hello") public
    @ResponseBody
    String hello(@RequestParam(value = "mapName") String mapName) {
        System.out.println("ip " + ipGlobal);
        System.out.println("community " + communityGlobal);
        System.out.println(mapName);
       /* if (communityGlobal == null || ipGlobal == null) {
            ipGlobal = "127.0.0.1";
            communityGlobal = "public";
            System.out.println("IP or Community not recieved");
        }
       */ JSONObject json = null;
        try {

            System.out.println("@controller-a  " + ipGlobal + communityGlobal);
            SnmpUtility client = new SnmpUtility(ipGlobal, communityGlobal);
            Map<String, String> snmpDataMap = client.snmpWalk(".");
            SnmpDataClassifier classifier = new SnmpDataClassifier();
            if (mapName.equalsIgnoreCase("systemMap")) {
                Map<String, String> snmpSystemData = classifier.getSystemMap(snmpDataMap);
                json = new JSONObject();
                json.putAll(snmpSystemData);
                System.out.println("@controller-b  " + json.toString());
            } else {
                Map<String, Map<String, String>> snmpData = classifier.traverseOid(snmpDataMap, mapName);
                json = new JSONObject();
                json.putAll(snmpData);
                System.out.println("@controller-c  " + json.toString());
            }
            return json.toString();
        } catch (Exception e) {
            System.out.println("@controller EXCEPTION IN RESPONSE BODY ( wrong ip ) : " + e);
            return "ipInvalid";
        }
    }





/*    public static void main(String[] args) {
        JSONArray ja = new JSONArray();
        ja.add("aaaa");
        JSONObject jo = new JSONObject();
        jo.put("ja", ja);
    }*/


}
