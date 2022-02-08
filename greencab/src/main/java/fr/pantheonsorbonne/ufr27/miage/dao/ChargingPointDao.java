package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.ChargingPointNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ChargingPoint;

public interface ChargingPointDao {

    ChargingPoint getChargingPoint(int id) throws ChargingPointNotFoundException;

    ChargingPoint[] getChargingPoints();

    void insertChargingPoint(ChargingPoint cp);
}
