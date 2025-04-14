package com.java.springbatch.Service;

import com.java.springbatch.Entity.Company;
import com.java.springbatch.Repository.CompanyRatingRepository;
import com.java.springbatch.Repository.CompanyRepository;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static java.lang.System.exit;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyRatingRepository companyRatingRepository;
    private static final Logger logger = LoggerFactory.getLogger(Service.class);

    public long currentCount;
    public long previousCount;
    public boolean paging(int pageNo,int pageSize,Sheet sheet,Workbook workbook) throws IllegalAccessException {
        LocalDateTime totalStartTime=LocalDateTime.now();
        int count=0;
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        Page<Company>companyPage=companyRepository.findAll(pageable);
        List<Company> companyList=companyPage.getContent();

        if (sheet.getPhysicalNumberOfRows() == 0) {
            Row headerRow = sheet.createRow(0);
            Class<Company> clazz = Company.class;
            int i=0;
            for (Field field : clazz.getDeclaredFields()){
                if (!isPrimitiveOrWrapper(field.getType()) || field.getName().equals("id")) {
                    String value=field.getName();
                    headerRow.createCell(i++).setCellValue(value);
                }
                else{
                    headerRow.createCell(i++).setCellValue(field.getName());
                }
            }
        }

           for(Company company:companyList) {
               Long value=company.getId();
               int i = 0;
               currentCount++;
               Row row = sheet.createRow(sheet.getLastRowNum() + 1);
               for (Field field : company.getClass().getDeclaredFields()) {
                   field.setAccessible(true);
                       if (!isPrimitiveOrWrapper(field.getType())) {
                           LocalDateTime startTime=LocalDateTime.now();
//                           for (Field subField : field.getType().getDeclaredFields()) {
//                               if (subField.getName().equals("review")) {
//                                   subField.setAccessible(true);
//                                   Object reviewValue = subField.get(value);
//                                   row.createCell(i++).setCellValue(reviewValue != null ? reviewValue.toString() : "NULL");
//                                   break;
//                               }
//                           }
                           row.createCell(i++).setCellValue(company.getCompanyRating().getReviews());
//                           Long ans= companyRatingRepository.findByCompany(value);
//                           row.createCell(i++).setCellValue(ans);
//                           LocalDateTime stopTime=LocalDateTime.now();
//                           Duration diff=Duration.between(stopTime,startTime);
//                           System.out.println(diff.toSeconds());
                           }
                   else {
                       field.setAccessible(true); // Allow access to private fields
                       try {
                           row.createCell(i++).setCellValue(field.get(company).toString());
                       } catch (IllegalAccessException e) {
                           System.out.println("Error accessing field: " + field.getName());
                       }
                   }

               }
           }
        LocalDateTime totalStopTime=LocalDateTime.now();
        Duration difference=Duration.between(totalStartTime,totalStopTime);
        System.out.println(difference.toSeconds());
        return true;
    }
    private static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive() ||
                type == String.class ||
                type == Integer.class ||
                type == Long.class ||
                type == Double.class ||
                type == Float.class ||
                type == Boolean.class ||
                type == Byte.class ||
                type == Short.class ||
                type == Character.class ||
                type == java.util.Date.class;
    }
    public ByteArrayInputStream pagingAndWriting() throws IllegalAccessException {

        Page<Company>companyPage=companyRepository.findAll(PageRequest.ofSize(500));
        int totalpageSize=companyPage.getTotalPages();
        int pageNo=0;
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Companies");
            while(paging(pageNo,20,sheet,workbook)){
                pageNo++;
                if(pageNo>totalpageSize){
                    break;
                }
            }
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            System.out.println("Error converting Excel to ByteArrayInputStream: " + e.getMessage());
            return null;
        }
    }
    @Scheduled(fixedRate = 60000)
    public void logInsertedRecords() {
        long insertedThisMinute = currentCount - previousCount;
        logger.info("After 1 minute, " + insertedThisMinute + " records inserted.");
        previousCount = currentCount;

    }
}
