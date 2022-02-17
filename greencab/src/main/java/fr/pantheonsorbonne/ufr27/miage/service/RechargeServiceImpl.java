package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.AutonomousCarDao;
import fr.pantheonsorbonne.ufr27.miage.dao.ChargingPointDao;
import fr.pantheonsorbonne.ufr27.miage.dao.JuicerDao;
import fr.pantheonsorbonne.ufr27.miage.dao.RechargeDao;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ChargingPointNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ElementNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.JuicerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Recharge;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RechargeServiceImpl implements RechargeService{

    @Inject
    RechargeDao rechargeDao;

    @Inject
    AutonomousCarDao carDao;

    @Inject
    JuicerDao juicerDao;

    @Inject
    ChargingPointDao chargingPointDao;

    @Override
    public void registerRecharge(fr.pantheonsorbonne.ufr27.miage.dto.Recharge r) {
        try{
            Recharge rech = new Recharge();
            rech.setId(r.getId());
            rech.setCar(carDao.getCar(r.getCarId()));
            rech.setJuicer(juicerDao.getJuicer(r.getJuicerId()));
            rech.setChargingPoint(chargingPointDao.getChargingPoint(r.getChargintPointId()));
            rech.setCost(r.getCost());
            rech.setState(r.getState());

            this.rechargeDao.updateFinishedRecharge(rech);
            this.carDao.setNeedRecharge(r.getCarId(), false);
            this.carDao.setAvailable(r.getCarId(), true);
            this.carDao.setCarPosition(
                    new CarPosition(r.getCarId(),
                            chargingPointDao.getChargingPoint(r.getChargintPointId()).getAddress()
                    )
            );
        }catch (CarNotFoundException | JuicerNotFoundException
                | ChargingPointNotFoundException | ElementNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
