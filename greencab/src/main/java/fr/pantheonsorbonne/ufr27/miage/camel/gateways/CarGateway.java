package fr.pantheonsorbonne.ufr27.miage.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dao.ChargingPointDao;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Recharge;
import fr.pantheonsorbonne.ufr27.miage.exception.ChargingPointNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.CarService;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class CarGateway {

    @Inject
    CarService carService;

    @Inject
    CamelContext context;

    @Inject
    ChargingPointDao chargingPointDao;

    public CarPosition setAvailable(CarPosition car){
        return carService.setAvailable(car);
    }

    public CarPosition initRecharge(CarPosition cp){
        return this.carService.initRecharge(cp);
    }

    public CarPosition notifyCarRechargeFinished(Recharge recharge){
        CarPosition cp = new CarPosition();
        try{
            cp = new CarPosition(recharge.getCarId(),
                    chargingPointDao.getChargingPoint(recharge.getChargintPointId()).getAddress());

            try(ProducerTemplate producer = context.createProducerTemplate()){
                producer.sendBody("direct:rechargeFinished", cp);
            } catch (IOException e){
                e.printStackTrace();
            }
        }catch(ChargingPointNotFoundException e){
            System.out.println(e.getMessage());
        }finally {
            return cp;
        }

    }
}
