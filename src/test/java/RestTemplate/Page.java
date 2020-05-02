package RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {

    private String login;


    public String getLogin() {
        return login;
    }

}
