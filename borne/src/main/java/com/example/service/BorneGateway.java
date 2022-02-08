package com.example.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Recharge;

public interface BorneGateway {

    void sendFinishedRecharge(Recharge r);
}
