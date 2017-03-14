/*
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
 * http://www.gammanalytics.com/
 * --------------------------------------------------------------------------------------------
 * The software in this package is published under the terms of the EUL (End User license)
 * agreement a copy of which has been included with this distribution in the license.txt file.
 */

package com.gamma.dexter.console.draft;

import java.io.IOException;
import java.util.*;

/**
 * Created by lenovo on 1/19/2016.
 */


public class SnmpDataClassifier {

    private Map<String, Map<String, String>> runningProcessMap = new HashMap<>();
    private Map<String, Map<String, String>> SWInstalledMap = new HashMap<>();
    private Map<String, Map<String, String>> storageMap = new HashMap<>();
    private Map<String, Map<String, String>> deviceMap = new HashMap<>();
    private Map<String, Map<String, String>> interfaceMap = new LinkedHashMap<>();
    private Map<String, String> systemMap = new HashMap<>();

    public Map<String, Map<String, String>> getRunningProcessMap(Map<String, String> snmpWalkData) {

        Map<String, String> processInfoMap;

        for (String oid : snmpWalkData.keySet()) {

            if (!checkIfProcessInfo(oid)) {
                continue;
            }

            String id[] = oid.split("\\.");
            String processId = id[id.length - 1];

            if (!runningProcessMap.containsKey(processId)) {
                processInfoMap = new HashMap<>();
                runningProcessMap.put(processId, processInfoMap);
            } else {
                processInfoMap = runningProcessMap.get(processId);
            }

            if (oid.startsWith("1.3.6.1.2.1.25.4.2.1.2")) {
                processInfoMap.put("name", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.25.4.2.1.4")) {
                processInfoMap.put("runPath", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.25.4.2.1.5")) {
                processInfoMap.put("runParameters", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.25.4.2.1.6")) {

                String runType = snmpWalkData.get(oid);

                if (runType.equals("1"))
                    runType = "Unknown";
                else if (runType.equals("2"))
                    runType = "Operating Sysytem";
                else if (runType.equals("3"))
                    runType = "Device Driver";
                else if (runType.equals("4"))
                    runType = "Application";
                processInfoMap.put("runType", runType);

            } else if (oid.startsWith("1.3.6.1.2.1.25.4.2.1.7")) {

                String runStatus = snmpWalkData.get(oid);

                if (runStatus.equals("1"))
                    runStatus = "Running";
                else if (runStatus.equals("2"))
                    runStatus = "Runnable";
                else if (runStatus.equals("3"))
                    runStatus = "Not Runnable ";
                else if (runStatus.equals("4"))
                    runStatus = "Invalid";

                processInfoMap.put("runStatus", runStatus);
            } else if (oid.startsWith("1.3.6.1.2.1.25.5.1.1.1")) {
                processInfoMap.put("cpuPerformance", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.25.5.1.1.2")) {
                processInfoMap.put("memory", snmpWalkData.get(oid));
            }
        }

        return runningProcessMap;
    }

    private boolean checkIfProcessInfo(String oid) {
        return oid.startsWith("1.3.6.1.2.1.25.4.2.1.2") || oid.startsWith("1.3.6.1.2.1.25.4.2.1.4")
                || oid.startsWith("1.3.6.1.2.1.25.4.2.1.5") || oid.startsWith("1.3.6.1.2.1.25.4.2.1.6")
                || oid.startsWith("1.3.6.1.2.1.25.4.2.1.7") || oid.startsWith("1.3.6.1.2.1.25.5.1.1.1")
                || oid.startsWith("1.3.6.1.2.1.25.5.1.1.2");
    }

    public Map<String, Map<String, String>> getSWInstalledMap(Map<String, String> snmpWalkData) {

        Map<String, String> SWInfoMap;

        for (String oid : snmpWalkData.keySet()) {

            if (!checkSWInfo(oid)) {
                continue;
            }

            String id[] = oid.split("\\.");
            String softwareId = id[id.length - 1];

            if (!SWInstalledMap.containsKey(softwareId)) {
                SWInfoMap = new HashMap<>();
                SWInstalledMap.put(softwareId, SWInfoMap);
            } else {
                SWInfoMap = SWInstalledMap.get(softwareId);
            }

            if (oid.startsWith("1.3.6.1.2.1.25.6.3.1.2")) {
                SWInfoMap.put("swInstalledName", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.25.6.3.1.5")) {
                SWInfoMap.put("swInstalledDate", DateConvertHex.convertDecimal(snmpWalkData.get(oid)));
            }
        }
        return SWInstalledMap;
    }

    private boolean checkSWInfo(String oid) {
        return oid.startsWith("1.3.6.1.2.1.25.6.3.1.2") || oid.startsWith("1.3.6.1.2.1.25.6.3.1.5");
    }

    public Map<String, Map<String, String>> getStorageMap(Map<String, String> snmpWalkData) {

        Map<String, String> storageInfoMap;
        for (String oid : snmpWalkData.keySet()) {

            if (!checkStorageInfo(oid)) {
                continue;
            }

            String id[] = oid.split("\\.");
            String storageId = id[id.length - 1];

            if (!storageMap.containsKey(storageId)) {
                storageInfoMap = new HashMap<>();
                storageMap.put(storageId, storageInfoMap);
            } else {
                storageInfoMap = storageMap.get(storageId);
            }

            if (oid.startsWith("1.3.6.1.2.1.25.2.3.1.2")) {
                String type = snmpWalkData.get(oid);
                type = (String.valueOf(type.charAt(type.length() - 1)));
                storageInfoMap.put("type", type);
            } else if (oid.startsWith("1.3.6.1.2.1.25.2.3.1.3")) {
                storageInfoMap.put("description", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.25.2.3.1.4")) {
                storageInfoMap.put("allocation units", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.25.2.3.1.5")) {
                storageInfoMap.put("size", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.25.2.3.1.6")) {
                storageInfoMap.put("used", snmpWalkData.get(oid));
            }
        }

        for (String storageId : storageMap.keySet()) {

            Map<String, String> storageInfoFilterMap = storageMap.get(storageId);

            long units = Long.parseLong(storageInfoFilterMap.get("allocation units"));
            long size = Long.parseLong(storageInfoFilterMap.get("size"));
            long used = Long.parseLong(storageInfoFilterMap.get("used"));
            long free = (size - used) * units;
            size = size * units;
            used = used * units;
            storageInfoFilterMap.put("size", String.valueOf(size));
            storageInfoFilterMap.put("allocation units", String.valueOf(units));
            storageInfoFilterMap.put("free", String.valueOf(free));
            storageInfoFilterMap.put("used", String.valueOf(used));
        }
        return storageMap;
    }

    private boolean checkStorageInfo(String oid) {
        return oid.startsWith("1.3.6.1.2.1.25.2.3.1.2") || oid.startsWith("1.3.6.1.2.1.25.2.3.1.3")
                || oid.startsWith("1.3.6.1.2.1.25.2.3.1.4") || oid.startsWith("1.3.6.1.2.1.25.2.3.1.5")
                || oid.startsWith("1.3.6.1.2.1.25.2.3.1.6");
    }

    public Map<String, Map<String, String>> getDeviceMap(Map<String, String> snmpWalkData) {

        Map<String, String> hrDeviceInfoMap;

        for (String oid : snmpWalkData.keySet()) {

            if (!checkHrDeviceDescrInfo(oid)) {
                continue;
            }

            String id[] = oid.split("\\.");
            String deviceId = id[id.length - 1];

            if (!deviceMap.containsKey(deviceId)) {
                hrDeviceInfoMap = new HashMap<>();
                deviceMap.put(deviceId, hrDeviceInfoMap);
            } else {
                hrDeviceInfoMap = deviceMap.get(deviceId);
            }

            if (oid.startsWith("1.3.6.1.2.1.25.3.2.1.3")) {
                hrDeviceInfoMap.put("hrDeviceDescr", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.25.3.2.1.5")) {
                hrDeviceInfoMap.put("hrDeviceStatus", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.25.3.2.1.6")) {
                hrDeviceInfoMap.put("hrDeviceErrors", snmpWalkData.get(oid));
            }
        }
        return deviceMap;
    }

    private boolean checkHrDeviceDescrInfo(String oid) {
        return oid.startsWith("1.3.6.1.2.1.25.3.2.1.3") || oid.startsWith("1.3.6.1.2.1.25.3.2.1.5") || oid.startsWith("1.3.6.1.2.1.25.3.2.1.6");
    }

    public Map<String, Map<String, String>> getInterfaceMap(Map<String, String> snmpWalkData) {

        Map<String, String> interfaceInfoMap;

        for (String oid : snmpWalkData.keySet()) {

            if (!checkInterfaceInfo(oid)) {
                continue;
            }

            String id[] = oid.split("\\.");
            String intrfaceId = id[id.length - 1];

            if (!interfaceMap.containsKey(intrfaceId)) {
                interfaceInfoMap = new HashMap<>();
                interfaceMap.put(intrfaceId, interfaceInfoMap);
            } else {
                interfaceInfoMap = interfaceMap.get(intrfaceId);
            }

            if (oid.startsWith("1.3.6.1.2.1.2.2.1.2.")) {
                interfaceInfoMap.put("ifDescr", DateConvertHex.hexToAscii(snmpWalkData.get(oid)));
            } else if (oid.startsWith("1.3.6.1.2.1.2.2.1.3")) {
                interfaceInfoMap.put("ifType", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.2.2.1.4")) {
                interfaceInfoMap.put("ifMtu", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.2.2.1.5")) {
                interfaceInfoMap.put("ifSpeed", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.2.2.1.6")) {
                interfaceInfoMap.put("ifPhysAddress", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.2.2.1.7")) {

                String ifAdminStatus = snmpWalkData.get(oid);

                if (ifAdminStatus.equals("1"))
                    ifAdminStatus = "up";
                else if (ifAdminStatus.equals("2"))
                    ifAdminStatus = "down";
                else if (ifAdminStatus.equals("3"))
                    ifAdminStatus = "testing";

                interfaceInfoMap.put("ifAdminStatus", ifAdminStatus);

            } else if (oid.startsWith("1.3.6.1.2.1.2.2.1.8")) {

                String ifOperStatus = snmpWalkData.get(oid);

                if (ifOperStatus.equals("2"))
                    ifOperStatus = "down";
                else if (ifOperStatus.equals("6"))
                    ifOperStatus = "Not present";
                else if (ifOperStatus.equals("1"))
                    ifOperStatus = "up";

                interfaceInfoMap.put("ifOperStatus", ifOperStatus);

            } else if (oid.startsWith("1.3.6.1.2.1.2.2.1.9")) {
                interfaceInfoMap.put("ifLastChange", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.2.2.1.10")) {
                interfaceInfoMap.put("ifInOctets", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.2.2.1.16")) {
                interfaceInfoMap.put("ifOutOctets", snmpWalkData.get(oid));
            }

        }
        return interfaceMap;
    }

    private boolean checkInterfaceInfo(String oid) {
        return oid.startsWith("1.3.6.1.2.1.2.2.1.2.") || oid.startsWith("1.3.6.1.2.1.2.2.1.3") ||
                oid.startsWith("1.3.6.1.2.1.2.2.1.4") || oid.startsWith("1.3.6.1.2.1.2.2.1.5") ||
                oid.startsWith("1.3.6.1.2.1.2.2.1.6") || oid.startsWith("1.3.6.1.2.1.2.2.1.7") ||
                oid.startsWith("1.3.6.1.2.1.2.2.1.8") || oid.startsWith("1.3.6.1.2.1.2.2.1.9") ||
                oid.startsWith("1.3.6.1.2.1.2.2.1.10") || oid.startsWith("1.3.6.1.2.1.2.2.1.16");
    }

    public Map<String, String> getSystemMap(Map<String, String> snmpWalkData) {

        for (String oid : snmpWalkData.keySet()) {

            if (!checkSystemInfo(oid)) {
                continue;
            }

            if (oid.equals("1.3.6.1.2.1.1.1.0")) {
                systemMap.put("sysDescr", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.1.2.0")) {
                systemMap.put("sysObjectID", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.1.3.0")) {
                systemMap.put("sysUpTimeInstance", String.valueOf(DateConvertHex.durationToSeconds(snmpWalkData.get(oid))));
            } else if (oid.equals("1.3.6.1.2.1.1.4.0")) {
                systemMap.put("sysContact", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.1.5.0")) {
                systemMap.put("sysName", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.1.6.0")) {
                systemMap.put("sysLocation", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.1.7.0")) {
                systemMap.put("sysServices", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.2.1.0")) {
                systemMap.put("ifNumber", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.4.1.0")) {
                systemMap.put("ipForwarding", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.4.2.0")) {
                systemMap.put("ipDefaultTTL", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.4.3.0")) {
                systemMap.put("ipInReceives", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.4.8.0")) {
                systemMap.put("ipInDiscards", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.4.9.0")) {
                systemMap.put("ipInDelivers", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.4.10.0")) {
                systemMap.put("ipOutRequests", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.4.11.0")) {
                systemMap.put("ipOutDiscards", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.4.12.0")) {
                systemMap.put("ipOutNoRoutes", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.4.20.1.1") && (oid.equals("1.3.6.1.2.1.4.20.1.1.127.0.0.1") == false)) {
                systemMap.put("ipAdEntAddr", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.4.20.1.2") && (oid.equals("1.3.6.1.2.1.4.20.1.2.127.0.0.1") == false)) {
                systemMap.put("ipAdEntIfIndex", snmpWalkData.get(oid));
            } else if (oid.startsWith("1.3.6.1.2.1.4.20.1.3") && (oid.equals("1.3.6.1.2.1.4.20.1.3.127.0.0.1") == false)) {
                systemMap.put("ipAdEntNetMask", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.4.21.1.8.0.0.0.0 ")) {
                systemMap.put("ipRouteType", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.25.1.1.0")) {
                systemMap.put("hrSystemUptime", String.valueOf(DateConvertHex.durationToSeconds(snmpWalkData.get(oid))));
            } else if (oid.equals("1.3.6.1.2.1.25.1.2.0 ")) {
                systemMap.put("hrSystemDate", DateConvertHex.convertDecimal(snmpWalkData.get(oid)));
            } else if (oid.equals("1.3.6.1.2.1.25.1.5.0")) {
                systemMap.put("hrSystemNumUsers", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.25.1.6.0")) {
                systemMap.put("hrSystemProcesses", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.25.2.2.0")) {
                systemMap.put("hrMemorySize", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.25.3.5.1.1.1")) {
                systemMap.put("hrPrinterStatus", snmpWalkData.get(oid));
            } else if (oid.equals("1.3.6.1.2.1.25.6.1.0")) {
                systemMap.put("hrSWInstalledLastChange", String.valueOf(DateConvertHex.durationToSeconds(snmpWalkData.get(oid))));
            }
        }
        return systemMap;
    }


    public Map<String, Map<String, String>> traverseOid(Map<String, String> snmpWalkData, String choice) {

        if (choice.equalsIgnoreCase("ProcessMap")) {
            return getRunningProcessMap(snmpWalkData);
        } else if (choice.equalsIgnoreCase("SWInstalledMap")) {
            return getSWInstalledMap(snmpWalkData);
        } else if (choice.equalsIgnoreCase("storageMap")) {
            return getStorageMap(snmpWalkData);
        } else if (choice.equalsIgnoreCase("deviceMap")) {
            return getDeviceMap(snmpWalkData);
        } else if (choice.equalsIgnoreCase("interfaceMap")) {
            return getInterfaceMap(snmpWalkData);
        }
        return null;
    }

    private boolean checkSystemInfo(String oid) {
        return oid.startsWith("1.3.6.1.2.1.1.1.0") || oid.startsWith("1.3.6.1.2.1.1.2.0") ||
                oid.startsWith("1.3.6.1.2.1.1.3.0") || oid.startsWith("1.3.6.1.2.1.1.4.0") ||
                oid.startsWith("1.3.6.1.2.1.1.5.0") || oid.startsWith("1.3.6.1.2.1.1.6.0") ||
                oid.startsWith("1.3.6.1.2.1.1.7.0") || oid.startsWith("1.3.6.1.2.1.2.1.0") ||
                oid.startsWith("1.3.6.1.2.1.4.1.0") || oid.startsWith("1.3.6.1.2.1.4.2.0") ||
                oid.startsWith("1.3.6.1.2.1.4.3.0") || oid.startsWith("1.3.6.1.2.1.4.8.0") ||
                oid.startsWith("1.3.6.1.2.1.4.9.0") || oid.startsWith("1.3.6.1.2.1.4.10.0") ||
                oid.startsWith("1.3.6.1.2.1.4.11.0") || oid.startsWith("1.3.6.1.2.1.4.12.0") ||
                oid.startsWith("1.3.6.1.2.1.4.20.1.1") && (!oid.equals("1.3.6.1.2.1.4.20.1.1.127.0.0.1")) ||
                oid.startsWith("1.3.6.1.2.1.4.20.1.2") && (!oid.equals("1.3.6.1.2.1.4.20.1.2.127.0.0.1")) ||
                oid.startsWith("1.3.6.1.2.1.4.20.1.3") && (!oid.equals("1.3.6.1.2.1.4.20.1.3.127.0.0.1")) ||
                oid.startsWith("1.3.6.1.2.1.4.21.1.8.0.0.0.0") || oid.startsWith("1.3.6.1.2.1.25.1.1.0") ||
                oid.startsWith("1.3.6.1.2.1.25.1.2.0") || oid.startsWith("1.3.6.1.2.1.25.1.5.0") ||
                oid.startsWith("1.3.6.1.2.1.25.1.6.0") || oid.startsWith("1.3.6.1.2.1.25.2.2.0") ||
                oid.startsWith("1.3.6.1.2.1.25.3.5.1.1.1") || oid.startsWith("1.3.6.1.2.1.25.6.1.0");
    }

    public static void main(String[] args) throws IOException {
        SnmpUtility client = new SnmpUtility("127.0.0.1", "public");
        Map<String, String> snmpDataMap = client.snmpWalk(".");
        SnmpDataClassifier classifier = new SnmpDataClassifier();

        Map<String, Map<String, String>> snmpProcessData = classifier.getRunningProcessMap(snmpDataMap);
        for (String key : snmpProcessData.keySet()) {
//                      System.out.println(key + ":" + snmpProcessData.get(key));
        }
        Map<String, Map<String, String>> snmpSWData = classifier.getSWInstalledMap(snmpDataMap);
        for (String key : snmpSWData.keySet()) {
            //       System.out.println(key+ ":"+snmpSWData.get(key));
        }
        Map<String, Map<String, String>> snmpStorageData = classifier.getStorageMap(snmpDataMap);
        for (String key : snmpStorageData.keySet()) {
//                     System.out.println(key+ ":"+snmpStorageData.get(key));
        }
        Map<String, Map<String, String>> snmpDeviceData = classifier.getDeviceMap(snmpDataMap);
        for (String key : snmpDeviceData.keySet()) {
            //               System.out.println(key+ ":"+ snmpDeviceData.get(key));
        }
        Map<String, Map<String, String>> snmpInterfaceData = classifier.getInterfaceMap(snmpDataMap);
        for (String key : snmpInterfaceData.keySet()) {
            System.out.println(key + ":" + snmpInterfaceData.get(key));
        }
        Map<String, String> snmpSystemData = classifier.getSystemMap(snmpDataMap);
        for (String key : snmpSystemData.keySet()) {
//                      System.out.println(key + ":" + snmpSystemData.get(key));
        }

        Map<String, Map<String, String>> snmpData = classifier.traverseOid(snmpDataMap, "interfaceMap");
        for (String key : snmpData.keySet()) {
//            System.out.println(key + ":" + snmpData.get(key));
        }


        String mac = null;
        for (String oid : snmpDataMap.keySet()) {
            if (oid.startsWith("1.3.6.1.2.1.55.1.5.1.8.")) {
                mac = DateConvertHex.filterHex(snmpDataMap.get(oid));
                if (mac.length() == 12) {
                    System.out.println("mac = " + mac + "\n" + "oid = "+ oid);
                    break;
                }
            }
        }

    }
}
