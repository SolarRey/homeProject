package parallel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataproviderAndExcel {

    @DataProvider(name = "qwerty",parallel = true)
    public Iterator<Object[]> createDataforTestExcel() throws IOException {
        File file=new File("G:/try.xlsx");
        FileInputStream fis=new FileInputStream(file);
        XSSFWorkbook workbook=new XSSFWorkbook(fis);
        XSSFSheet sheet=workbook.getSheetAt(0);
        List<Object []> list=new ArrayList<>();
        for (int i=0;i<sheet.getPhysicalNumberOfRows();i++){
            list.add(new Object[] {sheet.getRow(i).getCell(0).getStringCellValue()});
        }
        return list.iterator();
    }



    @Test(dataProvider = "qwerty")
    public void soutDataForRequest(String request) {
        System.out.println(request);
    }


}
