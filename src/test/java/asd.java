import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.ArrayList;

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
    public void firstT12estPar() {
        System.out.println(32 * 32);
    }



}
