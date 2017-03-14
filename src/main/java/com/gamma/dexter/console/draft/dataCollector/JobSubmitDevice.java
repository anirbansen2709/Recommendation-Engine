/*
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
 * http://www.gammanalytics.com/
 * --------------------------------------------------------------------------------------------
 * The software in this package is published under the terms of the EUL (End User license)
 * agreement a copy of which has been included with this distribution in the license.txt file.
 */

package com.gamma.dexter.console.draft.dataCollector;

/**
 * Created by lenovo on 2/25/2016.
 */

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;

public class JobSubmitDevice implements Job {
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        SnmpDataCollector client = null;
        try {
            client = new SnmpDataCollector("127.0.0.1", "public");
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.performRawSnmpDataDump();

    }

}


