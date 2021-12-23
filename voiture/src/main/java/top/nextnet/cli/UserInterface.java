package top.nextnet.cli;

public interface UserInterface {
    void showErrorMessage(String errorMessage);

    void showSuccessMessage(String s);

    void showInfoMessage(String infoMessage);


    String getCustomerEmail();

    int checkIdentity();

    String getAddressDestination();

}
