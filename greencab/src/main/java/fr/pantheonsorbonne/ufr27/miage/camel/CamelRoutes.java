package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Invoice;
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
    JuicerGateway juicerHandler;

    @Inject
    InvoiceGateway invoiceHandler;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        // Receives invoice
        from("jms:" + jmsPrefix + "invoice")
                .log("invoice received: ${in.headers}")
                .unmarshal().json(Invoice.class)
                .bean(invoiceHandler, "register").marshal().json()
        ;

        // NEED TO MERGE WITH JUICER BRANCH TO GET IMPL
        // Sets juicer available or not
//        from("jms:" + jmsPrefix + "juicerAvailable")
//                .log("juicer available: ${in.headers}")
//                .unmarshal().json(Invoice.class)
//                .bean(juicerHandler, "setAvailable").marshal().json()
//        ;

        // Sets car available or not --> need to merge with greenCabRV
//        from("jms:" + jmsPrefix + "available")//
//                .log("car is available: ${in.headers}")//
//                .unmarshal().json(Fare.class)//
//                .bean(carHandler, "setAvailable").marshal().json()
//        ;
    }
}
