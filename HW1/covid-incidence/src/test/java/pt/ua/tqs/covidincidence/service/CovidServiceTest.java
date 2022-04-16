package pt.ua.tqs.covidincidence.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import pt.ua.tqs.covidincidence.model.CovidStats;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CovidServiceTest {
    // TODO: Try removing lenient later
    @Mock(lenient = true)
    private WebClient webClientMock;
    @Mock(lenient = true)
    private WebClient.RequestHeadersUriSpec uriSpecMock;
    @Mock(lenient = true)
    private WebClient.RequestHeadersSpec headersSpecMock;
    @Mock(lenient = true)
    private WebClient.ResponseSpec responseSpecMock;

    @InjectMocks
    private CovidService covidService;

    public CovidServiceTest() {
    }

    @BeforeEach
    public void setUp() {
        Mockito.when(webClientMock.get()).thenReturn(uriSpecMock);
        Mockito.when(uriSpecMock.uri("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2021-06-09")).thenReturn(headersSpecMock);
        Mockito.when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
        Mockito.when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(
                "{\"get\":\"history\",\"parameters\":{\"country\":\"Portugal\",\"day\":\"2021-06-09\"},\"errors\":[],\"results\":3,\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10168845,\"cases\":{\"new\":\"+890\",\"active\":23996,\"critical\":70,\"recovered\":813489,\"1M_pop\":\"84033\",\"total\":854522},\"deaths\":{\"new\":null,\"1M_pop\":\"1675\",\"total\":17037},\"tests\":{\"1M_pop\":\"1188905\",\"total\":12089787},\"day\":\"2021-06-09\",\"time\":\"2021-06-09T22:00:02+00:00\"},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10168845,\"cases\":{\"new\":\"+598\",\"active\":23631,\"critical\":66,\"recovered\":812964,\"1M_pop\":\"83946\",\"total\":853632},\"deaths\":{\"new\":\"+1\",\"1M_pop\":\"1675\",\"total\":17037},\"tests\":{\"1M_pop\":\"1188905\",\"total\":12089787},\"day\":\"2021-06-09\",\"time\":\"2021-06-09T15:15:03+00:00\"},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10168925,\"cases\":{\"new\":\"+598\",\"active\":23631,\"critical\":66,\"recovered\":812964,\"1M_pop\":\"83945\",\"total\":853632},\"deaths\":{\"new\":\"+1\",\"1M_pop\":\"1675\",\"total\":17037},\"tests\":{\"1M_pop\":\"1188895\",\"total\":12089787},\"day\":\"2021-06-09\",\"time\":\"2021-06-09T00:00:02+00:00\"}]}"
        ));

        Mockito.when(webClientMock.get()).thenReturn(uriSpecMock);
        Mockito.when(uriSpecMock.uri("https://covid-193.p.rapidapi.com/history?country=Wrong Country&day=2021-06-09")).thenReturn(headersSpecMock);
        Mockito.when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
        Mockito.when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(
                "{\"get\":\"history\",\"parameters\":{\"country\":\"Wrong Country\",\"day\":\"2021-06-09\"},\"errors\":[],\"results\":0,\"response\":[]}"
        ));

        Mockito.when(webClientMock.get()).thenReturn(uriSpecMock);
        Mockito.when(uriSpecMock.uri("https://covid-193.p.rapidapi.com/history?country=Portugal&day=2010-01-01")).thenReturn(headersSpecMock);
        Mockito.when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
        Mockito.when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(
                "{\"get\":\"history\",\"parameters\":{\"country\":\"Wrong Country\",\"day\":\"2021-06-09\"},\"errors\":[],\"results\":0,\"response\":[]}"
        ));
    }

    @Test
    public void whenGetCovidStatsForPortugalOnDate_thenCorrectCovidStatsShouldBeReturned() {
        CovidStats covidStats = covidService.getCovidStatsByCountryAndDate("Portugal", "2021-06-09");

        CovidStats expectedStats = new CovidStats(
                "Portugal",
                "2021-06-09",
                598,
                23631,
                66,
                812964,
                83945,
                853632,
                1,
                1675,
                17037,
                1188895,
                12089787
        );

        assertThat(covidStats).isNotNull();
        assertThat(covidStats.equals(expectedStats)).isEqualTo(true);
    }

    @Test
    public void whenGetCovidStatsForNonExistingCountry_thenCovidStatsShouldBeNull() {
        CovidStats covidStats = covidService.getCovidStatsByCountryAndDate("Wrong Country", "2021-06-09");

        assertThat(covidStats).isNull();
    }

    @Test
    public void whenGetCovidStatsForDateWithNoData_thenCovidStatsShouldBeNull() {
        CovidStats covidStats = covidService.getCovidStatsByCountryAndDate("Portugal", "2010-01-01");

        assertThat(covidStats).isNull();
    }
}
