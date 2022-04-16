package pt.ua.tqs.covidincidence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pt.ua.tqs.covidincidence.model.CovidStats;
import reactor.core.publisher.Mono;

@Service
public class CovidService {
    @Autowired
    private WebClient webClient;

    public CovidStats getCovidStatsByCountryAndDate(String country, String date) {
        return null;
    }
}
