package pt.ua.tqs.covidincidence.restclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RestClient {
    private WebClient webClient;

    public RestClient() {
        webClient = WebClient.builder()
                .defaultHeader("X-RapidAPI-Key", "feed5f3f36mshc194d7043f6be9cp115694jsn68552002fd76")
                .build();
    }

    public String get(String rapidAPIHostHeader, String uri) {
        return webClient
                .get()
                .uri(uri)
                .header("X-RapidAPI-Host", rapidAPIHostHeader)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
