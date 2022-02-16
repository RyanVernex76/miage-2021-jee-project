package top.nextnet.camel.gateways;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import top.nextnet.exception.CarNotFoundException;
import top.nextnet.model.Car;
import top.nextnet.model.DistanceCarFare;
import top.nextnet.model.FareInfo;

import java.io.IOException;

public interface CarGateway {
    void notifyAvailability(int carId, String pos);

    void notifyRecharge(int carId, String pos);

    boolean checkNeedRecharge(int carId) throws CarNotFoundException;

    FareInfo getDistanceAndDurationFare(String origin, String dest);

    void sendFareToGreenCab(Fare fare);

    Car getCar(int carId) throws CarNotFoundException;

    void calculateDistanceCarPassenger(DistanceCarFare dcf) throws IOException, InterruptedException, ApiException;

}
