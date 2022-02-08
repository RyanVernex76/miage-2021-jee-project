package com.example.dao;

import com.example.exception.BorneNotFoundException;
import com.example.model.Borne;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@ApplicationScoped
@ActivateRequestContext
public class BorneDaoImpl implements BorneDao {

    @PersistenceContext(name="mysql")
    EntityManager em;

    @Override
    public Borne getBorne(int borneId) throws BorneNotFoundException {
        try {
            return em.find(Borne.class, borneId);
        }
        catch(NoResultException e)
        {
            throw new BorneNotFoundException(borneId);
        }
    }
}
