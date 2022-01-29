package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;
import fr.pantheonsorbonne.ufr27.miage.model.PositionableElement;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class AutonomousCarDaoImpl implements  AutonomousCarDao{

    @PersistenceContext(name="mysql")
    EntityManager em;

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
    public void setCarPosition(CarPosition carPos) {
        PositionableElement pe = new PositionableElement(carPos.getCarId(), null);
        em.persist(pe);
    }
}
