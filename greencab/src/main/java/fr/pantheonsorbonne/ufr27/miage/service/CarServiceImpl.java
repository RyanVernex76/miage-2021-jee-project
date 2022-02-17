package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.AutonomousCarDao;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Position;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ElementNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


@ApplicationScoped
public class CarServiceImpl implements CarService{

    @Inject
    AutonomousCarDao carDao;

    @Inject
    GoogleMapService maps;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.distanceMax")
    double maxDistance;

    @Override
    public CarPosition setAvailable(CarPosition car) {
        try{
            this.carDao.setAvailable(car.getCarId(), true);
            this.carDao.setCarPosition(car);
        }catch (CarNotFoundException | ElementNotFoundException e){
            System.out.println(e.getMessage());
        }

        return car;
    }

    @Override
    public CarPosition initRecharge(CarPosition car) {
        try{
            this.carDao.setNeedRecharge(car.getCarId(), true);
            this.carDao.setAvailable(car.getCarId(), false);
            this.carDao.setCarPosition(car);
        }catch (CarNotFoundException | ElementNotFoundException e){
            System.out.println(e.getMessage());
        }
        return car;
    }

    @Override
    public boolean checkDistance(int carId, String dest) {
        boolean ok = false;
        try{
            Position carCurrentPosition = carDao.getPosition(carId);
            double distance = maps.getDistance(maps.getAddress(carCurrentPosition), dest);

            ok = distance <= maxDistance;
        }catch (ElementNotFoundException e){
            System.out.println(e.getMessage());
        }
        return ok;
    }
}
