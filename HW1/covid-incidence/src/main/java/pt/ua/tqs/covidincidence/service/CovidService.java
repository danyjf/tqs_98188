package pt.ua.tqs.covidincidence.service;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ua.tqs.covidincidence.cache.CachedData;
import pt.ua.tqs.covidincidence.model.CovidHistoryData;
import pt.ua.tqs.covidincidence.model.CovidWorldData;
import pt.ua.tqs.covidincidence.restclient.RestClient;

@Service
public class CovidService {
    @Autowired
    private RestClient restClient;

    @Autowired
    private CachedData cachedData;

    private static final Logger logger = LogManager.getLogger();

    public CovidHistoryData getCovidHistoryDataByCountryAndDate(String country, String date) {
        logger.info("Getting covid history data for {} on {}", country, date);
        String requestUrl = String.format("https://covid-193.p.rapidapi.com/history?country=%s&day=%s", country, date);

        CovidHistoryData covidHistoryData = (CovidHistoryData) cachedData.getFromCache(requestUrl);
        if(covidHistoryData != null) {
            logger.info("Found data in cache");
            return covidHistoryData;
        }
        logger.info("Data not in cache");

        logger.info("Requesting data from external API");
        String response = restClient.get("covid-193.p.rapidapi.com", requestUrl);

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

    public CovidWorldData getCovidWorldData() {
        logger.info("Getting covid world data");
        String requestUrl = "https://vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com/api/npm-covid-data/world";

        CovidWorldData covidWorldData = (CovidWorldData) cachedData.getFromCache(requestUrl);
        if(covidWorldData != null) {
            logger.info("Found data in cache");
            return covidWorldData;
        }
        logger.info("Data not in cache");

        logger.info("Requesting data from external API");
        String response = restClient.get("vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com", requestUrl);

        try {
            JSONArray jsonResponse = new JSONArray(response);
            JSONObject jsonData = jsonResponse.getJSONObject(0);

            covidWorldData = new CovidWorldData(
                    jsonData.optInt("TotalCases", -1),
                    jsonData.optInt("NewCases", -1),
                    jsonData.optInt("ActiveCases", -1),
                    jsonData.optInt("TotalRecovered", -1),
                    jsonData.optInt("NewRecovered", -1),
                    jsonData.optDouble("Recovery_Proporation", -1.0),
                    jsonData.optDouble("TotCases_1M_Pop", -1.0),
                    jsonData.optInt("TotalDeaths", -1),
                    jsonData.optInt("NewDeaths", -1),
                    jsonData.optDouble("Deaths_1M_pop", -1.0)
            );
        } catch (JSONException err) {
            logger.error(err.toString());
        }

        logger.info("Adding data to cache");
        cachedData.addToCache(requestUrl, covidWorldData, 60);

        return covidWorldData;
    }
}
