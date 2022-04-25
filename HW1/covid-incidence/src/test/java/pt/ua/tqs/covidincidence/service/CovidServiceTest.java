package pt.ua.tqs.covidincidence.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ua.tqs.covidincidence.cache.CachedData;
import pt.ua.tqs.covidincidence.model.CovidHistoryData;
import pt.ua.tqs.covidincidence.model.CovidWorldData;
import pt.ua.tqs.covidincidence.restclient.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CovidServiceTest {
    @Mock
    private RestClient restClient;

    @Mock
    private CachedData cachedData;

    @InjectMocks
    private CovidService covidService;

    public CovidServiceTest() {
    }

    @Test
    void whenGetCovidHistoryForPortugalOnDate_thenCorrectCovidHistoryDataShouldBeReturned() {
        Mockito.when(restClient.get("covid-193.p.rapidapi.com", "https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09"))
                .thenReturn("{\"get\":\"history\",\"parameters\":{\"country\":\"Portugal\",\"day\":\"2021-06-09\"},\"errors\":[],\"results\":3,\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10168845,\"cases\":{\"new\":\"+890\",\"active\":23996,\"critical\":70,\"recovered\":813489,\"1M_pop\":\"84033\",\"total\":854522},\"deaths\":{\"new\":null,\"1M_pop\":\"1675\",\"total\":17037},\"tests\":{\"1M_pop\":\"1188905\",\"total\":12089787},\"day\":\"2021-06-09\",\"time\":\"2021-06-09T22:00:02+00:00\"},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10168845,\"cases\":{\"new\":\"+598\",\"active\":23631,\"critical\":66,\"recovered\":812964,\"1M_pop\":\"83946\",\"total\":853632},\"deaths\":{\"new\":\"+1\",\"1M_pop\":\"1675\",\"total\":17037},\"tests\":{\"1M_pop\":\"1188905\",\"total\":12089787},\"day\":\"2021-06-09\",\"time\":\"2021-06-09T15:15:03+00:00\"},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10168925,\"cases\":{\"new\":\"+598\",\"active\":23631,\"critical\":66,\"recovered\":812964,\"1M_pop\":\"83945\",\"total\":853632},\"deaths\":{\"new\":\"+1\",\"1M_pop\":\"1675\",\"total\":17037},\"tests\":{\"1M_pop\":\"1188895\",\"total\":12089787},\"day\":\"2021-06-09\",\"time\":\"2021-06-09T00:00:02+00:00\"}]}");

        CovidHistoryData covidHistoryData = covidService.getCovidHistoryDataByCountryAndDate("Portugal", "2021-06-09");

        CovidHistoryData expectedStats = new CovidHistoryData(
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

        assertThat(covidHistoryData).isNotNull();
        assertThat(covidHistoryData.equals(expectedStats)).isTrue();
    }

    @Test
    void whenGetCovidHistoryForNonExistingCountry_thenCovidHistoryDataShouldBeEmpty() {
        Mockito.when(restClient.get("covid-193.p.rapidapi.com", "https://covid-193.p.rapidapi.com/history?country=Wrong Country&day=2021-06-09"))
                .thenReturn("{\"get\":\"history\",\"parameters\":{\"country\":\"Wrong Country\",\"day\":\"2021-06-09\"},\"errors\":[],\"results\":0,\"response\":[]}");

        CovidHistoryData covidHistoryData = covidService.getCovidHistoryDataByCountryAndDate("Wrong Country", "2021-06-09");

        CovidHistoryData expected = new CovidHistoryData("Wrong Country", "2021-06-09");

        assertThat(covidHistoryData).isEqualTo(expected);
    }

    @Test
    void whenGetCovidHistoryForDateWithNoData_thenCovidHistoryDataShouldBeEmpty() {
        Mockito.when(restClient.get("covid-193.p.rapidapi.com", "https://covid-193.p.rapidapi.com/history?country=Portugal&day=2010-01-01"))
                .thenReturn("{\"get\":\"history\",\"parameters\":{\"country\":\"Wrong Country\",\"day\":\"2021-06-09\"},\"errors\":[],\"results\":0,\"response\":[]}");

        CovidHistoryData covidHistoryData = covidService.getCovidHistoryDataByCountryAndDate("Portugal", "2010-01-01");

        CovidHistoryData expected = new CovidHistoryData("Portugal", "2010-01-01");

        assertThat(covidHistoryData).isEqualTo(expected);
    }

    @Test
    void whenGetCovidDataForWorld_thenCovidWorldDataShouldBeReturned() {
        Mockito.when(restClient.get("vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com", "https://vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com/api/npm-covid-data/world"))
                .thenReturn("[{\"id\":\"40f44943-8413-4a31-a4df-deee8111f85f\",\"rank\":0,\"Country\":\"World\",\"Continent\":\"All\",\"TwoLetterSymbol\":null,\"ThreeLetterSymbol\":null,\"Infection_Risk\":0,\"Case_Fatality_Rate\":1.23,\"Test_Percentage\":0,\"Recovery_Proporation\":90.65,\"TotalCases\":508746132,\"NewCases\":236391,\"TotalDeaths\":6241033,\"NewDeaths\":842,\"TotalRecovered\":\"461178426\",\"NewRecovered\":287367,\"ActiveCases\":41326673,\"TotalTests\":\"0\",\"Population\":\"0\",\"one_Caseevery_X_ppl\":0,\"one_Deathevery_X_ppl\":0,\"one_Testevery_X_ppl\":0,\"Deaths_1M_pop\":800.7,\"Serious_Critical\":42727,\"Tests_1M_Pop\":0,\"TotCases_1M_Pop\":65267}]");

        CovidWorldData covidWorldData = covidService.getCovidWorldData();

        CovidWorldData expected = new CovidWorldData(
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

        assertThat(covidWorldData).isEqualTo(expected);
    }
}
