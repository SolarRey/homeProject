package soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import wsdl.CapitalCity;
import wsdl.CapitalCityResponse;

class CardChecker extends WebServiceGatewaySupport {

    public CapitalCityResponse add(String cardNumber) {
        CapitalCity request = new CapitalCity();
        request.setSCountryISOCode(cardNumber);

        System.out.println("Requesting for " + cardNumber);

        return (CapitalCityResponse) getWebServiceTemplate().marshalSendAndReceive(request,
                new SoapActionCallback(""));
    }

    public void printResponse(CapitalCityResponse response) {
        System.out.println("Response is " + response.getCapitalCityResult());
    }

}
