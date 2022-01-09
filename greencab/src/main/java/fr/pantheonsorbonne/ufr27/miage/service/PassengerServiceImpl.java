package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.PassengerDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.PassengerDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerAlreadyExistException;

public class PassengerServiceImpl implements PassengerService{

    private PassengerDAO passengerDAO;

    @Override
    public void  addNewPassenger(PassengerDTO passengerDTO) throws PassengerAlreadyExistException {

        if (!passengerDAO.numberPassengerWithEmail(passengerDTO.getEmail()).equals(0)) {

            throw new PassengerAlreadyExistException();
        }
        passengerDAO.createNewPassenger(passengerDTO);
    }
}
