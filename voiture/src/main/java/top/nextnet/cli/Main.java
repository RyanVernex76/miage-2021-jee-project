package top.nextnet.cli;

import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import picocli.CommandLine.Command;
import top.nextnet.service.CarGateway;


import javax.inject.Inject;
import java.util.Date;

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

                carGateway.sendFareToGreenCab(new Fare(infosFare[0], new Date(), passengerId, carId));

            } catch (Exception e) {
                carInterface.showErrorMessage(e.getMessage());
            }
        }


    }

}
