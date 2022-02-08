package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Recharge;
import fr.pantheonsorbonne.ufr27.miage.service.RechargeService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RechargeGateway {

    @Inject
    RechargeService rechargeService;

    void registerRecharge(Recharge r){
        this.rechargeService.registerRecharge(r);
    }
}
