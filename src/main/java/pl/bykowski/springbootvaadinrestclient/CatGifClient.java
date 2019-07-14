package pl.bykowski.springbootvaadinrestclient;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatGifClient {

    private RestTemplate restTemplate;

    public CatGifClient() {
        this.restTemplate = new RestTemplate();
    }

    public String getCatGifFormApi() {
        String url;
        do {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    "https://aws.random.cat/meow",
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    String.class);

            String json = exchange.getBody();
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
            url = jsonObject.get("file").getAsString();
        } while (!url.endsWith(".gif"));
        return url;
    }
}
