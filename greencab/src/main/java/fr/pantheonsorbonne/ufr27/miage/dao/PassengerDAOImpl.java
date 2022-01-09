package fr.pantheonsorbonne.ufr27.miage.dao;
import fr.pantheonsorbonne.ufr27.miage.dto.ConnectionDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;
import fr.pantheonsorbonne.ufr27.miage.model.User;
import fr.pantheonsorbonne.ufr27.miage.dto.PassengerDTO;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@ApplicationScoped
public class PassengerDAOImpl implements PassengerDAO {

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    public User findMatchingPassenger(ConnectionDTO connectionDTO) throws  PassengerNotFoundException {
        try {
            Query q = em.createQuery("Select u from User u inner join Passenger p on u.id = p.id where u.emailAddress =:email and u.password =:pwd");
            q.setParameter("email", connectionDTO.getEmail());
            q.setParameter("pwd", connectionDTO.getPassword());
            q.getSingleResult();
            User u = (User) q;
            return u;
        } catch (NoResultException e) {
            throw new PassengerNotFoundException();
        }
    }

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
