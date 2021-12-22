package top.nextnet.cli;

import fr.pantheonsorbonne.ufr27.miage.dto.Booking;

public interface UserInterface {
    void showErrorMessage(String errorMessage);

    void showSuccessMessage(String s);

    String getCustomerEmail();

    boolean checkIdentity();

    String getAddressDestination();

}
