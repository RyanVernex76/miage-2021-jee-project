package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.CustomerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Customer;

public interface PassengerDAO {
    Customer findMatchingCustomer(String email) throws CustomerNotFoundException;

    Customer createNewPassenger(String fname, String lname, String email);
}
