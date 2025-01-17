package top.nextnet.cli;

import com.google.maps.errors.ApiException;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;
import top.nextnet.exception.CarNotFoundException;
import top.nextnet.model.Car;
import top.nextnet.model.DistanceCarFare;
import top.nextnet.model.FareWaiting;
import top.nextnet.camel.gateways.CarGateway;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.io.IOException;


@ApplicationScoped
public class UserInterfaceCLIImpl implements UserInterfaceCLI {


    TextTerminal<?> terminal;
    TextIO textIO;

    @Inject
    CarGateway carGateway;

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
    public void showCarState(Car c) {
        String msg = "Autonomous car n°"+ c.getId() + "" +
                "\nKilometers before recharge : " + (c.getMaxKm() - c.getCurrentKm());
        showInfoMessage(msg);
    }

    @Override
    public void showSuccessMessage(String s) {
        terminal.getProperties().setPromptColor(Color.GREEN);
        terminal.println(s);
        terminal.getProperties().setPromptColor(Color.white);
    }

    @Override
    public boolean checkIdentity(int passengerId) {
        terminal.println("Please scan the QR Code GreenCab sent you for this fare.");
        // Add QR Code checks
        int i = 3;
        int input = this.textIO.newIntInputReader().read("passengerId");
        while((input != passengerId) && i > 0){
            this.showErrorMessage("Wrong id ! ");
            i--;
            if(i!=0){
                this.showErrorMessage("Please try again. You have " + i + " chance(s) left.");
                input = this.textIO.newIntInputReader().read("passengerId");
            }

        }
        if(input != passengerId){
            this.showErrorMessage("This was your last chance. This fare is canceled. Please contact Greencab fro further details");
            return false;
        }

        return true;
    }

    @Override
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

    @Override
    public Car connexionCar() throws CarNotFoundException {
        terminal.println("Please enter the id of the car you want to use (number)");
        int id = textIO.newIntInputReader().read("ID");
        return carGateway.getCar(id);
    }

    @Override
    public FareWaiting chooseFareToHandle(FareWaiting[] fares, Car c) throws ApiException, IOException, InterruptedException {
        terminal.println("Please enter the id of the fare you want to handle : ");
        Integer[] fareIds = new Integer[fares.length];

        DistanceCarFare dcf;
        for(int i = 0; i < fares.length; i++){
            dcf = new DistanceCarFare(fares[i], c);
            carGateway.calculateDistanceCarPassenger(dcf);
            terminal.println(dcf.toString());
            fareIds[i] = fares[i].getId();
        }

        int input = textIO.newIntInputReader()
                .withNumberedPossibleValues(fareIds)
                .read("Fare ID");

        FareWaiting choice = new FareWaiting();
        for (int i = 0; i < fares.length; i++)
            if(fares[i].getId() == input)
                choice = fares[i];

        return choice;
    }

    @Override
    public boolean stop() {
        terminal.println("Do you want to handle an other fare or stop ?");
        String input = textIO.newStringInputReader()
                .withPossibleValues("Continue", "Stop")
                .read("Choice");
        return input == "Stop";
    }

    @Override
    public void exit(){
        terminal.println("Press ENTER key to exit.");
        terminal.read(true);
        System.exit(0);
    }


}
