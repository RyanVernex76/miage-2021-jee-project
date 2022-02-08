package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.JuicerNotFoundException;
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
}
