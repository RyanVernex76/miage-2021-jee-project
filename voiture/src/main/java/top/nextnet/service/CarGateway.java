package top.nextnet.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import top.nextnet.exception.CarNotFoundException;
import top.nextnet.model.Car;
import top.nextnet.model.FareInfo;

public interface CarGateway {
    void notifyAvailability(int carId, String pos);

    void notifyRecharge(int carId, String pos);

    boolean checkNeedRecharge(int carId) throws CarNotFoundException;

    FareInfo getDistanceAndDurationFare(String origin, String dest);

    void sendFareToGreenCab(Fare fare);

    Car getCar(int carId) throws CarNotFoundException;
}
