package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Fare;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class FareDaoImpl implements FareDao{

    @PersistenceContext(name="mysql")
    EntityManager em;

    @Override
    public void saveFare(Fare f) {
        em.persist(f);
    }
}
