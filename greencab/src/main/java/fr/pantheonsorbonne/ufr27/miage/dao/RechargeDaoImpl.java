package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.RechargeNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Recharge;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class RechargeDaoImpl implements RechargeDao{

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public void insertNewRecharge(Recharge r) {
        em.persist(r);
    }

    @Override
    public Recharge getRecharge(int id) throws RechargeNotFoundException {
        try{
            return em.find(Recharge.class, id);
        }
        catch (NoResultException e){
            throw new RechargeNotFoundException(id);
        }
    }

    @Override
    public Recharge[] getJuicerRecharges(int juicerId) {
        List results = em.createQuery
                ("SELECT r from Recharge r WHERE r.juicer=:juicer")
                .setParameter("juicer", juicerId)
                .getResultList();
        Recharge[] recharges = new Recharge[results.size()];

        for(int i = 0; i < results.size(); i++)
            recharges[i] = (Recharge) results.get(i);

        return recharges;
    }
}
