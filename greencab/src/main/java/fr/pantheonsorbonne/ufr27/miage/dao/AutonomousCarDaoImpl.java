package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;
import fr.pantheonsorbonne.ufr27.miage.model.PositionableElement;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class AutonomousCarDaoImpl implements  AutonomousCarDao{

    @PersistenceContext(name="mysql")
    EntityManager em;

    @Override
    public AutonomousCar getCar(int carId) throws CarNotFoundException {
        try{
            AutonomousCar c = (AutonomousCar) em.createQuery("Select c from AutonomousCar c where c.id=:id").setParameter("id", carId).getSingleResult();
            return c;
        }
        catch (NoResultException e){
            throw new CarNotFoundException(carId);
        }
    }

    @Override
    public void setAvailable(int carId) throws CarNotFoundException {
        AutonomousCar car = this.getCar(carId);
        car.setAvailable(true);
        em.persist(car);
    }

    @Override
    public void insertNewCar(int carId) {
        AutonomousCar newCar = new AutonomousCar(carId, true, "XX-" + carId + "-CAB");
    }

    @Override
    public void setCarPosition(CarPosition carPos) {
        PositionableElement pe = new PositionableElement(carPos.getCarId(), null);
        em.persist(pe);
    }
}
