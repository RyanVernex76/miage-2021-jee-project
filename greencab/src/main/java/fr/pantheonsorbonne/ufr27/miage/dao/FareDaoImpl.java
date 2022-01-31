package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Fare;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class FareDaoImpl implements FareDao{

    @PersistenceContext(name="mysql")
    EntityManager em;

    @Inject
    PassengerDao passengerDao;

    @Override
    public void saveFare(Fare f) {
        em.persist(f);
    }

    @Override
    public Fare getFare(int id) {
        return em.find(Fare.class, id);
    }

    @Override
    public Fare[] getPassengerFares(int passengerId) throws PassengerNotFoundException {
        List results = em.createQuery("select f from Fare f where f.passenger=:passenger")
                .setParameter("passenger", passengerDao.getPassenger(passengerId))
                .getResultList();

        Fare[] fares = new Fare[results.size()];
        for(int i = 0; i < results.size(); i++)
            fares[i] = (Fare) results.get(i);

        return fares;
    }
}
