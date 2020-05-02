package RestAssured;


import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


public class SignInTest {


    Logger log = LogManager.getLogger(SignInTest.class);


    @Test()
    public void testPostWithOkHttp() throws IOException {
        String str="asdas";
        String name;
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().add("name",str).build();
        Request request = new Request.Builder().url("https://api.github.com/users/defunkt")
          .post(requestBody)
          .build();

        Response response = client.newCall(request)
          .execute();
        this.log.info(request);


        int statusCode = response.code();
        this.log.info(statusCode);

        String responseBody = Objects.requireNonNull(response.body())
                .string();

        this.log.info(responseBody);

    }


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    @Test
    void post() throws IOException {
        String url="https://api.github.com/users/defunkt";
        String json="{\"login\": \"defunkt\"}";

        RequestBody body = RequestBody.create(json, JSON); // new
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        this.log.info(request);
        this.log.info(json);
//        this.log.info();
        String responseBody = Objects.requireNonNull(response.body())
                .string();

        this.log.info(responseBody);
    }



//    /**
//     * @since Mar 7, 2020
//     * @param name
//     * @param job
//     * @throws IOException
//     */
//    @Test (dataProvider = "postData", groups = "PostTests")
//    public void testPostwithOkHttpForm (final String name, final String job) throws IOException {
//        final OkHttpClient client = new OkHttpClient ();
//        final RequestBody formBody = new FormBody.Builder ().add ("name", name)
//                .add ("job", job)
//                .build ();
//        final Request request = new Request.Builder ().url (this.url + "/api/users")
//                .post (formBody)
//                .build ();
//
//        final Response response = client.newCall (request)
//                .execute ();
//
//        final int statusCode = response.code ();
//        this.log.info (statusCode);
//
//        final String responseBody = response.body ()
//                .string ();
//
//        this.log.info (responseBody);
//
//        final JSONObject jsonResponse = new JSONObject (responseBody);
//        assertEquals (statusCode, 201);
//        assertThat (jsonResponse.getString ("name"), equalTo (name));
//        assertThat (jsonResponse.getString ("job"), equalTo (job));
//        assertThat (jsonResponse.getString ("id"), notNullValue ());
//
//    }


}
