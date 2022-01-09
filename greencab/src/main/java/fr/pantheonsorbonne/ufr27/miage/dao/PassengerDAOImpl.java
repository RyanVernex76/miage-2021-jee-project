package fr.pantheonsorbonne.ufr27.miage.dao;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomerNotFoundException;

import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;
import fr.pantheonsorbonne.ufr27.miage.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@ApplicationScoped
public class PassengerDAOImpl implements PassengerDAO {

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    public User findMatchingPassenger(String email,String pwd) throws  PassengerNotFoundException {
        try {
            Query q = em.createQuery("Select u from User u inner join Passenger p on u.id = p.id where u.emailAddress =:email and u.password =:pwd");
            q.setParameter("email", email);
            q.setParameter("pwd",pwd);
            q.getSingleResult();
            User u = (User) q;
            return u;
        } catch (NoResultException e) {
            throw new PassengerNotFoundException();
        }
    }
}
