package allure;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAllure {

    @Test
    void firstTest(){
        stepAssert(1,1);
        stepAssert(2,1);
        stepAssert("a","a");
    }

    @Step
    void stepAssert(Object a,Object c){
        Assert.assertEquals(a,c);
    }
}
