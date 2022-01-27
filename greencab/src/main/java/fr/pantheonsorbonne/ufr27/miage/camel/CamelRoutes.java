package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.dto.ETicket;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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
    CarGateway carHandler;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        from("jms:" + jmsPrefix + "fare")//
                .log("fare received: ${in.headers}")//
                .unmarshal().json(Fare.class)//
                .bean(fareHandler, "register").marshal().json()
        ;

        from("jms:" + jmsPrefix + "available")//
                .log("car is available: ${in.headers}")//
                .unmarshal().json(Fare.class)//
                .bean(carHandler, "setAvailable").marshal().json()
        ;

        from("jms:" + jmsPrefix + "recharge")//
                .log("car need recharge: ${in.headers}")//
                .unmarshal().json(Fare.class)//
                .bean(carHandler, "notifyRecharge").marshal().json()
        ;

        from("direct:ticketCancel")
                .marshal().json()
                .to("jms:topic:" + jmsPrefix + "cancellation");

    }

    private static class ExpiredTransitionalTicketProcessor implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            //https://camel.apache.org/manual/exception-clause.html
            CamelExecutionException caused = (CamelExecutionException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);


            exchange.getMessage().setBody(((ExpiredTransitionalTicketException) caused.getCause()).getExpiredTicketId());
        }
    }
}
