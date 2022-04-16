package pt.ua.tqs.covidincidence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class CovidService {
    @Autowired
    private WebClient webClient;

    public Map<String, String> getCovidCasesByCountryAndDate(String country, String date) {
        return null;
    }
}
