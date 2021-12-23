package top.nextnet.cli;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import top.nextnet.resource.VendorService;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;


@ApplicationScoped
public class UserInterfaceCLIImpl implements UserInterfaceCLI {

    @Inject
    @RestClient
    VendorService vendorService;


    TextTerminal<?> terminal;
    TextIO textIO;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.carId")
    Integer carId;

    @Override
    public void accept(TextIO textIO, RunnerData runnerData) {
        this.textIO = textIO;
        terminal = textIO.getTextTerminal();
   }

    @Override
    public void showErrorMessage(String errorMessage) {
        terminal.getProperties().setPromptColor(Color.RED);
        terminal.println(errorMessage);
        terminal.getProperties().setPromptColor(Color.white);
    }

    @Override
    public void showInfoMessage(String infoMessage) {
        terminal.getProperties().setPromptColor(Color.YELLOW);
        terminal.println(infoMessage);
        terminal.getProperties().setPromptColor(Color.white);
    }

    @Override
    public void showSuccessMessage(String s) {
        terminal.getProperties().setPromptColor(Color.GREEN);
        terminal.println(s);
        terminal.getProperties().setPromptColor(Color.white);
    }


    @Override
    public String getCustomerEmail() {
        return this.textIO.newStringInputReader().read("Customer Email");

    }

    @Override
    public int checkIdentity() {
        terminal.println("Please scan the QR Code GreenCab sent you for this fare.");
        // Add QR Code checks

        return this.textIO.newIntInputReader().read("passengerId");
    }

    public void traveling(){
        try {
            terminal.println("**** OK the fare will start in a few seconds ****\n" +
                    "**** Please make yourself confortable ****\n");
            Thread.sleep(5000);
            terminal.println("**** We will arrive soon ****");
            Thread.sleep(5000);
            terminal.println("**** We arrived at destination ****\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getAddressDestination() {
        terminal.println("Where do you want to go ?");

        return textIO.newStringInputReader().read("Destination");
    }


}
