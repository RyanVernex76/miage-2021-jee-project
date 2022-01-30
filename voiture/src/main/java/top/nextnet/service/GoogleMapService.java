package top.nextnet.service;


import com.google.maps.errors.ApiException;
import top.nextnet.model.FareInfo;

import java.io.IOException;

public interface GoogleMapService {

    FareInfo getDistanceAndDuration(String addressOrigin, String addressDestination) throws IOException, InterruptedException, ApiException;

    double getDistance(String dep, String arv) throws ApiException, IOException, InterruptedException;

}
