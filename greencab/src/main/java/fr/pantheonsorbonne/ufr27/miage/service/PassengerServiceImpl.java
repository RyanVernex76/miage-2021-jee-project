package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.PassengerDAO;

import fr.pantheonsorbonne.ufr27.miage.dto.ConnectionDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.dto.PassengerDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerAlreadyExistException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
@ApplicationScoped
public class PassengerServiceImpl implements PassengerService {

    @Inject
    PassengerDAO passengerDAO;

    @Override
    public Object connectPassenger(ConnectionDTO connectionDTO) throws PassengerNotFoundException {
        try {
            passengerDAO.findMatchingPassenger(connectionDTO);
        } catch (PassengerNotFoundException e) {
           throw e;
        }
        return null;
    }


    @Override
    public void  addNewPassenger(PassengerDTO passengerDTO) throws PassengerAlreadyExistException {

        if (!passengerDAO.numberPassengerWithEmail(passengerDTO.getEmail()).equals(0)) {

            throw new PassengerAlreadyExistException();
        }
        passengerDAO.createNewPassenger(passengerDTO);

    }
}
