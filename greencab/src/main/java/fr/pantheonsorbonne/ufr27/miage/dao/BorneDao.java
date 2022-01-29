package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.BorneNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ChargingPoint;

public interface BorneDao {
    ChargingPoint getBorne(int borneId) throws BorneNotFoundException;
}
