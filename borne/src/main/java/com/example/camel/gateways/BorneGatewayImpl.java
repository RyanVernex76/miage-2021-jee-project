package com.example.camel.gateways;

import com.example.service.BorneGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.Recharge;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class BorneGatewayImpl implements BorneGateway {

    @Inject
    CamelContext context;

    @Override
    public void sendFinishedRecharge(Recharge r) {
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:rechargeDone", r);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
