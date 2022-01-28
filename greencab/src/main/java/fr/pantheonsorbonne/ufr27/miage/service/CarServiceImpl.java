package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.AutonomousCarDao;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;

import javax.inject.Inject;

public class CarServiceImpl implements CarService{

    @Inject
    AutonomousCarDao carDao;

    @Override
    public CarPosition setAvailable(CarPosition car) {
        this.carDao.setAvailable(car.getCarId());
        return null;
    }

    @Override
    public CarPosition notifyRecharge(CarPosition car) {
        // TO IMPLEMENT
        return null;
    }
}
