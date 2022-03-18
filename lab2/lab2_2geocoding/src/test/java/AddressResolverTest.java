import org.apache.http.client.utils.URIBuilder;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URISyntaxException;
import java.util.Formatter;
import java.util.Locale;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
class AddressResolverTest {
    @Mock
    ISimpleHttpClient mockSimpleHttpClient;

    @InjectMocks
    AddressResolver addressResolver;

    @Test
    void whenResolveAlboiGps_returnCaisAlboiAddress() throws URISyntaxException, ParseException {
        URIBuilder uriBuilder = new URIBuilder("http://open.mapquestapi.com/geocoding/v1/reverse");
        uriBuilder.addParameter("key", "iBOFiJjeiqs83AkCwqa7oQqzAdOF7ywG");
        uriBuilder.addParameter("location", new Formatter().format(Locale.US, "%.6f,%.6f", 40.640661, -8.656688).toString());
        uriBuilder.addParameter("includeRoadMetadata", "true");

        String apiEndpoint = uriBuilder.build().toString();
        String apiResponse = "{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"\\u00A9 2022 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\\u00A9 2022 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":40.640661,\"lng\":-8.656688}},\"locations\":[{\"street\":\"Cais do Alboi\",\"adminArea6\":\"\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Gl\\u00F3ria e Vera Cruz\",\"adminArea5Type\":\"City\",\"adminArea4\":\"\",\"adminArea4Type\":\"County\",\"adminArea3\":\"Centro\",\"adminArea3Type\":\"State\",\"adminArea1\":\"PT\",\"adminArea1Type\":\"Country\",\"postalCode\":\"3800-246\",\"geocodeQualityCode\":\"B1AAA\",\"geocodeQuality\":\"STREET\",\"dragPoint\":false,\"sideOfStreet\":\"N\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":40.640524,\"lng\":-8.656713},\"displayLatLng\":{\"lat\":40.640524,\"lng\":-8.656713},\"mapUrl\":\"http://open.mapquestapi.com/staticmap/v5/map?key=iBOFiJjeiqs83AkCwqa7oQqzAdOF7ywG&type=map&size=225,160&locations=40.64052368145179,-8.656712986761146|marker-sm-50318A-1&scalebar=true&zoom=15&rand=-1313827853\",\"roadMetadata\":null}]}]}";
        when(mockSimpleHttpClient.doHttpGet(apiEndpoint)).thenReturn(apiResponse);

        Optional<Address> result = addressResolver.findAddressForLocation(40.640661, -8.656688);

        assertThat(result, is(Optional.of(new Address("Cais do Alboi", "Centro", "Glória e Vera Cruz", "3800-246", null))));
        Mockito.verify(mockSimpleHttpClient).doHttpGet(apiEndpoint);
    }

    @Test
    void whenBadCoordinates_thenReturnNoValidAddress() throws URISyntaxException, ParseException {
        URIBuilder uriBuilder = new URIBuilder("http://open.mapquestapi.com/geocoding/v1/reverse");
        uriBuilder.addParameter("key", "iBOFiJjeiqs83AkCwqa7oQqzAdOF7ywG");
        uriBuilder.addParameter("location", new Formatter().format(Locale.US, "%.6f,%.6f", -300.0, -810.0).toString());
        uriBuilder.addParameter("includeRoadMetadata", "true");

        String apiEndpoint = uriBuilder.build().toString();
        String apiResponse = "{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"\\u00A9 2022 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\\u00A9 2022 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":-300.0,\"lng\":-810.0}},\"locations\":[]}]}";
        when(mockSimpleHttpClient.doHttpGet(apiEndpoint)).thenReturn(apiResponse);

        Optional<Address> result = addressResolver.findAddressForLocation(-300, -810);

        assertThat(result, is(Optional.empty()));
        Mockito.verify(mockSimpleHttpClient).doHttpGet(apiEndpoint);
    }
}
