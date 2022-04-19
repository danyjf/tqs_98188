package pt.ua.tqs.covidincidence.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.tqs.covidincidence.model.CovidStats;
import pt.ua.tqs.covidincidence.service.CovidService;

@RestController
public class CovidController {
    @Autowired
    private CovidService covidService;

    @GetMapping("covid/{country}/{date}")
    private CovidStats getCovidStatsByCountryAndDate(@PathVariable(value = "country") String country, @PathVariable(value = "date") String date) throws JsonProcessingException {
        covidService.getCovidStatsByCountryAndDate(country, date);
        return null;
    }
}
