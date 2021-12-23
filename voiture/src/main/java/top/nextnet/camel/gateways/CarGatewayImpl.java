package top.nextnet.camel.gateways;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dto.CarAvailable;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import top.nextnet.cli.UserInterface;
import top.nextnet.dao.CarDao;
import top.nextnet.exception.CarNotFoundException;
import top.nextnet.resource.GoogleMapService;
import top.nextnet.service.CarGateway;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class CarGatewayImpl implements CarGateway {

    @Inject
    CamelContext context;

    @Inject
    GoogleMapService maps;

    @Inject
    CarDao carDao;

    @Inject
    UserInterface carInterface;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.carId")
    Integer carId;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.carRechargeMarge")
    Integer margeRecharge;


    @Override
    public void notifyAvailability(boolean available) {
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:available", new CarAvailable(carId, available));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void notifyRecharge() {
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:recharge", carId);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkNeedRecharge() throws CarNotFoundException {
        return carDao.getCurrentKm(carId) + margeRecharge > carDao.getMaxKmBeforeRecharge(carId);
    }

    @Override
    public int [] getDistanceAndDurationFare(String origin, String dest) {
        try {

            return maps.getDistanceAndDuration(origin, dest);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double getPriceFare() {
        return 0;
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
