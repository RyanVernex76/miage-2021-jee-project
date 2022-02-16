package top.nextnet.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import top.nextnet.camel.gateways.CarGateway;
import top.nextnet.cli.RunnerData;
import top.nextnet.cli.UserInterfaceCLI;
import top.nextnet.model.Car;
import top.nextnet.model.FareInfo;
import top.nextnet.model.FareWaiting;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FareServiceImpl implements FareService{

    @Inject
    UserInterfaceCLI carInterface;

    @Inject
    CarGateway carGateway;

    @Override
    public void handleFare(FareWaiting fw, Car c) {
        Fare f = new Fare(fw.getDeparture(), fw.getPassengerId(), fw.getDate());
        f.setCarId(c.getId());

        TextIO textIO = TextIoFactory.getTextIO();
        carInterface.accept(textIO, new RunnerData(""));
            try {
                if (!carInterface.checkIdentity(f.getPassengerId())) {
                    return;
                }
                String dest = carInterface.getAddressDestination();
                FareInfo infos = carGateway.getDistanceAndDurationFare(fw.getDeparture(), dest);
                carInterface.showInfoMessage("Destination is " + infos.getDistance() + "km away." + "\nWe will be arriving in " + infos.getDuration() + " minutes.");
                f.setDestination(dest);
                f.setDistance(infos.getDistance());
                carInterface.traveling();
                f.calculatePrice();
                carGateway.sendFareToGreenCab(f);
                carInterface.showInfoMessage("Price of this fare is " + f.getPrice() + "€. GreenCab will charge you in a few minutes.\n Have a nice day !");
                //update currentKm for car
                if (carGateway.checkNeedRecharge(c.getId())) {
                    carGateway.notifyRecharge(c.getId(), dest);
                    carInterface.showErrorMessage("This car needs a recharge.");
                    return;
                }
                carGateway.notifyAvailability(c.getId(), dest);

            } catch (Exception e) {
                carInterface.showErrorMessage(e.getMessage());
            }
    }
}
