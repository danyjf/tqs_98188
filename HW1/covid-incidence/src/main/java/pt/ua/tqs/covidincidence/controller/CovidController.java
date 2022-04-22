package pt.ua.tqs.covidincidence.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.tqs.covidincidence.model.CovidHistoryData;
import pt.ua.tqs.covidincidence.service.CovidService;

@RestController
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class CovidController {
    @Autowired
    private CovidService covidService;

    @GetMapping("covid/{country}/{date}")
    private CovidHistoryData getCovidStatsByCountryAndDate(@PathVariable(value = "country") String country, @PathVariable(value = "date") String date) throws JsonProcessingException {
        return covidService.getCovidHistoryDataByCountryAndDate(country, date);
    }
}
