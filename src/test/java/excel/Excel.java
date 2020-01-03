package excel;

import com.sun.xml.bind.v2.runtime.reflect.opt.FieldAccessor_Long;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Excel {

    @Test
    public void openExcel () throws IOException {
        File file=new File("G:/try.xlsx");
        FileInputStream fis=new FileInputStream(file);
        HSSFWorkbook workbook=new HSSFWorkbook(fis);
    }
}
