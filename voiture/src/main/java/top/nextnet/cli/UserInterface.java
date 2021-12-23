package top.nextnet.cli;

public interface UserInterface {
    void showErrorMessage(String errorMessage);

    void showSuccessMessage(String s);

    String getCustomerEmail();

    int checkIdentity();

    String getAddressDestination();

}
