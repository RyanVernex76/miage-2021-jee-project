package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.camel.gateways.CarGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateways.FareGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateways.RechargeGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
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
    FareGateway fareHandler;

    @Inject
    RechargeGateway rechargeGateway;

    @Inject
    CarGateway carHandler;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        // Send fare object to car
        from("direct:bookFare")//
                .log("fare sent to cars")//
                .bean(fareHandler, "notifyEmailPassenger")
                .marshal().json()
                .to("jms:" + jmsPrefix + "bookFare")
        ;

        // Send Recharge object to Borne when Juicer take it in charge
        from("direct:newRecharge")
                .marshal().json()
                .to("jms:" + jmsPrefix + "newRecharge");


        // Receives fare from car => Charge passenger
        from("jms:" + jmsPrefix + "fare")//
                .log("fare received: ${in.headers}")//
                .unmarshal().json(Fare.class)//
                .bean(fareHandler, "register").marshal().json()
        ;

        from("jms:" + jmsPrefix + "rechargeDone")
                .unmarshal().json(Recharge.class)
                .bean(rechargeGateway, "registerRecharge")
                .marshal().json();

        // Receives CarPosition object => register available + latest position
        from("jms:" + jmsPrefix + "carAvailable")//
                .log("car is available: ${in.headers}")//
                .unmarshal().json(CarPosition.class)//
                .bean(carHandler, "setAvailable").marshal().json()
        ;

        // Receives CarPosition object => notify needRecharge + latest location
        from("jms:" + jmsPrefix + "carRecharge")//
                .log("car need recharge: ${in.headers}")//
                .unmarshal().json(CarPosition.class)//
                .bean(rechargeGateway, "notifyRecharge")
                .bean(carHandler, "notifyRecharge")
                .marshal().json()
        ;
    }
}
