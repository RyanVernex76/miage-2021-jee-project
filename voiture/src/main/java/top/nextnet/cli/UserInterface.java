package top.nextnet.cli;

import com.google.maps.errors.ApiException;
import top.nextnet.exception.CarNotFoundException;
import top.nextnet.model.Car;
import top.nextnet.model.FareWaiting;

import java.io.IOException;

public interface UserInterface {
    void showErrorMessage(String errorMessage);

    void showSuccessMessage(String s);

    void showInfoMessage(String infoMessage);

    void showCarState(Car c);

    boolean checkIdentity(int passengerId);

    String getAddressDestination();

    Car connexionCar() throws CarNotFoundException;

    FareWaiting chooseFareToHandle(FareWaiting[] fares, Car c) throws IOException, InterruptedException, ApiException;

}
