package pt.ua.tqs.covidincidence.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ua.tqs.covidincidence.cache.CachedData;
import pt.ua.tqs.covidincidence.model.CovidHistoryData;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CovidControllerIT {
    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CachedData cachedData;

    @Test
    public void whenGetValidCountryAndDate_thenStatus200() {
        CovidHistoryData expected = new CovidHistoryData(
                "Portugal",
                "2021-06-09",
                "+598",
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

        ResponseEntity<CovidHistoryData> response = testRestTemplate
                .exchange("/covid/Portugal/2021-06-09", HttpMethod.GET, null, new ParameterizedTypeReference<CovidHistoryData>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expected);
    }
}
