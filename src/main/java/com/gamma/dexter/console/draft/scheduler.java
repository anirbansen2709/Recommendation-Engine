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
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class scheduler
{
    public static void main( String[] args ) throws Exception
    {

        JobDetail job = JobBuilder.newJob(JobSubmitDevice.class)
                .withIdentity("inputMapToDb", "group1").build();



        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("Trigger", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 0/2 * * * ?"))
                .build();

        //schedule it
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }
}
