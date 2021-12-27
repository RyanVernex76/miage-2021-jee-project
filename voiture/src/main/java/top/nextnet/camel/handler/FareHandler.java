package top.nextnet.camel.handler;

import fr.pantheonsorbonne.ufr27.miage.dto.Booking;
import fr.pantheonsorbonne.ufr27.miage.dto.ETicket;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import top.nextnet.dao.CarDao;
import top.nextnet.exception.CarNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@ApplicationScoped
public class FareHandler {

    @Inject
    CarDao carDao;

    @Handler
    public void onFareSent(Exchange exchange) throws CarNotFoundException {
        Fare f = exchange.getMessage().getBody(Fare.class);

        carDao.updateCurrentKm(f.getCarId(), f.getDistance());
    }
}
