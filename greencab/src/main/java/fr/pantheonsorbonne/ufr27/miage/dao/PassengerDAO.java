package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.PassengerDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Customer;
import fr.pantheonsorbonne.ufr27.miage.model.User;

public interface PassengerDAO {

    User createNewPassenger(PassengerDTO passengerDTO);

    Integer numberPassengerWithEmail(String email);
}
