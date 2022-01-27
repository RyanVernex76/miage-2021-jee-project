package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.service.CarService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CarGateway {

    @Inject
    CarService carService;

    public CarPosition setAvailable(CarPosition car){
        return carService.setAvailable(car);
    }

    public CarPosition notifyRecharge(CarPosition car){
        return carService.notifyRecharge(car);
    }
}
