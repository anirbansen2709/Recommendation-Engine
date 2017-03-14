/*
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
 * http://www.gammanalytics.com/
 * --------------------------------------------------------------------------------------------
 * The software in this package is published under the terms of the EUL (End User license)
 * agreement a copy of which has been included with this distribution in the license.txt file.
 */

package com.gamma.dexter.console.draft;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    /**
     * Welcome.
     *
     * @return the string
     */
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String welcome() {
        return "deviceManagement";
    }

//    @RequestMapping(value = "settings", method = RequestMethod.GET)
//    public String settings(){
//        return "settings";
//    }

    @RequestMapping(value = "administration", method = RequestMethod.GET)
    public String administration(@RequestParam String type){
        return type;
    }

    @RequestMapping(value = "Tests", method = RequestMethod.GET)
    public String Tests(){
        return "Tests";
    }

//    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
//    public String dashboard(){
//        return "dashboard";
//    }

    @RequestMapping(value = "deviceManagement", method = RequestMethod.GET)
    public String deviceManagement(){
        return "deviceManagement";
    }

    @RequestMapping(value = "documentation", method = RequestMethod.GET)
    public String documentation(){
        return "documentation";
    }

   @RequestMapping(value = "SnmpTable", method = RequestMethod.GET)
    public String SnmpTable(){
        return "SnmpTable";
    }


    @RequestMapping(value = "testDeviceManagement", method = RequestMethod.GET)
    private String testDeviceManagement(){
        return "testDeviceManagement";
    }


    @RequestMapping(value = "insertUser", method = RequestMethod.GET)
    private String insertUser(){
        return "insertUser";
    }


    @RequestMapping(value = "SNMP_UI", method = RequestMethod.GET)
    public String SNMP_UI(){
        return "SNMP_UI";
    }


    @RequestMapping(value = "topCharts", method = RequestMethod.GET)
    private String topCharts(){
        return "topCharts";
    }


    @RequestMapping(value = "rateSongs", method = RequestMethod.GET)
    private String rateSongs(){
        return "rateSongs";
    }

    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    private String dashboard(){
        return "dashboard";
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    private String test(){
        return "test";
    }

}

