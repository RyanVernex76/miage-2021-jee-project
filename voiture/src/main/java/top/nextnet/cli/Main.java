package top.nextnet.cli;

import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import picocli.CommandLine.Command;
import top.nextnet.service.CarGateway;


import javax.inject.Inject;

@Command(name = "greeting", mixinStandardHelpOptions = true)
public class Main implements Runnable {


    @Inject
    UserInterfaceCLI carInterface;

    @Inject
    CarGateway carGateway;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.carId")
    Integer carId;

    @Override
    public void run() {


        TextIO textIO = TextIoFactory.getTextIO();
        carInterface.accept(textIO, new RunnerData(""));


        while (true) {
            try {
                int passengerId = carInterface.checkIdentity();
                String dest = carInterface.getAddressDestination();
                int infosFare[] = carGateway.getDistanceAndDurationFare("12 Avenue Condorcet 91200 Athis-Mons", dest);
                carInterface.showInfoMessage("Destination is " + infosFare[0] + "km away." + "\nWe will be arriving in " + infosFare[1] + " minutes.");
                Fare f = new Fare(infosFare[0], passengerId, carId);
                carInterface.showInfoMessage("Price of this fare is " + f.getPrice() + "€. GreenCab will charge you in a few minutes.\n Have a nice day !");
                carGateway.sendFareToGreenCab(f);
                if(carGateway.checkNeedRecharge()){
                    carGateway.notifyRecharge();
                    carInterface.showErrorMessage("This car needs a recharge.");
                    break;
                }


            } catch (Exception e) {
                carInterface.showErrorMessage(e.getMessage());
            }
        }
    }

}
