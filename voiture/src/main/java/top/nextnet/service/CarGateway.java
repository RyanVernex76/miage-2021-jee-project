package top.nextnet.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Fare;

public interface CarGateway {
    void notifyAvailability(boolean available);

    void needRecharge();

    void sendFareToGreenCab(Fare fare);
}
