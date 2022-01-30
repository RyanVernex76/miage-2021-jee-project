package top.nextnet.cli;

import top.nextnet.exception.CarNotFoundException;
import top.nextnet.model.Car;
import top.nextnet.model.FareWaiting;

public interface UserInterface {
    void showErrorMessage(String errorMessage);

    void showSuccessMessage(String s);

    void showInfoMessage(String infoMessage);

    void showCarState(Car c);

    boolean checkIdentity(int passengerId);

    String getAddressDestination();

    Car connexionCar() throws CarNotFoundException;

    FareWaiting chooseFareToHandle(FareWaiting[] fares);

}
