package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.BorneNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ChargingPoint;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public class BorneDaoImpl implements BorneDao {

    @PersistenceContext(name="mysql")
    EntityManager em;

    @Override
    public ChargingPoint getBorne(int borneId) throws BorneNotFoundException {
        try{
            ChargingPoint c = (ChargingPoint) em.createQuery("Select c from ChargingPoint c where c.id=:id").setParameter("id", borneId).getSingleResult();
            return c;
        }
        catch (NoResultException e){
            throw new BorneNotFoundException(borneId);
        }
    }
}
