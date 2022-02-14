package com.example.cli;

public interface UserInterface {
    void showErrorMessage(String errorMessage);

    void showSuccessMessage(String s);

    void showInfoMessage(String infoMessage);

    int checkIdentity();

    int getCarId();

    int getAmountToCharge();

    void exit();
}
