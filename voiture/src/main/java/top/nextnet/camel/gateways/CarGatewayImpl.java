package top.nextnet.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dto.CarAvailable;
import fr.pantheonsorbonne.ufr27.miage.dto.CarRecharge;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import top.nextnet.service.CarGateway;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class CarGatewayImpl implements CarGateway {

    @Inject
    CamelContext context;



    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.carId")
    Integer carId;


    @Override
    public void notifyAvailability(boolean available) {
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:available", new CarAvailable(carId, available));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void notifyRecharge(boolean recharge) {
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:recharge", new CarRecharge(carId, recharge));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void sendFareToGreenCab(Fare fare) {
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:fare", fare);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
