package top.nextnet.resource;


import com.google.maps.errors.ApiException;
import top.nextnet.model.FareInfo;

import java.io.IOException;

public interface GoogleMapService {

    FareInfo getDistanceAndDuration(String addressOrigin, String addressDestination) throws IOException, InterruptedException, ApiException;
}
