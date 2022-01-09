package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.PassengerDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Customer;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;
import fr.pantheonsorbonne.ufr27.miage.model.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class PassengerDAOImpl implements PassengerDAO{

    EntityManager em;

    @Override
    @Transactional
    public User createNewPassenger(PassengerDTO passengerDTO) {

        User u = new User(passengerDTO.getFirst_name(), passengerDTO.getLast_name(), passengerDTO.getPhone(), passengerDTO.getEmail(), passengerDTO.getPassword());
        em.persist(u);
        Passenger p = new Passenger(u.getId());
        em.persist(p);
        return u;
    }

    @Override
    public Integer numberPassengerWithEmail(String email)
    {
        return (Integer) em.createQuery("Select count(Distinct User) From User u Inner Join Passenger p on u.id = p.id where u.emailAddress =:email").setParameter("email", email).getSingleResult();
    }
}
