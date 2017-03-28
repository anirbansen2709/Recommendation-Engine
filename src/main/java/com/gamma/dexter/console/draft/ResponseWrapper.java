/*
 * **********************************************************************************************
 *  Copyright (C) 2015 Gamma Analytics, Inc. All rights reserved.                               *
 *  http://www.gammanalytics.com/                                                               *
 *  --------------------------------------------------------------------------------------------*
 *  The software in this package is published under the terms of the EUL (End User license)     *
 *  agreement a copy of which has been included with this distribution in the license.txt file. *
 * **********************************************************************************************
 */
package com.gamma.dexter.console.draft;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;


/**
 * The Class ResponseWrapper.
 */
public class ResponseWrapper {

    /** The Constant CODE_SUCCESS. */
    public final static String CODE_SUCCESS = "200";
    
    /** The Constant CODE_APP_ERROR. */
    public final static String CODE_APP_ERROR = "422";

    /** The json. */
    JSONObject json = new JSONObject();
    
    /** The array. */
    JSONArray array = new JSONArray();

    /** The message. */
    String returnCode = null, message = null;

    /**
     * Instantiates a new response wrapper.
     */
    public ResponseWrapper() {}

    /**
     * Adds the payload.
     *
     * @param obj the obj
     */
    public void addPayload(JSONObject obj) {
        array.add(obj);
    }

    /**
     * Adds the payload.
     *
     * @param map the map
     */
    public void addPayload(Map<String, ?> map) {
        array.add(JSONWrapper.getJson(map));
    }

    /**
     * Adds the payload.
     *
     * @param list the list
     */
    public void addPayload(List<?> list) {
        for(Object o : list){
            array.add(JSONWrapper.getJson(o));
        }
    }

    public void addPayload(String name, Object value){
        array.add(JSONWrapper.getJSON(name, value));
    }


    /**
     * Adds the payload.
     *
     * @param o the o
     */
    public void addPayload(Object o) {
        array.add(JSONWrapper.getJson(o));
    }

    /**
     * Sets the success.
     *
     * @param success the new success
     */

    public void setSuccess(boolean success) {
        if (success) {
            returnCode = CODE_SUCCESS;
        } else {
            returnCode = CODE_APP_ERROR;
        }
    }

    /**
     * Sets the return code.
     *
     * @param returnCode the new return code
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the response.
     *
     * @return the response
     */
    public String getResponse() {

        if (returnCode == null) {
            returnCode = CODE_SUCCESS;
        }

        if (message == null) {
            message = "";
        }

        json.put("returnCode", returnCode);
        json.put("message", message);
        json.put("Payload", array);

        return json.toString();
    }
}