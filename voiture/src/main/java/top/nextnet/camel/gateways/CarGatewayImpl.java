package top.nextnet.camel.gateways;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import top.nextnet.dao.CarDao;
import top.nextnet.exception.CarNotFoundException;
import top.nextnet.model.Car;
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


    @Override
    public void notifyAvailability(int carId, String pos) {
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:available",
                    new CarPosition(carId, pos));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void notifyRecharge(int carId, String pos) {
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:recharge",
                    new CarPosition(carId, pos));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkNeedRecharge(int carId) throws CarNotFoundException {
        return carDao.getCurrentKm(carId) > carDao.getMaxKmBeforeRecharge(carId);
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
    public void sendFareToGreenCab(Fare fare) {
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:fare", fare);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Car getCar(int carId) throws CarNotFoundException {
        return carDao.getCar(carId);
    }
}
