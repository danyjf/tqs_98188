package pt.ua.tqs.covidincidence.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ua.tqs.covidincidence.model.CovidHistoryData;
import pt.ua.tqs.covidincidence.model.CovidWorldData;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class CachedDataTest {
    private CachedData cachedData;
    private final CountDownLatch waiter = new CountDownLatch(1);

    @BeforeEach
    void setUp() {
        cachedData = new CachedData();
    }

    @Test
    void whenAddToCache_thenDataShouldBeInCache() {
        CovidHistoryData portugalCovidData = new CovidHistoryData(
                "Portugal",
                "2021-06-09",
                "+598",
                23631,
                66,
                812964,
                83945,
                853632,
                "+1",
                1675,
                17037,
                1188895,
                12089787
        );

        CovidWorldData worldData = new CovidWorldData(
                508746132,
                236391,
                41326673,
                461178426,
                287367,
                90.65,
                65267.0,
                6241033,
                842,
                800.7
        );

        cachedData.addToCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09", portugalCovidData, 60);
        cachedData.addToCache("https://vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com/api/npm-covid-data/world", worldData, 60);

        assertThat(cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09"))
                .isEqualTo(portugalCovidData);
        assertThat(cachedData.getFromCache("https://vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com/api/npm-covid-data/world"))
                .isEqualTo(worldData);
        assertThat(cachedData.getAllCachedData())
                .hasSize(2);
    }

    @Test
    void whenGetDataNotCached_thenReturnNull() {
        assertThat(cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09"))
                .isNull();
        assertThat(cachedData.getAllCachedData())
                .isEmpty();
    }

    @Test
    void whenGetPreviouslyCachedDataWithExpiredTTL_thenReturnNull() throws InterruptedException {
        CovidHistoryData portugalCovidData = new CovidHistoryData(
                "Portugal",
                "2021-06-09",
                "+598",
                23631,
                66,
                812964,
                83945,
                853632,
                "+1",
                1675,
                17037,
                1188895,
                12089787
        );

        CovidWorldData worldData = new CovidWorldData(
                508746132,
                236391,
                41326673,
                461178426,
                287367,
                90.65,
                65267.0,
                6241033,
                842,
                800.7
        );

        cachedData.addToCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09", portugalCovidData, 5);
        cachedData.addToCache("https://vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com/api/npm-covid-data/world", worldData, 60);

        assertThat(cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09"))
                .isEqualTo(portugalCovidData);
        assertThat(cachedData.getFromCache("https://vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com/api/npm-covid-data/world"))
                .isEqualTo(worldData);

        waiter.await(10, TimeUnit.SECONDS);

        assertThat(cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09"))
                .isNull();
        assertThat(cachedData.getFromCache("https://vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com/api/npm-covid-data/world"))
                .isNotNull();
    }

    @Test
    void whenRequestsAreMade_thenRequestCountShouldIncrement() {
        assertThat(cachedData.getRequestCount()).isZero();

        CovidHistoryData portugalCovidData = new CovidHistoryData(
                "Portugal",
                "2021-06-09",
                "+598",
                23631,
                66,
                812964,
                83945,
                853632,
                "+1",
                1675,
                17037,
                1188895,
                12089787
        );

        CovidHistoryData spainCovidData = new CovidHistoryData(
                "Spain",
                "2022-01-01",
                "+598",
                23631,
                66,
                812964,
                83945,
                853632,
                "+1",
                1675,
                17037,
                1188895,
                12089787
        );

        cachedData.addToCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09", portugalCovidData, 60);
        cachedData.addToCache("https://covid-193.p.rapidapi.com/history?country=Spain&day=2022-01-01", spainCovidData, 60);

        assertThat(cachedData.getRequestCount()).isZero();

        cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09");

        assertThat(cachedData.getRequestCount()).isEqualTo(1);
    }

    @Test
    void whenGetCachedData3Times_thenThereShouldBe3Hits0Misses() {
        CovidHistoryData portugalCovidData = new CovidHistoryData(
                "Portugal",
                "2021-06-09",
                "+598",
                23631,
                66,
                812964,
                83945,
                853632,
                "+1",
                1675,
                17037,
                1188895,
                12089787
        );
        cachedData.addToCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09", portugalCovidData, 60);

        cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09");
        cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09");
        cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09");

        assertThat(cachedData.getHit()).isEqualTo(3);
        assertThat(cachedData.getMiss()).isZero();
    }

    @Test
    void whenGetNonCachedData3Times_thenThereShouldBe3Misses0Hits() {
        cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09");
        cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09");
        cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09");

        assertThat(cachedData.getMiss()).isEqualTo(3);
        assertThat(cachedData.getHit()).isZero();
    }
}
