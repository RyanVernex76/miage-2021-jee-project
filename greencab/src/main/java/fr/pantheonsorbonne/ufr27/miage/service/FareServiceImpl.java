package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.dao.AutonomousCarDao;
import fr.pantheonsorbonne.ufr27.miage.dao.FareDao;
import fr.pantheonsorbonne.ufr27.miage.dao.PassengerDao;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;

@ApplicationScoped
public class FareServiceImpl implements FareService {

    @Inject
    AutonomousCarDao carDao;

    @Inject
    PassengerDao passengerDao;

    @Inject
    FareDao fareDao;


    @Override
    @Transactional
    public Fare register(Fare fare) {
        try {
            AutonomousCar car = carDao.getCar(fare.getCarId());
            Passenger pass = passengerDao.getPassenger(fare.getPassengerId());
            fr.pantheonsorbonne.ufr27.miage.model.Fare f = new fr.pantheonsorbonne.ufr27.miage.model.Fare(
                    car, pass, fare.getDeparture(), fare.getDestination(), fare.getPrice(), fare.getDate()
            );

            fareDao.saveFare(f);
            }
        catch (NonUniqueResultException | NoResultException e) {
            //throw new UnsuficientQuotaForVenueException(booking.getVenueId());
        }
        catch (CarNotFoundException e){

        }
        catch (PassengerNotFoundException e){

        }
        return fare;
    }
}
