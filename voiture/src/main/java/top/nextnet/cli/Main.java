package top.nextnet.cli;

import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import picocli.CommandLine.Command;
import top.nextnet.exception.CarNotFoundException;
import top.nextnet.model.Car;
import top.nextnet.service.CarGateway;


import javax.inject.Inject;

@Command(name = "greeting", mixinStandardHelpOptions = true)
public class Main implements Runnable {


    @Inject
    UserInterfaceCLI carInterface;

    @Inject
    CarGateway carGateway;

    @Override
    public void run() {


        TextIO textIO = TextIoFactory.getTextIO();
        carInterface.accept(textIO, new RunnerData(""));

        try {
            Car c = carInterface.connexionCar();
            carInterface.showCarState(c);

            while (true) {
                try {
                    int passengerId = carInterface.checkIdentity();
                    String dest = carInterface.getAddressDestination();
                    int infosFare[] = carGateway.getDistanceAndDurationFare("12 Avenue Condorcet 91200 Athis-Mons", dest);
                    carInterface.showInfoMessage("Destination is " + infosFare[0] + "km away." + "\nWe will be arriving in " + infosFare[1] + " minutes.");
                    Fare f = new Fare(infosFare[0], passengerId, c.getId());
                    carInterface.traveling();
                    carGateway.sendFareToGreenCab(f);
                    carInterface.showInfoMessage("Price of this fare is " + f.getPrice() + "€. GreenCab will charge you in a few minutes.\n Have a nice day !");
                    //update currentKm for car
                    if(carGateway.checkNeedRecharge(c.getId())) {
                        carGateway.notifyRecharge(c.getId(), dest);
                        carInterface.showErrorMessage("This car needs a recharge.");
                        break;
                    }
                    carGateway.notifyAvailability(c.getId(), dest);

                } catch (Exception e) {
                    carInterface.showErrorMessage(e.getMessage());
                }
            }
        } catch (CarNotFoundException e) {
            carInterface.showErrorMessage(e.getMessage());
        }
    }

}
