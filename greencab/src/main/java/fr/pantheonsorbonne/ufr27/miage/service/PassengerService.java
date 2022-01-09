package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.ConnectionDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.dto.PassengerDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerAlreadyExistException;


public interface PassengerService {
    public Object connectPassenger(ConnectionDTO connectionDTO) throws PassengerNotFoundException;

    public void  addNewPassenger(PassengerDTO passengerDTO) throws PassengerAlreadyExistException;

}
