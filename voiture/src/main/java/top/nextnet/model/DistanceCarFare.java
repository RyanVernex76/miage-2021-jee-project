package top.nextnet.model;

import com.google.maps.errors.ApiException;
import top.nextnet.service.GoogleMapService;

import javax.inject.Inject;
import java.io.IOException;

public class DistanceCarFare {

    private FareWaiting fw;
    private Car c;
    private double distance;


    public DistanceCarFare(FareWaiting fw, Car c) throws IOException, InterruptedException, ApiException {
        this.fw = fw;
        this.c = c;
    }

    public DistanceCarFare(){}

    public FareWaiting getFw() {
        return fw;
    }

    public void setFw(FareWaiting fw) {
        this.fw = fw;
    }

    public Car getC() {
        return c;
    }

    public void setC(Car c) {
        this.c = c;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    @Override
    public String toString() {
        return this.fw.toString() + " - "
                + (this.distance / 1000) + " km away";
    }
}
