package top.nextnet.dao;

import top.nextnet.exception.CarNotFoundException;
import top.nextnet.model.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.persistence.*;
import javax.transaction.Transactional;

@ApplicationScoped
@ActivateRequestContext
public class CarDaoImpl implements CarDao{

    @PersistenceContext(name="mysql")
    EntityManager em;

    @Override
    public int getMaxKmBeforeRecharge(int carId) throws CarNotFoundException {
        try{
            return (int) em.createQuery("SELECT c.maxKm FROM Car c WHERE c.id=:id").setParameter("id", carId).getSingleResult();
        }
        catch (NoResultException e){
            throw new CarNotFoundException(carId);
        }
    }

    @Override
    public int getCurrentKm(int carId) throws CarNotFoundException {
        try{
            return (int) em.createQuery("SELECT c.currentKm FROM Car c WHERE c.id=:id").setParameter("id", carId).getSingleResult();
        }
        catch (NoResultException e){
            throw new CarNotFoundException(carId);
        }
    }

    @Override
    public Car getCar(int carId) throws CarNotFoundException {
        try{
            Car c = (Car) em.createQuery("Select c from Car c where c.id=:id").setParameter("id", carId).getSingleResult();
            return c;
        }
        catch (NoResultException e){
            throw new CarNotFoundException(carId);
        }
    }

    @Override
    @Transactional
    public void updateCurrentKm(int carId, int add) throws CarNotFoundException {
        Car car = this.getCar(carId);
        car.setCurrentKm(car.getCurrentKm() + add);
        em.persist(car);
    }
}
