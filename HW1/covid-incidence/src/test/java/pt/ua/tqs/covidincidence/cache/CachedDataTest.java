package pt.ua.tqs.covidincidence.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ua.tqs.covidincidence.model.CovidHistoryData;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class CachedDataTest {
    private CachedData cachedData;
    private final CountDownLatch waiter = new CountDownLatch(1);

    @BeforeEach
    public void setUp() {
        cachedData = new CachedData();
    }

    @Test
    public void whenAddToCache_thenDataShouldBeInCache() {
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

        assertThat(cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09"))
                .isEqualTo(portugalCovidData);
        assertThat(cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Spain&day=2022-01-01"))
                .isEqualTo(spainCovidData);
        assertThat(cachedData.getAllCachedData().size())
                .isEqualTo(2);
    }

    @Test
    public void whenGetDataNotCached_thenReturnNull() {
        assertThat(cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09"))
                .isNull();
        assertThat(cachedData.getAllCachedData().size())
                .isEqualTo(0);
    }

    @Test
    public void whenGetPreviouslyCachedDataWithExpiredTTL_thenReturnNull() throws InterruptedException {
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

        cachedData.addToCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09", portugalCovidData, 5);
        cachedData.addToCache("https://covid-193.p.rapidapi.com/history?country=Spain&day=2022-01-01", spainCovidData, 5);

        assertThat(cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09"))
                .isEqualTo(portugalCovidData);
        assertThat(cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Spain&day=2022-01-01"))
                .isEqualTo(spainCovidData);

        waiter.await(10, TimeUnit.SECONDS);

        assertThat(cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09"))
                .isNull();
        assertThat(cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Spain&day=2022-01-01"))
                .isNull();
    }

    @Test
    public void whenRequestsAreMade_thenRequestCountShouldIncrement() {
        assertThat(cachedData.getRequestCount()).isEqualTo(0);

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

        assertThat(cachedData.getRequestCount()).isEqualTo(2);

        cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09");

        assertThat(cachedData.getRequestCount()).isEqualTo(3);
    }

    @Test
    public void whenGetCachedData3Times_thenThereShouldBe3Hits0Misses() {
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
        assertThat(cachedData.getMiss()).isEqualTo(0);
    }

    @Test
    public void whenGetNonCachedData3Times_thenThereShouldBe3Misses0Hits() {
        cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09");
        cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09");
        cachedData.getFromCache("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09");

        assertThat(cachedData.getMiss()).isEqualTo(3);
        assertThat(cachedData.getHit()).isEqualTo(0);
    }
}
