package com.java.springbatch.config;

import com.java.springbatch.Entity.Student;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class ExcelItemReader implements ItemReader<Student>, ItemWriter<Student> {

    private Iterator<Row> rowIterator;

    public ExcelItemReader() {
    }

    public ExcelItemReader(String filePath) throws Exception {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("The file is loaded successfully");
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        this.rowIterator = sheet.iterator();
        this.rowIterator.next();
    }

    @Override
    public Student read() {
        if (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Student student = new Student();
            student.setId((long) row.getCell(0).getNumericCellValue());
            student.setName(row.getCell(1).getStringCellValue());
            student.setEmail(row.getCell(2).getStringCellValue());
            student.setAge((int) row.getCell(3).getNumericCellValue());
            student.setAddress(row.getCell(4).getStringCellValue());
            return student;
        }
        return null;
    }

    @Override
    public void write(Chunk<? extends Student> students) throws Exception {

        String FILE_PATH = "src/main/resources/new_students.xlsx";
        File file = new File(FILE_PATH);
        Workbook workbook;
        Sheet sheet;
        Set<Long> existingIds = new HashSet<>();

        try {
            if (file.exists() && file.length() > 0) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    workbook = new XSSFWorkbook(fis);
                    sheet = workbook.getSheetAt(0);

                    for (Row row : sheet) {
                        Cell idCell = row.getCell(0);
                        if (idCell != null && idCell.getCellType() == CellType.NUMERIC) {
                            existingIds.add((long) idCell.getNumericCellValue());
                        }
                    }
                }
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Students");
            }
        } catch (IOException | POIXMLException e) {
            System.err.println(" Corrupt Excel file detected. Deleting and recreating...");
            file.delete();
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Students");
        }

        if (sheet.getPhysicalNumberOfRows() == 0) {
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Name", "Email", "Age", "Address"};
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }
        }


        int lastRowNum = sheet.getLastRowNum();
        for (Student student : students) {
            if (!existingIds.contains(student.getId())) {
                lastRowNum++;
                Row row = sheet.createRow(lastRowNum);
                row.createCell(0).setCellValue(student.getId());
                row.createCell(1).setCellValue(student.getName());
                row.createCell(2).setCellValue(student.getEmail());
                row.createCell(3).setCellValue(student.getAge());
                row.createCell(4).setCellValue(student.getAddress());
                existingIds.add(student.getId());
            }
        }

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            workbook.write(fos);
            System.out.println(" Excel file updated successfully at " + FILE_PATH);
        } catch (IOException e) {
            System.err.println(" Error writing to Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                System.err.println(" Error closing workbook: " + e.getMessage());
            }
        }

    }
}
