package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.AutonomousCarDao;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;

import javax.inject.Inject;

public class CarServiceImpl implements CarService{

    @Inject
    AutonomousCarDao carDao;

    @Override
    public CarPosition setAvailable(CarPosition car) {
        try{
            this.carDao.setAvailable(car.getCarId());
        }catch (CarNotFoundException e){
            this.carDao.insertNewCar(car.getCarId());
        }

        return car;
    }

    @Override
    public CarPosition notifyRecharge(CarPosition car) {
        // TO IMPLEMENT
        return null;
    }
}
