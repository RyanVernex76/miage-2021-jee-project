package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Recharge;

public interface RechargeService {

    void sendNewRecharge(Recharge r);

    void registerRecharge(fr.pantheonsorbonne.ufr27.miage.dto.Recharge r);
}
