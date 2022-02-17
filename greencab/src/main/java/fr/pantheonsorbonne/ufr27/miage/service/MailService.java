package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import fr.pantheonsorbonne.ufr27.miage.model.Juicer;

public interface MailService {

    void sendMailValidationFare(Fare fare);

    void sendNotfifRechargeJuicer(Juicer juicer, CarPosition car);
}
