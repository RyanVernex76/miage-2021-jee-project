package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Position;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ElementNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;

public interface AutonomousCarDao {

    AutonomousCar getCar(int carId) throws CarNotFoundException;

    void setAvailable(int carId) throws CarNotFoundException;

    void setCarPosition(CarPosition carPos) throws ElementNotFoundException;

    AutonomousCar[] getAvailableCars() throws CarNotFoundException;

    AutonomousCar[] getNeedRechargeCars() throws CarNotFoundException;

    void setNeedRecharge(int carId, boolean need) throws CarNotFoundException;

    Position getPosition(int carId) throws ElementNotFoundException;
}
