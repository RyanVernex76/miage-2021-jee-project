package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class FareServiceImpl implements FareService {

    @PersistenceContext
    EntityManager em;


    @Override
    @Transactional
    public Fare register(Fare fare) {
        try {
            AutonomousCar car = em.find(AutonomousCar.class, fare.getCarId());
            Passenger pass = em.find(Passenger.class, fare.getPassengerId());
            fr.pantheonsorbonne.ufr27.miage.model.Fare f = new fr.pantheonsorbonne.ufr27.miage.model.Fare();
            f.setCar(car);
            f.setDate(fare.getDate());
            f.setPassenger(pass);
            f.setPrice(fare.getPrice());
            em.persist(f);
            }
        catch (NonUniqueResultException | NoResultException e) {
            //throw new UnsuficientQuotaForVenueException(booking.getVenueId());
        }
        return fare;
    }
}
