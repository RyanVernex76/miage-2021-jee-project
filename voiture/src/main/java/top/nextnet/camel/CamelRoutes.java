package top.nextnet.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import top.nextnet.camel.handler.CarRechargeHandler;
import top.nextnet.camel.handler.FareHandler;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    FareHandler fareHandler;

    @Inject
    CarRechargeHandler carRechargeHandler;

    @Inject
    CamelContext camelContext;


    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);

        //Send fare object to greenCab => Charge passenger
        from("direct:fare")//
                .marshal().json()//
                .to("jms:" + jmsPrefix + "fare")//
                .unmarshal().json(Fare.class)//
                .bean(fareHandler, "onFareSent")
                ;

        //Send CarPosition object to greencab => set car as available and
        // register its position
        from("direct:available")
                .marshal().json()
                .to("jms:" + jmsPrefix + "carAvailable");

        //Send CarPosition object to greencab => notify car need recharge and
        // register its position
        from("direct:recharge")//
                .marshal().json()
                .to("jms:" + jmsPrefix + "carRecharge");

        // Receive fare and treat it
        from("jms:" + jmsPrefix + "bookFare")//
                .log("fare received: ${in.headers}")//
                .unmarshal().json(Fare.class)//
                .bean(fareHandler, "onFareReceived")
        ;

        // Receive CarPosition object from greenCab ==> reset current km
        from("jms:" + jmsPrefix + "rechargeFinished")//
                .log("car is fully recharged")//
                .unmarshal().json(CarPosition.class)//
                .bean(carRechargeHandler, "setCarAfterRecharge")
        ;



        /*from("jms:topic:" + jmsPrefix + "cancellation")
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
                .bean(eCommerce, "showErrorMessage");*/


    }
}
