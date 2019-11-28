package soap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CheckerConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("wsdl");
        return marshaller;
    }

    @Bean
    public CardChecker cardChecker(Jaxb2Marshaller marshaller) {
        CardChecker checker = new CardChecker();
        checker.setDefaultUri("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso");
        checker.setMarshaller(marshaller);
        checker.setUnmarshaller(marshaller);
        return checker;
    }

}
