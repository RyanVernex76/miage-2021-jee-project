package fr.pantheonsorbonne.ufr27.miage.dao;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Position;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;
import fr.pantheonsorbonne.ufr27.miage.model.PositionableElement;
import fr.pantheonsorbonne.ufr27.miage.service.GoogleMapService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@ApplicationScoped
@ActivateRequestContext
public class AutonomousCarDaoImpl implements  AutonomousCarDao{

    @PersistenceContext(name="mysql")
    EntityManager em;

    @Inject
    GoogleMapService maps;

    @Override
    public AutonomousCar getCar(int carId) throws CarNotFoundException {
        try{
            AutonomousCar c = em.find(AutonomousCar.class, carId);
            return c;
        }
        catch (NoResultException e){
            throw new CarNotFoundException(carId);
        }
    }

    @Override
    @Transactional
    public void setAvailable(int carId) throws CarNotFoundException {
        AutonomousCar car = this.getCar(carId);
        car.setAvailable(true);
        em.persist(car);
    }

    @Override
    @Transactional
    public void insertNewCar(int carId) {
        AutonomousCar newCar = new AutonomousCar(carId, true, "XX-" + carId + "-CAB");
    }

    @Override
    @Transactional
    public void setCarPosition(CarPosition carPos) throws IOException, InterruptedException, ApiException {
        PositionableElement pe = new PositionableElement(carPos.getCarId(), maps.getTranslatedCoordonates(carPos.getPosition()));
        em.createNativeQuery("UPDATE Positionable_element(element_id, latitude, longitude) " +
                "VALUES(?, ?, ?)")
                .setParameter(1, pe.getId())
                .setParameter(2, pe.getLatitude())
                .setParameter(3, pe.getLongitude())
                .executeUpdate();

    }

    @Override
    public AutonomousCar[] getAvailableCars() throws CarNotFoundException {
        List cars = em.createQuery("SELECT c from AutonomousCar c where c.available=true").getResultList();
        AutonomousCar[] availableCars = new AutonomousCar[] {};

        for(int i = 0; i < cars.size(); i++)
            availableCars[i] = (AutonomousCar) cars.get(i);

        return availableCars;
    }

    @Override
    public Position getPosition(int carId) {
        PositionableElement pe = em.find(PositionableElement.class, carId);
        return new Position(pe.getLatitude(), pe.getLongitude());
    }


}
