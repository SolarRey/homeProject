import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class asd {

    int q = -1;

    public static void checkSumStep(int num1, int num2, int expectedSum) {
        Assert.assertTrue(num1 + num2 == expectedSum, "Сумма слагаемых не соответствует ожидаемому значению");
    }


    @DataProvider(parallel = true, name = "para")
    public Object[] getId() {
        q++;
        ArrayList<String> list = new ArrayList<String>();
        list.add("qwe1");
        list.add("qwe2");
        list.add("qwe3");
        list.add("qwe4");
        list.add("qwe5");
        list.add("qwe6");
        list.add("qwe7");
        list.add("qwe8");
        list.add("qwe9");
        list.add("qwe10");
        list.add("qwe11");
        list.add("qwe12");
        list.add("qwe13");
        list.add("qwe14");
        list.add("qwe15");
        return new Object[]{list};
    }

    @Test(dataProvider = "para")
    public void firstTestPar(String a) {
        System.out.println(a + Thread.currentThread().getName());

    }


    @Test
    public void firstT12estPar(){
        String [] [] str=null;
        str=new String[Integer.parseInt("1")][Integer.parseInt("4")];
        System.out.println(Arrays.deepToString(str));
    }


    @Test
    public void firstTestMap() throws IOException, SQLException {
        File file = new File("G:/try.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
             list.add(sheet.getRow(i).getCell(0).getStringCellValue());
        }
        map.put("1",list);



        String url = "jdbc:h2:mem:";

        Connection con = DriverManager.getConnection(url);
        Statement stm = con.createStatement();
        stm.executeUpdate("CREATE TABLE CUSTOMER (name varchar(20));");

        for (int i = 0; i < list.size(); i++) {
            stm.executeUpdate(
                    "INSERT into CUSTOMER values ('"+list.get(i)+"');");
        }
        ResultSet rs = stm.executeQuery("SELECT * from CUSTOMER");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }

    }
} 
