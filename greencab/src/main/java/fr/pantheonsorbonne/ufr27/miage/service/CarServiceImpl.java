package fr.pantheonsorbonne.ufr27.miage.service;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dao.AutonomousCarDao;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Position;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

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
            this.carDao.setAvailable(car.getCarId());
            this.carDao.setCarPosition(car);
        }catch (CarNotFoundException | IOException |
                ApiException | InterruptedException e) {
            e.printStackTrace();
        }

        return car;
    }

    @Override
    public CarPosition notifyRecharge(CarPosition car) {
        try{
            this.carDao.setNeedRecharge(car.getCarId(), true);
            this.carDao.setCarPosition(car);
        }catch (CarNotFoundException | IOException |
                ApiException | InterruptedException e){
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public boolean checkDistance(int carId, String dest) {
        Position carCurrentPosition = carDao.getPosition(carId);
        double distance = maps.getDistance(maps.getAddress(carCurrentPosition), dest);

        return distance <= maxDistance;
    }
}
