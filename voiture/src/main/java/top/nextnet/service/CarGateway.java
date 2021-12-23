package top.nextnet.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Fare;

public interface CarGateway {
    void notifyAvailability(boolean available);

    void notifyRecharge();

    boolean checkNeedRecharge();

    int [] getDistanceAndDurationFare(String origin, String dest);

    double getPriceFare();

    void sendFareToGreenCab(Fare fare);
}
