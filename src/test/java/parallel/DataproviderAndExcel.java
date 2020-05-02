package parallel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
    private XSSFSheet sheetIn;
    private Sheet sheetOut;
    private FileOutputStream fos;

    @BeforeClass
    public void createExcel() throws IOException {
        File file = new File("G:/excel/try.xlsx");
        FileInputStream fis = new FileInputStream(file);
        workbookFis = new XSSFWorkbook(fis);

        File fileOut = new File("G:/excel/tryOut.xlsx");
        fos = new FileOutputStream(fileOut);
        workbookFos = new XSSFWorkbook();
        sheetOut = workbookFos.createSheet("1");

    }


    @DataProvider(name = "qwerty", parallel = true)
    public Iterator<Object[]> createDataforTestExcel() throws IOException {
        XSSFSheet sheet = workbookFis.getSheetAt(0);
        Map<Integer, Row> map = new HashMap<>();
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            map.put(i, sheet.getRow(i));
        }
        workbookFis.close();
        List<Object[]> list = new ArrayList<Object[]>();
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            list.add(new Object[]{map.get(i)});
        }
        return list.iterator();
    }


    @Test(dataProvider = "qwerty")
    public synchronized void soutDataForRequest(Row map) throws IOException, InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(1025);
        long estimatedTime = System.currentTimeMillis() - startTime;
        sheetOut.createRow(map.getRowNum()).createCell(0).setCellValue(map.getCell(0).getStringCellValue());
        sheetOut.getRow(map.getRowNum()).createCell(1).setCellValue(estimatedTime);
    }

    @AfterClass
    public void closeExcel() throws IOException {
        workbookFos.write(fos);
    }
}
