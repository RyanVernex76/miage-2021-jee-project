package com.example.camel.handler;

import com.example.dao.RechargeDao;
import com.example.model.RechargeWaiting;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import fr.pantheonsorbonne.ufr27.miage.dto.Recharge;
import org.apache.camel.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RechargeHandler {

    @Inject
    RechargeDao rechargeDao;

    public void onRechargeReceived(Exchange exchange) throws InterruptedException {
        Recharge r = exchange.getMessage().getBody(Recharge.class);

        RechargeWaiting rw = new RechargeWaiting(r.getId(), r.getJuicerId(), r.getCarId());

        this.rechargeDao.insertRechargeToQueue(rw);
    }


}
