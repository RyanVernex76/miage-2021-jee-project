package top.nextnet.dao;

import top.nextnet.model.FareWaiting;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
@ActivateRequestContext
public class FareWaitingDaoImpl implements  FareWaitingDao{

    @PersistenceContext(name="mysql")
    EntityManager em;

    @Override
    public boolean hasNext() {
        return em.createQuery("SELECT f FROM FareWaiting f").getResultList().size() > 0;
    }


    @Override
    public FareWaiting getFareWaiting() {

        FareWaiting f = (FareWaiting) em.createQuery("SELECT f FROM FareWaiting f ORDER BY f.date").getResultList().get(0);
        return f;
    }

    @Override
    @Transactional
    public void insertFareToQueue(FareWaiting f) {
        em.persist(f);
    }

    private FareWaiting getFareWaitingById(int id){
        return em.find(FareWaiting.class, id);
    }

    @Override
    @Transactional
    public void removeFareFromQueue(FareWaiting f) {
        em.remove(getFareWaitingById(f.getId()));
    }


}
