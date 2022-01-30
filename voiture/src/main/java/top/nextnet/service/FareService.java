package top.nextnet.service;

import top.nextnet.model.Car;
import top.nextnet.model.FareWaiting;

public interface FareService {

    void handleFare(FareWaiting f, Car c);
}
