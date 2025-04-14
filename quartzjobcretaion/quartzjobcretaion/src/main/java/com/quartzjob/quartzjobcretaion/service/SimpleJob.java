package com.quartzjob.quartzjobcretaion.service;

import com.quartzjob.quartzjobcretaion.Entity.Employee;
import com.quartzjob.quartzjobcretaion.Repository.EmployeeRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.core.env.Environment;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleJob implements Job {
    @Autowired
    private Environment environment;

    @Value("excel-path")
    private String values;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        List<Employee> employeeList = employeeRepository.getEmployeeWhereIdIsNull();
        if (employeeList.size() > 0) {
            String[] path = environment.getProperty("excel-path").split(" ");
            String resultantPath = "";
            for (int i = 0; i < path.length - 1; i++) {
                resultantPath += path[i] + "\\";
            }
            resultantPath += path[path.length - 1];
            System.out.println("Path " + resultantPath);
            Path resultantPath1 = Paths.get(resultantPath);

            if (!Files.isDirectory(resultantPath1)) {
                try {
                    Files.createDirectories(resultantPath1);
                    System.out.println("Directory is created");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Directory is already present");
            }
            LocalDateTime localDateTime = LocalDateTime.now();
            String fileName = "Employee_" + localDateTime.toString().replaceAll(":", "-") + ".xlsx";
            Path filePath = resultantPath1.resolve(fileName);

            Workbook workbook = null;
            boolean isNewFile = false;

            // If the file exists, load it; otherwise create a new workbook.
            if (Files.exists(filePath)) {
                try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
                    workbook = new XSSFWorkbook(fis);
                    System.out.println("Existing workbook loaded.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                workbook = new XSSFWorkbook();
                isNewFile = true;
                System.out.println("New workbook created.");
            }
            Sheet sheet;
            if (workbook.getNumberOfSheets() > 0) {
                sheet = workbook.getSheetAt(0);
            } else {
                sheet = workbook.createSheet("Employee");
            }

            try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                // Load and append new data to the sheet.
                loadData(sheet, workbook, fos,employeeList);

                // Write the workbook to the file.
                workbook.write(fos);
                System.out.println("Excel file updated successfully at " + filePath.toAbsolutePath());
            } catch (Exception e) {
                System.out.println("Error while writing the Excel file:");
                e.printStackTrace();
            }
//        JobExecutionLog log = new JobExecutionLog();
//        log.setJobName(context.getJobDetail().getKey().toString());
//        log.setExecutionTime(LocalDateTime.now());
//        log.setStatus("SUCCESS");  // or "FAILED" if an exception occurs
//
//
//        jobExecutionLogRepository.save(log);
        }
    }

    public void loadData(Sheet sheet, Workbook workbook,FileOutputStream fos,List<Employee>employeeList) throws IOException {
     Row headerRow;
     int lastRowIndex=0;
        if(sheet.getLastRowNum()>0){
            System.out.println("The row index is greater than 0");
            lastRowIndex=sheet.getLastRowNum();
        }
        else{
            System.out.println("Create a first row");
            headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Id");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Role");}
        int i = lastRowIndex+1;
        for (Employee employee : employeeList) {
            if(employee.getStatus()==null){
            Row row = sheet.createRow(i++);
                List<String>fields=employeeToList(employee);
                int k=0;
                for (String str:fields){
                    row.createCell(k++).setCellValue(str);
                }
            employee.setStatus("Done");
            employeeRepository.save(employee);}
        }
        System.out.println("the workbook is written");
    }
    public List<String> employeeToList(Employee employee) {
        List<String> values = new ArrayList<>();
        Field[] fields = employee.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);  // Allow access to private fields
            try {
                Object value = field.get(employee);
                values.add(value != null ? value.toString() : "");
            } catch (IllegalAccessException e) {

                values.add("");
            }
        }
        return values;
    }
}