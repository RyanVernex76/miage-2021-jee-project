package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Position;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

@ApplicationScoped
public class GoogleMapServiceImpl implements GoogleMapService{

    static final String API_KEY_GEOCODING = "AIzaSyBAiXb0PdYJofkqCvJP2-7w42ceh1-TlP8";

    @Override
    public Position getTranslatedCoordonates(String address) throws IOException, InterruptedException, ApiException {
        try{
            GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY_GEOCODING).build();
            GeocodingResult[] source = GeocodingApi.geocode(context, address).await();

            return new Position(source[0].geometry.location.lat, source[0].geometry.location.lng);
        }catch (IOException | InterruptedException | ApiException e){
            e.printStackTrace();
            return null;
        }
    }
}
