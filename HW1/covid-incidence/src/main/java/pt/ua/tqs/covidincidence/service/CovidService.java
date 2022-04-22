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

        logger.info(String.format("Getting covid history data for %s on %s", country, date));
        String requestUrl = String.format("https://covid-193.p.rapidapi.com/history?country=%s&day=%s", country, date);

        CovidHistoryData covidHistoryData = cachedData.getFromCache(requestUrl);
        if(covidHistoryData != null) {
            logger.info("Found data in cache");
            return covidHistoryData;
        }
        logger.info("Data not in cache");

        logger.info("Requesting data from external API");
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
                logger.info("Requested data not in external API");
                logger.info("Adding to cache");

                CovidHistoryData dataNotFound = new CovidHistoryData(country, date);
                cachedData.addToCache(requestUrl, dataNotFound, 60);

                return dataNotFound;
            }

            JSONObject jsonCovidStats = jsonResponse.getJSONArray("response").getJSONObject(nResults - 1);
            JSONObject jsonCovidCasesStats = jsonCovidStats.getJSONObject("cases");
            JSONObject jsonCovidDeathsStats = jsonCovidStats.getJSONObject("deaths");
            JSONObject jsonCovidTestsStats = jsonCovidStats.getJSONObject("tests");
            covidHistoryData = new CovidHistoryData(
                    jsonCovidStats.optString("country", "N/A"),
                    jsonCovidStats.optString("day", "N/A"),
                    jsonCovidCasesStats.optString("new", "N/A"),
                    jsonCovidCasesStats.optInt("active", -1),
                    jsonCovidCasesStats.optInt("critical", -1),
                    jsonCovidCasesStats.optInt("recovered", -1),
                    jsonCovidCasesStats.optInt("1M_pop", -1),
                    jsonCovidCasesStats.optInt("total", -1),
                    jsonCovidDeathsStats.optString("new", "N/A"),
                    jsonCovidDeathsStats.optInt("1M_pop", -1),
                    jsonCovidDeathsStats.optInt("total", -1),
                    jsonCovidTestsStats.optInt("1M_pop", -1),
                    jsonCovidTestsStats.optInt("total", -1)
            );
        } catch (JSONException err) {
            logger.error(err.toString());
        }

        logger.info("Adding data to cache");
        cachedData.addToCache(requestUrl, covidHistoryData, 60);

        return covidHistoryData;
    }
}
