package fr.pantheonsorbonne.ufr27.miage.camel;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dao.AutonomousCarDao;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;
import fr.pantheonsorbonne.ufr27.miage.service.CarService;
import fr.pantheonsorbonne.ufr27.miage.service.FareService;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class FareGateway {

    @Inject
    FareService fareService;

    @Inject
    CamelContext context;

    @Inject
    AutonomousCarDao carDao;

    @Inject
    CarService carService;

    public Fare register(Fare fare) {

        return fareService.register(fare);
    }

    public void sendFareToAvailableCar(Fare fare){
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:bookFare", fare);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean checkFarePossible(Fare f) throws CarNotFoundException, IOException, InterruptedException, ApiException {
        AutonomousCar[] cars = carDao.getAvailableCars();
        // return false if no car are available for the fare
        if(cars.length < 1)
            return false;

        for (AutonomousCar c: cars) {
            // return true as soon as 1 car is close enought to handle the fare
            if(carService.checkDistance(c.getId(), f.getDeparture()))
                return true;
        }
        // return false if no available car is close enough to handle the fare
        return false;
    }


}

