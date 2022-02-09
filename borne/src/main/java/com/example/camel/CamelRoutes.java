package com.example.camel;

import com.example.camel.handler.RechargeHandler;
import fr.pantheonsorbonne.ufr27.miage.dto.Recharge;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Inject
    RechargeHandler rechargeHandler;

    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);

        // Receives recharge from greenCab
        from("jms:" + jmsPrefix + "newRecharge")
                .unmarshal().json(Recharge.class)
               .bean(rechargeHandler, "onRechargeReceived");

        // Send recharge back to GreenCab
        from("direct:rechargeDone")
                .marshal().json()
                .to("jms:" + jmsPrefix + "rechargeDone");

    }
}
