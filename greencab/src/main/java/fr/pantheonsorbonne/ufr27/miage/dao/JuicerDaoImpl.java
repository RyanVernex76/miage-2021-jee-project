package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.JuicerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Juicer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@ActivateRequestContext
public class JuicerDaoImpl implements JuicerDao{

    @PersistenceContext(name="mysql")
    EntityManager em;

    @Override
    public Juicer getJuicer(int id) throws JuicerNotFoundException {
        try{
            return em.find(Juicer.class, id);
        }
        catch (NoResultException e){
            throw new JuicerNotFoundException(id);
        }
    }

    @Override
    public Juicer[] getJuicers() {
        List results = em.createQuery("SELECT p from Juicer p").getResultList();
        Juicer[] juicers = new Juicer[results.size()];

        for(int i = 0; i < results.size(); i++)
            juicers[i] = (Juicer) results.get(i);

        return juicers;
    }

    @Override
    @Transactional
    public void insertNewJuicer(Juicer j) {
        em.persist(j.getJuicerAccount());
        em.persist(j);
    }

    @Override
    @Transactional
    public void deleteJuicer(int id) throws JuicerNotFoundException {
        em.createQuery("delete from Recharge r where r.juicer=:id")
                .setParameter("id", this.getJuicer(id))
                .executeUpdate();
        int isSuccessful = em.createQuery("delete from Juicer p where p.id=:id")
                .setParameter("id", this.getJuicer(id).getId())
                .executeUpdate();
        if (isSuccessful == 0){
            throw new JuicerNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void changeJuicerPassword(int id, String newPassword) throws JuicerNotFoundException{
        try{
            this.getJuicer(id);
        } catch(JuicerNotFoundException e){
            throw e;
        }
        em.createQuery("update Juicer p set p.password=:newPassword where p.id=:id")
                .setParameter("newPassword", newPassword)
                .setParameter("id", id)
                .executeUpdate();
    }
}
