package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.PassengerDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;

import javax.inject.Inject;

public class PassengerServiceImpl implements PassengerService {

    @Inject
    PassengerDAO passengerDAO;

    @Override
    public Object connectPassenger(String email, String pwd) throws PassengerNotFoundException {
        try {
            passengerDAO.findMatchingPassenger(email,pwd);
        } catch (PassengerNotFoundException e) {
           throw e;
        }
        return null;
    }

    @Override
    public void addNewPassenger() {

    }
}
