package com.gamma.dexter.console.draft;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Rushil Mahaan on 11-03-2016.
 */
@Controller
public class dashboard {

/* JSONObject uptime = db.upTimeAnalysis();
        JSONObject freeSize = db.freeStorageAnalysis();
        JSONObject totSize = db.storageSizeAnalysis();
        JSONObject downDytes = db.downloadAnalysis();
        JSONObject upBytes = db.uploadAnalysis();
        JSONObject externeviceFrequency = db.externalDeviceFrequencyAnalysis();
*/

    @RequestMapping("/Upload") public
    @ResponseBody
    String Upload() {
        try {
            Charts db = new Charts();
            JSONObject upBytes = db.uploadAnalysis();
//            System.out.println("@c upbytes  "+upBytes);
            return upBytes.toString();
        } catch (Exception e) {
            return "Invalid";
        }
    }


    @RequestMapping("/Download") public
    @ResponseBody
    String Download() {
        try {
            Charts db = new Charts();
            JSONObject downbytes = db.downloadAnalysis();
//            System.out.println("@c downbytes  "+downbytes);
            return downbytes.toString();
        } catch (Exception e) {
            return "Invalid";
        }
    }

    @RequestMapping("/uptime") public
    @ResponseBody
    String uptime() {
        try {
            Charts db = new Charts();
            JSONObject uptime = db.upTimeAnalysis();
//            System.out.println(uptime);
            return uptime.toString();
        } catch (Exception e) {
            return "Invalid";
        }
    }

    @RequestMapping("/externalDeviceFrequency") public
    @ResponseBody
    String externalDeviceFrequency() {
        try {
            Charts db = new Charts();
            JSONObject externaldevicefrequency = db.externalDeviceFrequencyAnalysis(20);
//            System.out.println(externaldevicefrequency);
            return externaldevicefrequency.toString();
        } catch (Exception e) {
            return "Invalid";
        }
    }


    @RequestMapping("/totalSize") public
    @ResponseBody
    String totalSize() {
        try {
            Charts db = new Charts();
            JSONObject totalsize = db.storageSizeAnalysis();
//            System.out.println(totalsize);
            return totalsize.toString();
        } catch (Exception e) {
            return "Invalid";
        }
    }


    @RequestMapping("/freeSize") public
    @ResponseBody
    String freeSize() {
        try {
            Charts db = new Charts();
            JSONObject freesize = db.freeStorageAnalysis();
//            System.out.println(totalsize);
            return freesize.toString();
        } catch (Exception e) {
            return "Invalid";
        }
    }
 @RequestMapping("/avgRamAnalysis") public
    @ResponseBody
    String avgRamAnalysis() {
        try {
            Charts db = new Charts();
            JSONObject avgramanalysis = db.avgRamAnalysis();
            return avgramanalysis.toString();
        } catch (Exception e) {
            return "Invalid";
        }
    }
 @RequestMapping("msUpDownLoadAnalysis") public
    @ResponseBody
    String msUpDownLoadAnalysis() {
        try {
            Charts db = new Charts();
//            JSONObject msupdownloadanalysis = JSONObject.fromObject(db.msUpDownLoadAnalysis());
            JSONArray msupdownloadanalysis = db.msUpDownLoadAnalysis();
            System.out.println("ms "+msupdownloadanalysis.toString());
            return msupdownloadanalysis.toString();
        } catch (Exception e) {
            return "Invalid MS chart";
        }
    }
}
