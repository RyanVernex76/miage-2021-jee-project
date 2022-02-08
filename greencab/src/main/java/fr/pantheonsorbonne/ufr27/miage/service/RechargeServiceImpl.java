package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Recharge;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class RechargeServiceImpl implements RechargeService{

    @Inject
    CamelContext context;

    @Override
    public void sendNewRecharge(Recharge r) {
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
