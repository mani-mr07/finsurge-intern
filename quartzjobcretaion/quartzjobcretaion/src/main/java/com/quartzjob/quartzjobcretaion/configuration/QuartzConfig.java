package com.quartzjob.quartzjobcretaion.configuration;

import com.quartzjob.quartzjobcretaion.service.SimpleJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        // Define job detail specifying the job's class and identity
        return JobBuilder.newJob(SimpleJob.class)
                .withIdentity("printJob", "group1")
                .storeDurably()  // The job is durable even if there is no trigger
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("simpleJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 * * * ? "))
                .build();
    }
}