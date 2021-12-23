package top.nextnet.resource;


import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface GoogleMapService {

    /*2 results
    [0] => Distance of the fare in km
    [1] => Duration of the fare in minutes
     */
    int [] getDistanceAndDuration(String addressOrigin, String addressDestination) throws IOException, InterruptedException, ApiException;
}
