package top.nextnet.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Booking;
import fr.pantheonsorbonne.ufr27.miage.dto.CancelationNotice;
import fr.pantheonsorbonne.ufr27.miage.dto.ETicket;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import top.nextnet.cli.UserInterface;
import top.nextnet.service.TicketingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.carId")
    Integer carId;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.user")
    String smtpUser;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.password")
    String smtpPassword;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.host")
    String smtpHost;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.port")
    String smtpPort;

    @Inject
    top.nextnet.camel.handler.BookingResponseHandler BookingResponseHandler;

    @Inject
    TicketingService ticketingService;

    @Inject
    UserInterface eCommerce;

    @Inject
    CamelContext camelContext;


    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);


        from("direct:fare")//
                .marshal().json()
                .to("jms:" + jmsPrefix + "fare");


        from("jms:topic:" + jmsPrefix + "cancellation")
                .log("cancellation notice ${body} ${headers}")
                .filter(header("carId").isEqualTo(carId))

                .unmarshal().json(CancelationNotice.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        CancelationNotice notice = exchange.getMessage().getMandatoryBody(CancelationNotice.class);
                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("to", notice.getEmail());
                        exchange.getMessage().setHeader("from", "vendor@miage.dev");
                        exchange.getMessage().setHeader("contentType", "text/html");
                        exchange.getMessage().setHeader("subject", "cancellation notice for venue");
                        exchange.getMessage().setBody("Venue for your ticket " + notice.getTicketId() + " has been cancelled. Contact vendor for refund");
                    }
                })
                .log("cancellation notice ${body} ${headers}")
                .to("smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword + "&contentType=")
                .bean(eCommerce, "showErrorMessage");


    }
}
