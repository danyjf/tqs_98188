package pt.ua.tqs.covidincidence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import pt.ua.tqs.covidincidence.service.CovidService;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class CovidServiceTest {
    @Mock
    private WebClient webClient;

    @InjectMocks
    private CovidService covidService;

    @BeforeEach
    public void setUp() {
        Mockito.when(
                webClient
                        .get()
                        .uri("https://covid-19-statistics.p.rapidapi.com/reports?date=2021-04-14&region_name=Spain&region_province=Pais Vasco")
                        .retrieve()
                        .bodyToMono(String.class)
                        .log()
        ).thenReturn(Mono.just(
                "{\"data\":[{\"date\":\"2021-04-14\",\"confirmed\":172237,\"deaths\":4028,\"recovered\":16160,\"confirmed_diff\":1005,\"deaths_diff\":2,\"recovered_diff\":0,\"last_update\":\"2021-04-15 04:20:54\",\"active\":152049,\"active_diff\":1003,\"fatality_rate\":0.0234,\"region\":{\"iso\":\"ESP\",\"name\":\"Spain\",\"province\":\"Pais Vasco\",\"lat\":\"42.9896\",\"long\":\"-2.6189\",\"cities\":[]}}]}"
        ));
    }
}
