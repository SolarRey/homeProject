package soap;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.Test;
import wsdl.CapitalCityResponse;

public class Application {

    @Test
    public static void soapCHICK() {
        ApplicationContext ctx = SpringApplication.run(CheckerConfiguration.class, "null");

        CardChecker checker = ctx.getBean(CardChecker.class);

        CapitalCityResponse response = checker.add("ISO 3166-2:AF");
        checker.printResponse(response);



    }


}
