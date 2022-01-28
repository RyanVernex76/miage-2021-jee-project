package top.nextnet.camel.gateways;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import top.nextnet.model.FareInfo;
import top.nextnet.resource.GoogleMapService;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

@ApplicationScoped
public class GoogleMapServiceImpl implements GoogleMapService {

    static final String API_KEY_GEOCODING = "AIzaSyBAiXb0PdYJofkqCvJP2-7w42ceh1-TlP8";

    public FareInfo getDistanceAndDuration(String addressOrigin, String addressDestination)
            throws IOException, InterruptedException, ApiException {

        GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY_GEOCODING).build();
        GeocodingResult[] source = GeocodingApi.geocode(context, addressOrigin).await();
        GeocodingResult[] dest = GeocodingApi.geocode(context, addressDestination).await();

        DistanceMatrix matrix = DistanceMatrixApi.getDistanceMatrix(context,
                new String[]{source[0].formattedAddress},
                new String[]{dest[0].formattedAddress}).await();

        return new FareInfo(
                Math.toIntExact(matrix.rows[0].elements[0].distance.inMeters / 1000),
                Math.toIntExact(matrix.rows[0].elements[0].duration.inSeconds / 60)
        );
    }
}
