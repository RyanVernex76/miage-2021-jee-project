package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Position;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ElementNotFoundException;
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
            return em.find(AutonomousCar.class, carId);
        }
        catch (NoResultException e){
            throw new CarNotFoundException(carId);
        }
    }

    @Override
    @Transactional
    public void setAvailable(int carId, boolean available) throws CarNotFoundException {
        AutonomousCar car = this.getCar(carId);
        car.setAvailable(available);
        em.persist(car);
    }

    @Override
    @Transactional
    public void setCarPosition(CarPosition carPos) throws ElementNotFoundException {
        try{
            PositionableElement pe = em.find(PositionableElement.class, carPos.getCarId());
            Position newLoc = maps.getTranslatedCoordonates(carPos.getPosition());
            pe.setLatitude(newLoc.getLat());
            pe.setLongitude(newLoc.getLon());

            em.persist(pe);
        }catch (NoResultException e){
            throw new ElementNotFoundException(carPos.getCarId());
        }


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
    public AutonomousCar[] getNeedRechargeCars() throws CarNotFoundException {
        List cars = em.createQuery("SELECT c from AutonomousCar c" +
                " where c.needRecharge=true AND c.id" +
                " not in (SELECT r.car.id from Recharge r where r.state='waiting')").getResultList();
        AutonomousCar[] needRechargeCars = new AutonomousCar[cars.size()];

        for(int i = 0; i < cars.size(); i++)
            needRechargeCars[i] = (AutonomousCar) cars.get(i);

        return needRechargeCars;
    }

    @Override
    @Transactional
    public void setNeedRecharge(int carId, boolean need) throws CarNotFoundException {
        try{
            AutonomousCar c = em.find(AutonomousCar.class, carId);
            c.setNeedRecharge(need);
            em.persist(c);

        } catch (NoResultException e){
            throw new CarNotFoundException(carId);
        }
    }

    @Override
    public Position getPosition(int carId) throws ElementNotFoundException {
        try{
            PositionableElement pe = em.find(PositionableElement.class, carId);
            return new Position(pe.getLatitude(), pe.getLongitude());
        }catch (NoResultException e){
            throw new ElementNotFoundException(carId);
        }
    }


}
