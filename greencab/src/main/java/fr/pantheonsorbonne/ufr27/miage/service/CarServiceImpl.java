package fr.pantheonsorbonne.ufr27.miage.service;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dao.AutonomousCarDao;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class CarServiceImpl implements CarService{

    @Inject
    AutonomousCarDao carDao;

    @Override
    public CarPosition setAvailable(CarPosition car) {
        try{
            this.carDao.setAvailable(car.getCarId());
            this.carDao.setCarPosition(car);
        }catch (CarNotFoundException e){
            this.carDao.insertNewCar(car.getCarId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return car;
    }

    @Override
    public CarPosition notifyRecharge(CarPosition car) {
        // TO IMPLEMENT
        return null;
    }
}
