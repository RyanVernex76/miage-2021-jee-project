package top.nextnet.camel.handler;

import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import org.apache.camel.Exchange;
import top.nextnet.dao.CarDao;
import top.nextnet.exception.CarNotFoundException;
import top.nextnet.service.FareService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FareHandler {

    @Inject
    CarDao carDao;

    @Inject
    FareService fareService;

    public void onFareSent(Exchange exchange) throws CarNotFoundException {
        Fare f = exchange.getMessage().getBody(Fare.class);

        carDao.updateCurrentKm(f.getCarId(), f.getDistance());
    }

    public void onFareReceived(Exchange exchange) {
        Fare f = exchange.getMessage().getBody(Fare.class);

        this.fareService.handleFare(f);
    }

}
