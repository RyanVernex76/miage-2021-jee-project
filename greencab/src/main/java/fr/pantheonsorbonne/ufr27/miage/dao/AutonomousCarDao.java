package fr.pantheonsorbonne.ufr27.miage.dao;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;

import java.io.IOException;

public interface AutonomousCarDao {

    AutonomousCar getCar(int carId) throws CarNotFoundException;

    void setAvailable(int carId) throws CarNotFoundException;

    void insertNewCar(int carId);

    void setCarPosition(CarPosition carPos) throws IOException, InterruptedException, ApiException;
}
