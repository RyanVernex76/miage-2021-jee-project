package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@ActivateRequestContext
public class PassengerDaoImpl implements PassengerDao{

    @PersistenceContext(name="mysql")
    EntityManager em;

    @Override
    public Passenger getPassenger(int id) throws PassengerNotFoundException {
        try{
            return em.find(Passenger.class, id);
        }
        catch (NoResultException e){
            throw new PassengerNotFoundException(id);
        }
    }

    @Override
    public Passenger[] getPassengers() {
        List results = em.createQuery("SELECT p from Passenger p").getResultList();
        Passenger[] passengers = new Passenger[results.size()];

        for(int i = 0; i < results.size(); i++)
            passengers[i] = (Passenger) results.get(i);

        return passengers;
    }

    @Override
    @Transactional
    public void insertNewPassenger(Passenger p) {
        em.persist(p);
    }
}