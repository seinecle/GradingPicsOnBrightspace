/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.gradingpics.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author LEVALLOIS
 */
public class Excel {

    public void writeFGradeForOneStudent(String name, String grade) throws FileNotFoundException, IOException {
        XSSFWorkbook workbook;
        FileInputStream file = null;
        if (Files.exists(Paths.get("grades_2018.xlsx"))) {
            file = new FileInputStream(new File("grades_2018.xlsx"));
            workbook = new XSSFWorkbook(file);
        } else {
            workbook = new XSSFWorkbook();
        }
        while (workbook.getNumberOfSheets() < 4) {
            workbook.createSheet(String.valueOf(String.valueOf(workbook.getNumberOfSheets() + 1)));
        }
        XSSFSheet sheet = workbook.getSheetAt(1);
        int lastRowNumber = sheet.getLastRowNum();
//Create a new row in current sheet
        XSSFRow row = sheet.createRow(lastRowNumber + 1);
//Create a new cell in current row
        XSSFCell cellName = row.createCell(0);
//Set value to new value
        cellName.setCellValue(name);
        XSSFCell cellGrade = row.createCell(1);
        cellGrade.setCellValue(grade);

//close the excel file when done        
        if (file != null) {
            file.close();
        }
        FileOutputStream fos = new FileOutputStream(new File("grades_2018.xlsx"));
        workbook.write(fos);
        fos.close();
    }

}
