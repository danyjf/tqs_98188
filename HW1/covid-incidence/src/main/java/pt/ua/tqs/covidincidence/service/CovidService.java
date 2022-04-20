package pt.ua.tqs.covidincidence.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pt.ua.tqs.covidincidence.cache.CachedData;
import pt.ua.tqs.covidincidence.model.CovidHistoryData;

@Service
public class CovidService {
    @Autowired
    private WebClient webClient;

    @Autowired
    private CachedData cachedData;

    public CovidHistoryData getCovidHistoryDataByCountryAndDate(String country, String date) {
        Logger logger = LoggerFactory.getLogger(CovidService.class);

        String requestUrl = String.format("https://covid-193.p.rapidapi.com/history?country=%s&day=%s", country, date);
        CovidHistoryData covidHistoryData = cachedData.getFromCache(requestUrl);
        if(covidHistoryData != null)
            return covidHistoryData;

        String response = webClient
                .get()
                .uri(requestUrl)
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
            JSONObject jsonCovidCasesStats = jsonCovidStats.getJSONObject("cases");
            JSONObject jsonCovidDeathsStats = jsonCovidStats.getJSONObject("deaths");
            JSONObject jsonCovidTestsStats = jsonCovidStats.getJSONObject("tests");
            covidHistoryData = new CovidHistoryData(
                    jsonCovidStats.getString("country"),
                    jsonCovidStats.getString("day"),
                    jsonCovidCasesStats.getString("new"),
                    jsonCovidCasesStats.getInt("active"),
                    jsonCovidCasesStats.getInt("critical"),
                    jsonCovidCasesStats.getInt("recovered"),
                    jsonCovidCasesStats.getInt("1M_pop"),
                    jsonCovidCasesStats.getInt("total"),
                    jsonCovidDeathsStats.getInt("new"),
                    jsonCovidDeathsStats.getInt("1M_pop"),
                    jsonCovidDeathsStats.getInt("total"),
                    jsonCovidTestsStats.getInt("1M_pop"),
                    jsonCovidTestsStats.getInt("total")
            );
        } catch (JSONException err) {
            logger.error(err.toString());
        }

//        cachedData.addToCache(requestUrl, covidHistoryData, 60);

        return covidHistoryData;
    }
}
