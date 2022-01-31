package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;

public interface PassengerDao {

    Passenger getPassenger(int id) throws PassengerNotFoundException;

    Passenger[] getPassengers();

    void insertNewPassenger(Passenger p);

}
