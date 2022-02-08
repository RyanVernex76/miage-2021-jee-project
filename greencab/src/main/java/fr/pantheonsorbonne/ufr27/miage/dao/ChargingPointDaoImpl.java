package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.ChargingPointNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ChargingPoint;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ChargingPointDaoImpl implements ChargingPointDao{

    @PersistenceContext(name="mysql")
    EntityManager em;

    @Override
    public ChargingPoint getChargingPoint(int id) throws ChargingPointNotFoundException {
        try{
            return em.find(ChargingPoint.class, id);
        }
        catch (NoResultException e){
            throw new ChargingPointNotFoundException(id);
        }
    }

    @Override
    public ChargingPoint[] getChargingPoints() {
        List results = em.createQuery("SELECT cp from ChargingPoint cp").getResultList();
        ChargingPoint[] points = new ChargingPoint[results.size()];

        for(int i = 0; i < results.size(); i++)
            points[i] = (ChargingPoint) results.get(i);

        return points;
    }

    @Override
    public void insertChargingPoint(ChargingPoint cp) {
        em.persist(cp);
    }
}
