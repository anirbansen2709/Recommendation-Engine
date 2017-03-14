/*
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
 * http://www.gammanalytics.com/
 * --------------------------------------------------------------------------------------------
 * The software in this package is published under the terms of the EUL (End User license)
 * agreement a copy of which has been included with this distribution in the license.txt file.
 */

package com.gamma.dexter.console.draft;

/**
 * Created by lenovo on 2/25/2016.
 */

import com.gamma.dexter.console.draft.threadManager.ThreadPool;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class JobSubmitDevice implements Job {

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date).toString();
        System.out.println("job started at " + time);
        ThreadPool threadPool = new ThreadPool();

        try {

            MapToSqlDb db = new MapToSqlDb();
            Map<String, String> getDevices = db.getAllDevices();

            Iterator it = getDevices.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry ipPair = (Map.Entry) it.next();
                String ip = (String) ipPair.getValue();

                Map.Entry communityPair = (Map.Entry) it.next();
                String community = (String) communityPair.getValue();

                Map.Entry macPair = (Map.Entry) it.next();

                threadPool.startNotificationThread(ip,community);
            }
        } catch (Exception e) {
            System.out.println(e + "@ in the JobSubmitDevice.java");
        }
    }

}


