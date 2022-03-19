package integration;

import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Formatter;
import java.util.Locale;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AddressResolverIT {
    private TqsBasicHttpClient tqsBasicHttpClient;
    private AddressResolver addressResolver;

    @BeforeEach
    public void init() {
        tqsBasicHttpClient = new TqsBasicHttpClient();
        addressResolver = new AddressResolver(tqsBasicHttpClient);
    }

    @Test
    void whenResolveAlboiGps_returnCaisAlboiAddress() throws URISyntaxException, ParseException, IOException {
        URIBuilder uriBuilder = new URIBuilder("http://open.mapquestapi.com/geocoding/v1/reverse");
        uriBuilder.addParameter("key", "iBOFiJjeiqs83AkCwqa7oQqzAdOF7ywG");
        uriBuilder.addParameter("location", new Formatter().format(Locale.US, "%.6f,%.6f", 40.640661, -8.656688).toString());
        uriBuilder.addParameter("includeRoadMetadata", "true");

        Optional<Address> result = addressResolver.findAddressForLocation(40.640661, -8.656688);

        assertThat(result, is(Optional.of(new Address("Cais do Alboi", "Centro", "Glória e Vera Cruz", "3800-246", null))));
    }

    @Test
    void whenBadCoordinates_thenReturnNoValidAddress() throws URISyntaxException, ParseException, IOException {
        URIBuilder uriBuilder = new URIBuilder("http://open.mapquestapi.com/geocoding/v1/reverse");
        uriBuilder.addParameter("key", "iBOFiJjeiqs83AkCwqa7oQqzAdOF7ywG");
        uriBuilder.addParameter("location", new Formatter().format(Locale.US, "%.6f,%.6f", -300.0, -810.0).toString());
        uriBuilder.addParameter("includeRoadMetadata", "true");

        Optional<Address> result = addressResolver.findAddressForLocation(-300, -810);

        assertThat(result, is(Optional.empty()));
    }
}
