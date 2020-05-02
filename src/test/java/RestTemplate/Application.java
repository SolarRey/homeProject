package RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import okhttp3.*;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Application {

    @Test(priority = 1)
    void springRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        Page page = restTemplate.getForObject("https://api.github.com/users/defunkt",Page.class);
        System.out.println(page.getLogin());
    }

    @Test(priority = 2)
    void okHttp() throws IOException {
        String url="https://api.github.com/users/defunkt";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseBody responseBody = client.newCall(request).execute().body();
        Page entity = objectMapper.readValue(responseBody.string(), Page.class);
        System.out.println(entity.getLogin());
    }

    @Test(priority = 3)
    void restAssured() {
        Page page=given()
                .contentType(ContentType.JSON)
                .get("https://api.github.com/users/defunkt")
                .then().extract().response().as(Page.class);
        System.out.println(page.getLogin());
    }
}
