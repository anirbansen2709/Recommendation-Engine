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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Rushil Mahaan on 03-03-2016.
 */
@Controller
public class TestIndexControler {
    String startTimeGlobal;
    String endTimeGlobal;
    String processNameGlobal;
    Double LimitGlobal;
    Double TimeLimitGlobal;

    @RequestMapping(value = "externalDevicesTest", method = RequestMethod.GET)
    public String externalDevicesTest() {
        return "externalDevicesTest";
    }

    @RequestMapping(value = "performanceTest", method = RequestMethod.GET)
    public String performanceTest() {
        return "performanceTest";
    }

    @RequestMapping(value = "dataUsage", method = RequestMethod.GET)
    public String dataUsage() {
        return "dataUsage";
    }

    @RequestMapping(value = "otherTest", method = RequestMethod.GET)
    public String otherTest() {
        return "otherTest";
    }

    @RequestMapping("/submitFrequencyDuration")
    public
    @ResponseBody
    String submitTimeLeft(@RequestParam(value = "frequency") int frequency,
                          @RequestParam(value = "duration") String duration,
                          @RequestParam(value = "deviceId") String device) {
        startTimeGlobal = DateConvertHex.hhmmss(frequency, duration);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        endTimeGlobal = dateFormat.format(date);
        System.out.println(startTimeGlobal + "--" + endTimeGlobal + "--" + device);

        String results = getTestResult(device);
        return results;
    }

    @RequestMapping("/submitStartDateEndDate")
    public
    @ResponseBody
    String submitTimeRight(@RequestParam(value = "startTime") String startTime,
                           @RequestParam(value = "endTime") String endTime,
                           @RequestParam(value = "deviceId") String device) {

        startTimeGlobal = DateConvertHex.dateFormatConverter(startTime);
        endTimeGlobal = DateConvertHex.dateFormatConverter(endTime);

        System.out.println(startTimeGlobal + "--" + endTimeGlobal + "--" + device);

        String results = getTestResult(device);
        return results;
    }

    @RequestMapping("/submitLim")
    public
    @ResponseBody
    String submitLim(@RequestParam(value = "Data") String data,
                     @RequestParam(value = "bytes") String bytes,
                     @RequestParam(value = "deviceId") String device) {

        Double dataSize = Double.parseDouble(data);
        String results = null;

        if (bytes.equalsIgnoreCase("KB")) {
            LimitGlobal = dataSize * 1024.0;
        } else if (bytes.equalsIgnoreCase("MB")) {
            LimitGlobal = dataSize * 1024.0 * 1024.0;
        } else if (bytes.equalsIgnoreCase("GB")) {
            LimitGlobal = dataSize * 1024.0 * 1024.0 * 1024.0;
        }

        System.out.println("Data Size(bytes) " + LimitGlobal);
        if (!device.equals("driveSpace")||!device.equals("upload")||!device.equals("download")) {
             results = getTestResult(device);
        }
        return results;
    }
//   ajax call by model for both limit and time duration
    @RequestMapping("/submitDataLimitAndDuration")
    public
    @ResponseBody
    String submitDataLimitAndDuration(@RequestParam(value = "Data") String data,
                     @RequestParam(value = "bytes") String bytes,
                     @RequestParam(value = "deviceId") String device,@RequestParam(value = "frequency") int frequency,
                               @RequestParam(value = "duration") String duration) {

        Double dataSize = Double.parseDouble(data);
        String results = null;

        if (bytes.equalsIgnoreCase("KB")) {
            LimitGlobal = dataSize * 1024.0;
        }
        else if (bytes.equalsIgnoreCase("MB")) {
            LimitGlobal = dataSize * 1024.0 * 1024.0;
        }
        else if (bytes.equalsIgnoreCase("GB")) {
            LimitGlobal = dataSize * 1024.0 * 1024.0 * 1024.0;
        }

        startTimeGlobal = DateConvertHex.hhmmss(frequency, duration);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        endTimeGlobal = dateFormat.format(date);
        System.out.println(startTimeGlobal + "--" + endTimeGlobal + "--" + device);

        results = getTestResult(device);
        return results;
    }
    @RequestMapping("/submitTimeAndDuration")
    public
    @ResponseBody
    String submitTimeAndDuration(@RequestParam(value = "Data") String data,
                     @RequestParam(value = "bytes") String bytes,
                     @RequestParam(value = "deviceId") String device,@RequestParam(value = "frequency") int frequency,
                               @RequestParam(value = "duration") String duration) {

        Double dataSize = Double.parseDouble(data);
        String results = null;


        if (bytes.equalsIgnoreCase("mins")) {
            TimeLimitGlobal = dataSize * 60.0;
        }
        else if (bytes.equalsIgnoreCase("hours")) {
            TimeLimitGlobal = dataSize * 60.0 * 60.0;
        }
        else if (bytes.equalsIgnoreCase("days")) {
            TimeLimitGlobal = dataSize * 60.0 * 60.0 * 24;
        }
        else if (bytes.equalsIgnoreCase("weeks")) {
            TimeLimitGlobal = dataSize * 60.0 * 60.0 * 24 * 7;
        }
        else if (bytes.equalsIgnoreCase("months")) {
            TimeLimitGlobal = dataSize * 60.0 * 60.0 * 24 * 7 * 30;
        }


        startTimeGlobal = DateConvertHex.hhmmss(frequency, duration);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        endTimeGlobal = dateFormat.format(date);
        System.out.println(startTimeGlobal + "--" + endTimeGlobal + "--" + device);
        results = getTestResult(device);
        return results;
    }
    @RequestMapping("/submitProcess")
    public
    @ResponseBody
    String submitProcess(@RequestParam(value = "processName") String data){
        processNameGlobal = data;
        System.out.println("process name: " + processNameGlobal);
        return data;
    }

