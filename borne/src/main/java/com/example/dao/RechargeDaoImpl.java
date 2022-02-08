package com.example.dao;

import com.example.exception.RechargeNotFoundException;
import com.example.model.RechargeWaiting;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@ActivateRequestContext
public class RechargeDaoImpl implements RechargeDao{

    @Inject
    EntityManager em;

    @Override
    public boolean hasNext() {
        return em.createQuery("SELECT r FROM RechargeWaiting r").getResultList().size() > 0;
    }


    @Override
    public RechargeWaiting getRecharge(int rechargeId) throws RechargeNotFoundException {
        try{
            return em.find(RechargeWaiting.class, rechargeId);
        }
        catch (NoResultException e){
            throw new RechargeNotFoundException(rechargeId);
        }
    }

    @Override
    public RechargeWaiting[] getRechargesWaiting(){
        List<RechargeWaiting> fares = em.createQuery("SELECT r FROM RechargeWaiting r").getResultList();
        RechargeWaiting [] tab = new RechargeWaiting[fares.size()];
        for (int i = 0; i < fares.size(); i ++) {
            tab[i] = fares.get(i);
        }

        return tab;
    }

    @Override
    @Transactional
    public void insertRechargeToQueue(RechargeWaiting f) {
        em.persist(f);
    }

    private RechargeWaiting getFareWaitingById(int id){
        return em.find(RechargeWaiting.class, id);
    }

    @Override
    @Transactional
    public void removeRechargeFromQueue(RechargeWaiting f) {
        em.remove(getFareWaitingById(f.getId()));
    }


}
