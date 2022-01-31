package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Fare;

public interface FareDao {

    void saveFare(Fare f);

    Fare getFare(int id);

    Fare[] getPassengerFares(int passengerId) throws PassengerNotFoundException;
}
