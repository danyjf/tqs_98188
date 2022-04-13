package pt.ua.tqs.covidincidence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.tqs.covidincidence.service.CovidService;

@RestController
public class CovidController {
    @Autowired
    private CovidService covidService;

    @GetMapping("covid/{country}/{date}")
    private void getCovidCasesByCountryAndDate(@PathVariable(value = "country") String country, @PathVariable(value = "date") String date) {

    }
}
