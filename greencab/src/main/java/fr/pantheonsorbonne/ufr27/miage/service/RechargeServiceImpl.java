package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.AutonomousCarDao;
import fr.pantheonsorbonne.ufr27.miage.dao.ChargingPointDao;
import fr.pantheonsorbonne.ufr27.miage.dao.JuicerDao;
import fr.pantheonsorbonne.ufr27.miage.dao.RechargeDao;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ChargingPointNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.JuicerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Recharge;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class RechargeServiceImpl implements RechargeService{

    @Inject
    CamelContext context;

    @Inject
    RechargeDao rechargeDao;

    @Inject
    AutonomousCarDao carDao;

    @Inject
    JuicerDao juicerDao;

    @Inject
    ChargingPointDao chargingPointDao;

    @Override
    public void sendNewRecharge(Recharge r) {
        fr.pantheonsorbonne.ufr27.miage.dto.Recharge toSend = new fr.pantheonsorbonne.ufr27.miage.dto.Recharge();
        toSend.setCarId(r.getCar().getId());
        toSend.setJuicerId(r.getJuicer().getId());
        toSend.setId(r.getId());
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:newRecharge", toSend);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void registerRecharge(fr.pantheonsorbonne.ufr27.miage.dto.Recharge r) {
        try{
            Recharge rech = new Recharge();
            rech.setId(r.getId());
            rech.setCar(carDao.getCar(r.getCarId()));
            rech.setJuicer(juicerDao.getJuicer(r.getJuicerId()));
            rech.setChargingPoint(chargingPointDao.getChargingPoint(r.getChargintPointId()));
            rech.setCost(r.getCost());

            this.rechargeDao.insertNewRecharge(rech);
        }catch (CarNotFoundException | JuicerNotFoundException | ChargingPointNotFoundException e){
            e.printStackTrace();
        }



    }

}
