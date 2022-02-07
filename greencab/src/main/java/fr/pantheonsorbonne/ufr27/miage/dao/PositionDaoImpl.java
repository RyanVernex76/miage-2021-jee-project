package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Position;
import fr.pantheonsorbonne.ufr27.miage.exception.ElementNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.PositionableElement;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@ApplicationScoped
public class PositionDaoImpl implements PositionDao{

    @Inject
    EntityManager em;

    @Override
    public Position getPosition(int elementId) throws ElementNotFoundException {
        try{
            PositionableElement pe = em.find(PositionableElement.class, elementId);
            return new Position(pe.getLatitude(), pe.getLongitude());
        }catch (NoResultException e){
            throw new ElementNotFoundException(elementId);
        }
    }
}
