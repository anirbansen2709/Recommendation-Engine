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

import net.sf.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Map;


/**
 * The Class JSONWrapper.
 */
public class JSONWrapper {

    /**
     * Gets the json.
     *
     * @param object the object
     * @return the json
     */
    public static JSONObject getJson(Object object){
        JSONObject obj = new JSONObject();
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field :  fields){
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                    obj.put(field.getName(), value == null ? "null": value);
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }


    /**
     * Gets the json.
     *
     * @param map the map
     * @return the json
     */
    public static JSONObject getJson(Map<String,?> map){
        JSONObject obj = new JSONObject();
        for(Map.Entry<String,?> entry : map.entrySet()){
            obj.put(entry.getKey(),entry.getValue());
        }
        return obj;
    }

    public static Object getJSON(String name, Object value) {
        JSONObject obj = new JSONObject();
        obj.put(name,value);
        return obj;
    }
}
