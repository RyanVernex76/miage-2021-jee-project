package top.nextnet.dao;

import top.nextnet.exception.CarNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class CarDaoImpl implements CarDao{

    @PersistenceContext(name="mysql")
    EntityManager em;

    @Override
    public int getMaxKmBeforeRecharge(int carId) throws CarNotFoundException {
        try{
            int nb = (int) em.createQuery("SELECT C.max_km FROM CAR AS C WHERE C.car_id=:id").setParameter("id", carId).getSingleResult();
            return nb;
        }
        catch (NoResultException e){
            throw new CarNotFoundException(carId);
        }
    }

    @Override
    public int getCurrentKm(int carId) throws CarNotFoundException {
        try{
            int nb = (int) em.createQuery("SELECT C.current_km FROM CAR AS C WHERE C.car_id=:id").setParameter("id", carId).getSingleResult();
            return nb;
        }
        catch (NoResultException e){
            throw new CarNotFoundException(carId);
        }
    }
}
