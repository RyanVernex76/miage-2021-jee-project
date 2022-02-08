package com.example.service;

import com.example.cli.UserInterfaceCLI;
import com.example.model.Borne;
import com.example.model.RechargeWaiting;
import fr.pantheonsorbonne.ufr27.miage.dto.Recharge;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RechargeServiceImpl implements RechargeService {

    @Inject
    BorneGateway borneGateway;

    @Inject
    UserInterfaceCLI cli;

    @Override
    public void handleRecharge(RechargeWaiting rw, Borne b) {
        Recharge r = new Recharge(rw.getId(), rw.getJuicerId(), rw.getCarId());
        r.setCost(b.getPrice());
        r.setChargintPointId(b.getId());

        cli.showInfoMessage("Please connect the cable to your car in order to start the charge.");

        cli.chargingFull();

        r.setState("done");
        this.borneGateway.sendFinishedRecharge(r);
    }
}
