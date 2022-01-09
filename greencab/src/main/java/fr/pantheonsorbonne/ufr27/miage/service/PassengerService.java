package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;

public interface PassengerService {
    public Object connectPassenger(String email, String pwd) throws PassengerNotFoundException;

    public void  addNewPassenger();

}
