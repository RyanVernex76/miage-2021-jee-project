package fr.pantheonsorbonne.ufr27.miage.service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
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
    public Position getTranslatedCoordonates(String address){
        Position p = new Position();
        try{
            GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY_GEOCODING).build();
            GeocodingResult[] source = GeocodingApi.geocode(context, address).await();

            p = new Position(source[0].geometry.location.lat, source[0].geometry.location.lng);
        }catch (IOException | InterruptedException | ApiException e){
            e.printStackTrace();
        }

        return p;
    }

    @Override
    public double getDistance(String dep, String arv) {
        double dist = 0;
        try{
            GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY_GEOCODING).build();
            GeocodingResult[] source = GeocodingApi.geocode(context, dep).await();
            GeocodingResult[] dest = GeocodingApi.geocode(context, arv).await();

            DistanceMatrix matrix = DistanceMatrixApi.getDistanceMatrix(context,
                    new String[]{source[0].formattedAddress},
                    new String[]{dest[0].formattedAddress}).await();

            dist = matrix.rows[0].elements[0].distance.inMeters;
        }
        catch (IOException | InterruptedException | ApiException e){
            e.printStackTrace();
        }

        return dist;
    }

    @Override
    public String getAddress(Position p){
        String address = "";

        try{
            GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY_GEOCODING).build();
            GeocodingResult[] resp = GeocodingApi.reverseGeocode(context, new LatLng(p.getLat(), p.getLon())).await();
            address = resp[0].formattedAddress;
        }
        catch (IOException | InterruptedException | ApiException e){
            e.printStackTrace();
        }
        return address;
    }
}
