package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.JuicerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.RechargeNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Recharge;

public interface RechargeDao {

    void insertNewRecharge(Recharge r);

    Recharge getRecharge(int id) throws RechargeNotFoundException;

    Recharge[] getJuicerRecharges(int juicerId) throws JuicerNotFoundException;
}
