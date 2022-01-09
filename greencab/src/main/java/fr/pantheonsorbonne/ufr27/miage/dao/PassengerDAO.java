package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;
import fr.pantheonsorbonne.ufr27.miage.model.User;


public interface PassengerDAO {
     User findMatchingPassenger(String email,String pwd)  throws PassengerNotFoundException;
}
