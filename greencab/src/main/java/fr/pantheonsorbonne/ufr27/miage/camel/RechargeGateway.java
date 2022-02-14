package fr.pantheonsorbonne.ufr27.miage.camel;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dao.AutonomousCarDao;
import fr.pantheonsorbonne.ufr27.miage.dao.ChargingPointDao;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Recharge;
import fr.pantheonsorbonne.ufr27.miage.exception.BorneNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ChargingPointNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.RechargeService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class RechargeGateway {

    @Inject
    RechargeService rechargeService;

    @Inject
    AutonomousCarDao carDao;

    @Inject
    ChargingPointDao chargingPointDao;

    void registerRecharge(Recharge r){
        try{
            this.rechargeService.registerRecharge(r);
            this.carDao.setNeedRecharge(r.getCarId(), false);
            this.carDao.setAvailable(r.getCarId());
            this.carDao.setCarPosition(
                    new CarPosition(r.getCarId(),
                            chargingPointDao.getChargingPoint(r.getChargintPointId()).getAddress()
                    )
            );
        }catch (CarNotFoundException | ChargingPointNotFoundException | IOException | InterruptedException | ApiException e){
            e.printStackTrace();
        }


    }
}
