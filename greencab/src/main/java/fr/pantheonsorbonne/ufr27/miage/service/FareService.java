package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dto.Fare;

public interface FareService {

    Fare register(Fare fare);

    void sendMailValidation(Fare f);
}
