package top.nextnet.cli;

import top.nextnet.exception.CarNotFoundException;
import top.nextnet.model.Car;

public interface UserInterface {
    void showErrorMessage(String errorMessage);

    void showSuccessMessage(String s);

    void showInfoMessage(String infoMessage);

    void showCarState(Car c);

    int checkIdentity();

    String getAddressDestination();

    Car connexionCar() throws CarNotFoundException;

}
