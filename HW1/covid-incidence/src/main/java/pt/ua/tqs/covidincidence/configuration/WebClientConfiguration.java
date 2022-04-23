package pt.ua.tqs.covidincidence.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
//                .defaultHeader("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
                .defaultHeader("X-RapidAPI-Key", "feed5f3f36mshc194d7043f6be9cp115694jsn68552002fd76")
                .build();
    }
}
