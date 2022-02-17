package top.nextnet.camel.handler;

import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import top.nextnet.dao.CarDao;
import top.nextnet.exception.CarNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CarRechargeHandler {

    @Inject
    CarDao carDao;

    public void setCarAfterRecharge(CarPosition car){
        try{
            this.carDao.resetCurrentKm(car.getCarId());
            this.carDao.setPosition(car.getCarId(), car.getPosition());
        }catch (CarNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
}
