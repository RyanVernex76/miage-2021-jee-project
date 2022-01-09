package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Passenger {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer id;

<<<<<<< HEAD
    public Passenger(int id) {
=======
    public Passenger(Integer id){
>>>>>>> c861269d252e2944bb7164dd1445c8d6bd8f6270
        this.id = id;
    }

    public Passenger() {

    }

<<<<<<< HEAD

=======
>>>>>>> c861269d252e2944bb7164dd1445c8d6bd8f6270
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}