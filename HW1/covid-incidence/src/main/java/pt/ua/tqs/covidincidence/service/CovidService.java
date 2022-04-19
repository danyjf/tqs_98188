package pt.ua.tqs.covidincidence.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pt.ua.tqs.covidincidence.model.CovidStats;

@Service
public class CovidService {
    @Autowired
    private WebClient webClient;

    public CovidStats getCovidStatsByCountryAndDate(String country, String date) {
        Logger logger = LoggerFactory.getLogger(CovidService.class);
        CovidStats covidStats = null;

        String response = webClient
                .get()
                .uri(String.format("https://covid-193.p.rapidapi.com/history?country=%s&day=%s", country, date))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            JSONObject jsonResponse = new JSONObject(response);
            int nResults = jsonResponse.getInt("results");

            if(nResults == 0) {
                return null;
            }

            JSONObject jsonCovidStats = jsonResponse.getJSONArray("response").getJSONObject(nResults - 1);
            covidStats = new CovidStats(
                    jsonCovidStats.getString("country"),
                    jsonCovidStats.getString("day"),
                    jsonCovidStats.getJSONObject("cases").getString("new"),
                    jsonCovidStats.getJSONObject("cases").getInt("active"),
                    jsonCovidStats.getJSONObject("cases").getInt("critical"),
                    jsonCovidStats.getJSONObject("cases").getInt("recovered"),
                    jsonCovidStats.getJSONObject("cases").getInt("1M_pop"),
                    jsonCovidStats.getJSONObject("cases").getInt("total"),
                    jsonCovidStats.getJSONObject("deaths").getInt("new"),
                    jsonCovidStats.getJSONObject("deaths").getInt("1M_pop"),
                    jsonCovidStats.getJSONObject("deaths").getInt("total"),
                    jsonCovidStats.getJSONObject("tests").getInt("1M_pop"),
                    jsonCovidStats.getJSONObject("tests").getInt("total")
            );
        } catch (JSONException err) {
            logger.error(err.toString());
        }

        return covidStats;
    }
}
