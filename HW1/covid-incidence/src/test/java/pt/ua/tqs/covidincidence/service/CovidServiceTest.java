package pt.ua.tqs.covidincidence.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import pt.ua.tqs.covidincidence.cache.CachedData;
import pt.ua.tqs.covidincidence.model.CovidHistoryData;
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

    @Mock
    private CachedData cachedData;

    @InjectMocks
    private CovidService covidService;

    public CovidServiceTest() {
    }

    @Test
    public void whenGetCovidHistoryForPortugalOnDate_thenCorrectCovidHistoryDataShouldBeReturned() {
        Mockito.when(webClientMock.get()).thenReturn(uriSpecMock);
        Mockito.when(uriSpecMock.uri(ArgumentMatchers.<String>notNull())).thenReturn(headersSpecMock);
        Mockito.when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
        Mockito.when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(
                "{\"get\":\"history\",\"parameters\":{\"country\":\"Portugal\",\"day\":\"2021-06-09\"},\"errors\":[],\"results\":3,\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10168845,\"cases\":{\"new\":\"+890\",\"active\":23996,\"critical\":70,\"recovered\":813489,\"1M_pop\":\"84033\",\"total\":854522},\"deaths\":{\"new\":null,\"1M_pop\":\"1675\",\"total\":17037},\"tests\":{\"1M_pop\":\"1188905\",\"total\":12089787},\"day\":\"2021-06-09\",\"time\":\"2021-06-09T22:00:02+00:00\"},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10168845,\"cases\":{\"new\":\"+598\",\"active\":23631,\"critical\":66,\"recovered\":812964,\"1M_pop\":\"83946\",\"total\":853632},\"deaths\":{\"new\":\"+1\",\"1M_pop\":\"1675\",\"total\":17037},\"tests\":{\"1M_pop\":\"1188905\",\"total\":12089787},\"day\":\"2021-06-09\",\"time\":\"2021-06-09T15:15:03+00:00\"},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10168925,\"cases\":{\"new\":\"+598\",\"active\":23631,\"critical\":66,\"recovered\":812964,\"1M_pop\":\"83945\",\"total\":853632},\"deaths\":{\"new\":\"+1\",\"1M_pop\":\"1675\",\"total\":17037},\"tests\":{\"1M_pop\":\"1188895\",\"total\":12089787},\"day\":\"2021-06-09\",\"time\":\"2021-06-09T00:00:02+00:00\"}]}"
        ));

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
        assertThat(covidHistoryData.equals(expectedStats)).isEqualTo(true);
    }

    @Test
    public void whenGetCovidHistoryForNonExistingCountry_thenCovidHistoryDataShouldBeEmpty() {
        Mockito.when(webClientMock.get()).thenReturn(uriSpecMock);
        Mockito.when(uriSpecMock.uri(ArgumentMatchers.<String>notNull())).thenReturn(headersSpecMock);
        Mockito.when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
        Mockito.when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(
                "{\"get\":\"history\",\"parameters\":{\"country\":\"Wrong Country\",\"day\":\"2021-06-09\"},\"errors\":[],\"results\":0,\"response\":[]}"
        ));

        CovidHistoryData covidHistoryData = covidService.getCovidHistoryDataByCountryAndDate("Wrong Country", "2021-06-09");

        CovidHistoryData expected = new CovidHistoryData("Wrong Country", "2021-06-09");

        assertThat(covidHistoryData).isEqualTo(expected);
    }

    @Test
    public void whenGetCovidHistoryForDateWithNoData_thenCovidHistoryDataShouldBeEmpty() {
        Mockito.when(webClientMock.get()).thenReturn(uriSpecMock);
        Mockito.when(uriSpecMock.uri(ArgumentMatchers.<String>notNull())).thenReturn(headersSpecMock);
        Mockito.when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
        Mockito.when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(
                "{\"get\":\"history\",\"parameters\":{\"country\":\"Portugal\",\"day\":\"2010-01-01\"},\"errors\":[],\"results\":0,\"response\":[]}"
        ));

        CovidHistoryData covidHistoryData = covidService.getCovidHistoryDataByCountryAndDate("Portugal", "2010-01-01");

        CovidHistoryData expected = new CovidHistoryData("Portugal", "2010-01-01");

        assertThat(covidHistoryData).isEqualTo(expected);
    }
}
