package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;

public interface AutonomousCarDao {

    AutonomousCar getCar(int carId) throws CarNotFoundException;

    void setAvailable(int carId) throws CarNotFoundException;

    void insertNewCar(int carId);

    void setCarPosition(CarPosition carPos);
}
