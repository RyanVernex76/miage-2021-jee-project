package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.ConnectionDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;
import fr.pantheonsorbonne.ufr27.miage.model.User;
import fr.pantheonsorbonne.ufr27.miage.dto.PassengerDTO;

public interface PassengerDAO {
     User findMatchingPassenger(ConnectionDTO connectionDTO)  throws PassengerNotFoundException;

    User createNewPassenger(PassengerDTO passengerDTO);

    Integer numberPassengerWithEmail(String email);

}
