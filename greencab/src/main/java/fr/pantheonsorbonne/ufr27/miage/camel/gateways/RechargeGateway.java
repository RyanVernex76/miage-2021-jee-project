package fr.pantheonsorbonne.ufr27.miage.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dao.JuicerDao;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Recharge;
import fr.pantheonsorbonne.ufr27.miage.model.Juicer;
import fr.pantheonsorbonne.ufr27.miage.service.MailService;
import fr.pantheonsorbonne.ufr27.miage.service.RechargeService;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class RechargeGateway {

    @Inject
    CamelContext context;

    @Inject
    RechargeService rechargeService;

    @Inject
    JuicerDao juicerDao;

    @Inject
    MailService mailService;

    public void registerRecharge(Recharge r){
        this.rechargeService.registerRecharge(r);
    }


    public void notifyRecharge(CarPosition car){
        Juicer[] juicers = this.juicerDao.getJuicers();
        for (Juicer j : juicers) {
            this.mailService.sendNotfifRechargeJuicer(j, car);
        }
    }

    public void sendNewRecharge(fr.pantheonsorbonne.ufr27.miage.model.Recharge r) {
        fr.pantheonsorbonne.ufr27.miage.dto.Recharge toSend = new fr.pantheonsorbonne.ufr27.miage.dto.Recharge();
        toSend.setCarId(r.getCar().getId());
        toSend.setJuicerId(r.getJuicer().getId());
        toSend.setId(r.getId());
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:newRecharge", toSend);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
