package pt.ua.tqs.covidincidence.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import pt.ua.tqs.covidincidence.service.CovidService;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class CovidController {
    @Autowired
    private CovidService covidService;

    @GetMapping("covid/{country}/{date}")
    private Mono<String> getCovidCasesByCountryAndDate(@PathVariable(value = "country") String country, @PathVariable(value = "date") String date) throws JsonProcessingException {
        return null;
    }
}
