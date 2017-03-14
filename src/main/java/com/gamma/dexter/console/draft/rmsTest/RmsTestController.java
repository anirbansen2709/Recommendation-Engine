/*
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
 * http://www.gammanalytics.com/
 * --------------------------------------------------------------------------------------------
 * The software in this package is published under the terms of the EUL (End User license)
 * agreement a copy of which has been included with this distribution in the license.txt file.
 */

package com.gamma.dexter.console.draft.rmsTest;

import com.gamma.dexter.console.draft.ResponseWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class RmsTestController {

    @RequestMapping(value = "integration/refugeeApp/updateDeviceData", method = RequestMethod.POST)
    public @ResponseBody
    String registerRefugee(@RequestHeader(value="imei") String imei,
                           @RequestBody Map<String, Map<String,String>> mRefugeeDeviceDataWrapper){

        ResponseWrapper wrapper = new ResponseWrapper();
        try{
            for (String recordTime : mRefugeeDeviceDataWrapper.keySet()) {

                for (String paramKey  : mRefugeeDeviceDataWrapper.get(recordTime).keySet()) {

                    MRefugeeDeviceData mRefugeeDeviceData = new MRefugeeDeviceData();
                    mRefugeeDeviceData.setRecordTime(recordTime);
                    mRefugeeDeviceData.setParamKey(paramKey);
                    mRefugeeDeviceData.setParamValue(mRefugeeDeviceDataWrapper.get(recordTime).get(paramKey));

                    wrapper.addPayload(mRefugeeDeviceData);
                }
            }
            wrapper.setSuccess(true);
        }catch (Exception e){
            wrapper.setSuccess(false);
            wrapper.setMessage(e.getMessage());
        }
        System.out.println("Rms Data Received "+wrapper.getResponse());
        return wrapper.getResponse();
    }

}

