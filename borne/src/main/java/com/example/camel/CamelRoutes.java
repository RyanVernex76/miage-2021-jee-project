package com.example.camel;

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
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);

        from("direct:available")
                .marshal().json()
                .to("jms:" + jmsPrefix + "juicerAvailable");

        from("direct:available")
                .marshal().json()
                .to("jms:" + jmsPrefix + "carAvailable");
    }
}
