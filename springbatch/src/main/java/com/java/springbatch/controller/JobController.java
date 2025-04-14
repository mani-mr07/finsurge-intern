package com.java.springbatch.controller;

import com.java.springbatch.Service.Service;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("importStudent")
    private Job importStudent;
    @Autowired
    @Qualifier("writeToExcelJob")
    private Job writeToExcelJob;
    @Autowired
    private Service service;

    @PostMapping
    public void csvToDBJob(){
        JobParameters jobParameters=new JobParametersBuilder()
                .addLong("startAt",System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(importStudent,jobParameters);
            System.out.println(importStudent.getName());
        } catch (JobExecutionAlreadyRunningException |JobRestartException |JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
//            throw new RuntimeException(e);
              e.printStackTrace();
        }
    }
    @GetMapping("/import")
    public String importStudentsJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis()) // Unique run
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(writeToExcelJob, jobParameters);
            System.out.println(writeToExcelJob.getName());
            return "Import Job Started. Status: " + execution.getStatus();
        } catch (Exception e) {
            return "Error starting Import Job: " + e.getMessage();
        }
    }
    @GetMapping("/paging")
    public ResponseEntity<ByteArrayResource> exportToExcel() throws IllegalAccessException {
        LocalDateTime startTime=LocalDateTime.now();
        ByteArrayInputStream byteArrayInputStream=service.pagingAndWriting();
        LocalDateTime stopTime=LocalDateTime.now();
        Duration diff=Duration.between(startTime,stopTime);
        System.out.println("Seconds "+diff.toSeconds());
        System.out.println("Minutes "+diff.toMinutes());
        System.out.println("Hours "+diff.toHours());
        ByteArrayResource resource = new ByteArrayResource(byteArrayInputStream.readAllBytes());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Company_details.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
