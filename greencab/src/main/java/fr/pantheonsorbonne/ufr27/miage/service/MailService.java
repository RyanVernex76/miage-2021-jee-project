package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Juicer;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;

public interface MailService {

    public void sendEmailPassenger(Passenger passenger);

    public void sendEmailJuicer(Juicer juicer);
}
