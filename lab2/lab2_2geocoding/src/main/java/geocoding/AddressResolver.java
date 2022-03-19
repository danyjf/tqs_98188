package geocoding;

import connection.ISimpleHttpClient;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Formatter;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressResolver {
    private ISimpleHttpClient simpleHttpClient;

    public AddressResolver(ISimpleHttpClient simpleHttpClient) {
        this.simpleHttpClient = simpleHttpClient;
    }

    public Optional<Address> findAddressForLocation(double latitude, double longitude) throws URISyntaxException, ParseException, IOException {
        String apiKey = "iBOFiJjeiqs83AkCwqa7oQqzAdOF7ywG";

        URIBuilder uriBuilder = new URIBuilder("http://open.mapquestapi.com/geocoding/v1/reverse");
        uriBuilder.addParameter("key", apiKey);
        uriBuilder.addParameter("location", new Formatter().format(Locale.US, "%.6f,%.6f", latitude, longitude).toString());
        uriBuilder.addParameter("includeRoadMetadata", "true");

        String apiResponse = simpleHttpClient.doHttpGet(uriBuilder.build().toString());
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, apiResponse);

        JSONObject obj = (JSONObject) new JSONParser().parse(apiResponse);

        obj = (JSONObject) ((JSONArray) obj.get("results")).get(0);

        if(((JSONArray) obj.get("locations")).isEmpty()) {
            return Optional.empty();
        } else {
            JSONObject address = (JSONObject) ((JSONArray) obj.get("locations")).get(0);

            String road = (String) address.get("street");
            String city = (String) address.get("adminArea5");
            String state = (String) address.get("adminArea3");
            String zip = (String) address.get("postalCode");

            return Optional.of(new Address(road, state, city, zip, null));
        }
    }
}
