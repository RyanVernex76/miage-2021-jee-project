package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class PassengerDaoImpl implements PassengerDao{

    @PersistenceContext(name="mysql")
    EntityManager em;

    @Override
    public Passenger getPassenger(int id) throws PassengerNotFoundException {
        try{
            Passenger p = (Passenger) em.createQuery("Select p from Passenger p where p.id=:id").setParameter("id", id).getSingleResult();
            return p;
        }
        catch (NoResultException e){
            throw new PassengerNotFoundException(id);
        }
    }

    @Override
    public void insertNewPassenger(Passenger p) {
        em.persist(p);
    }
}
