package parallel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

public class DataproviderAndExcel {
    int q = 0, i = 0;
    private XSSFWorkbook workbookFis;
    private Workbook workbookFos;
    private XSSFSheet sheet;

    @BeforeClass
    public void createExcel() throws IOException {
        File file = new File("G:/excel/try.xlsx");
        FileInputStream fis = new FileInputStream(file);
        workbookFis = new XSSFWorkbook(fis);

        File fileOut = new File("G:/excel/tryOut.xlsx");
        FileOutputStream fos = new FileOutputStream(fileOut);
        workbookFos = new HSSFWorkbook();
        workbookFos.createSheet("123");
    }


    @DataProvider(name = "qwerty", parallel = false)
    public Iterator<Object[]> createDataforTestExcel() throws IOException {
        XSSFSheet sheet = workbookFis.getSheetAt(0);
        Map<Integer, Row> map = new HashMap<>();
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            map.put(i, sheet.getRow(i));
        }
        List<Object[]> list = new ArrayList<Object[]>();
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            list.add(new Object[]{map.get(i)});
        }
        return list.iterator();
    }


    @Test(dataProvider = "qwerty")
//    @Test
    public void soutDataForRequest(Row map) throws IOException {
        System.out.println(map.getCell(0));
    }


    @DataProvider(name = "Passing List Of Maps", parallel = true)
    public Iterator<Object[]> createDataforTest3() {
        XSSFSheet sheet = workbookFis.getSheetAt(0);
        Map<Integer, Row> map = new HashMap<>();
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            map.put(i, sheet.getRow(i));
        }
        List<Object[]> list = new ArrayList<Object[]>();
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            list.add(new Object[]{map.get(i)});
        }
        return list.iterator();

    }

    @Test(dataProvider = "Passing List Of Maps")
    public synchronized void test1(Row map) {

        System.out.println(map.getCell(0));
    }


}
