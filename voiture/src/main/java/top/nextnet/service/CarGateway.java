package top.nextnet.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import top.nextnet.exception.CarNotFoundException;

public interface CarGateway {
    void notifyAvailability(boolean available);

    void notifyRecharge();

    boolean checkNeedRecharge() throws CarNotFoundException;

    int [] getDistanceAndDurationFare(String origin, String dest);

    double getPriceFare();

    void sendFareToGreenCab(Fare fare);
}
