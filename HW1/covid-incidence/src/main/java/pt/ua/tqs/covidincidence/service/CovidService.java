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
                return null;
            }

            JSONObject jsonCovidStats = jsonResponse.getJSONArray("response").getJSONObject(nResults - 1);
            JSONObject jsonCovidCasesStats = jsonCovidStats.getJSONObject("cases");
            JSONObject jsonCovidDeathsStats = jsonCovidStats.getJSONObject("deaths");
            JSONObject jsonCovidTestsStats = jsonCovidStats.getJSONObject("tests");
            covidHistoryData = new CovidHistoryData(
                    jsonCovidStats.optString("country"),
                    jsonCovidStats.optString("day"),
                    jsonCovidCasesStats.optString("new"),
                    jsonCovidCasesStats.optInt("active"),
                    jsonCovidCasesStats.optInt("critical"),
                    jsonCovidCasesStats.optInt("recovered"),
                    jsonCovidCasesStats.optInt("1M_pop"),
                    jsonCovidCasesStats.optInt("total"),
                    jsonCovidDeathsStats.optString("new"),
                    jsonCovidDeathsStats.optInt("1M_pop"),
                    jsonCovidDeathsStats.optInt("total"),
                    jsonCovidTestsStats.optInt("1M_pop"),
                    jsonCovidTestsStats.optInt("total")
            );
        } catch (JSONException err) {
            logger.error(err.toString());
        }

        logger.info("Adding data to cache");
        cachedData.addToCache(requestUrl, covidHistoryData, 60);

        return covidHistoryData;
    }
}
