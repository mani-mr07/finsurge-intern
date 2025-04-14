package com.quartzjob.quartzjobcretaion.service;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService  {


    @Autowired
    private  Scheduler scheduler;

//
//    public void triggerAndPrintJob() {
//    try {
//        // Trigger the durable job using its JobKey.
//        scheduler.triggerJob(JobKey.jobKey("printJob", "group1"));
//        System.out.println("JobService: Triggered quartz job");
//    } catch (SchedulerException e) {
//        e.printStackTrace();
//    }
//}


}
