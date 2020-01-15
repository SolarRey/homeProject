package excel;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Excel {

    @Test
    public void openExcel () throws IOException {
        File file=new File("G:/try.xlsx");
        FileInputStream fis=new FileInputStream(file);
        XSSFWorkbook workbook=new XSSFWorkbook(fis);
        ArrayList <String> list  =new ArrayList<>();
        for (int i=0;i<workbook.getSheetAt(0).getPhysicalNumberOfRows();i++) {
            list.add(workbook.getSheetAt(0).getRow(i).getCell(0).getStringCellValue());
        }
    }
}