    @RequestMapping("/submitTimeLimit")
    public
    @ResponseBody
    String submitTimeLimit(@RequestParam(value = "Data") String data,
                     @RequestParam(value = "bytes") String bytes) {
        Double dataSize = Double.parseDouble(data);
        String results = null;

        if (bytes.equalsIgnoreCase("mins")) {
            TimeLimitGlobal = dataSize * 60.0;
        }
        else if (bytes.equalsIgnoreCase("hours")) {
            TimeLimitGlobal = dataSize * 60.0 * 60.0;
        }
        else if (bytes.equalsIgnoreCase("days")) {
            TimeLimitGlobal = dataSize * 60.0 * 60.0 * 24;
        }
        else if (bytes.equalsIgnoreCase("weeks")) {
            TimeLimitGlobal = dataSize * 60.0 * 60.0 * 24 * 7;
        }
        else if (bytes.equalsIgnoreCase("months")) {
            TimeLimitGlobal = dataSize * 60.0 * 60.0 * 24 * 7 * 30;
        }

        System.out.println("Time(seconds): " + TimeLimitGlobal);
        return results;
    }

    public String getTestResult(String testName) {
        Tests getResults = new Tests();
        JSONObject json = null;

        if (testName.equals("pendrive")) {
            Map<String, List<String>> TestResults = getResults.pendriveTest(startTimeGlobal, endTimeGlobal);
            TestResults = getResults.addSystemDetailsToTestResults(TestResults);
            json = new JSONObject();
            json.putAll(TestResults);
            System.out.println("@testIndexController " + json.toString());
        }
        else if (testName.equals("printer")) {
            Map<String, List<String>> TestResults = getResults.printerTest(startTimeGlobal, endTimeGlobal);
            TestResults = getResults.addSystemDetailsToTestResults(TestResults);
            json = new JSONObject();
            json.putAll(TestResults);
            System.out.println("@testIndexController " + json.toString());
        }
        else if (testName.equals("ram")) {
            Map<String, List<String>> TestResults = getResults.RAMTest(LimitGlobal);
            TestResults = getResults.addSystemDetailsToTestResults(TestResults);
            json = new JSONObject();
            json.putAll(TestResults);
            System.out.println("@testIndexController " + json.toString());
        }
        else if (testName.equals("driveSpace")) {
            Map<String, List<String>> TestResults = getResults.driveSpaceTest(startTimeGlobal, endTimeGlobal, LimitGlobal);
            TestResults = getResults.addSystemDetailsToTestResults(TestResults);
            json = new JSONObject();
            json.putAll(TestResults);
            System.out.println("@testIndexController " + json.toString());
        }
        else if (testName.equals("upload")) {
            Map<String, List<String>> TestResults = getResults.upLoadTest(startTimeGlobal, endTimeGlobal, LimitGlobal);
            TestResults = getResults.addSystemDetailsToTestResults(TestResults);
            json = new JSONObject();
            json.putAll(TestResults);
            System.out.println("@testIndexController " + json.toString());
        }
        else if (testName.equals("download")) {
            Map<String, List<String>> TestResults = getResults.downloadTest(startTimeGlobal, endTimeGlobal, LimitGlobal);
            TestResults = getResults.addSystemDetailsToTestResults(TestResults);
            json = new JSONObject();
            json.putAll(TestResults);
            System.out.println("@testIndexController " + json.toString());
        }
        else if (testName.equals("systemUpTime")) {
            Map<String, List<String>> TestResults = getResults.sysUpTimeTest(startTimeGlobal, endTimeGlobal, TimeLimitGlobal);
            TestResults = getResults.addSystemDetailsToTestResults(TestResults);
            json = new JSONObject();
            json.putAll(TestResults);
            System.out.println("@testIndexController " + json.toString());
        }
        else if (testName.equals("process")) {
            Map<String, List<String>> TestResults = getResults.processTest(startTimeGlobal, endTimeGlobal, processNameGlobal);
            TestResults = getResults.addSystemDetailsToTestResults(TestResults);
            json = new JSONObject();
            json.putAll(TestResults);
            System.out.println("@testIndexController " + json.toString());
        }
        else if (testName.equals("softwareUpdate")) {
            Map<String, List<String>> TestResults = getResults.softwareUpdateTest(startTimeGlobal, endTimeGlobal);
            TestResults = getResults.addSystemDetailsToTestResults(TestResults);
            json = new JSONObject();
            json.putAll(TestResults);
            System.out.println("@testIndexController " + json.toString());
        }
        else if (testName.equals("duplicateIp")) {
            Map<String, List<String>> TestResults = getResults.duplicateSystemTest(startTimeGlobal, endTimeGlobal);
            TestResults = getResults.addSystemDetailsToTestResults(TestResults);
            json = new JSONObject();
            json.putAll(TestResults);
            System.out.println("@testIndexController " + json.toString());
        }

        return json.toString();
    }


}
