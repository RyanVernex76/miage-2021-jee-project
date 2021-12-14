package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Card {
    @Id
    @Column(name = "number", nullable = false, length = 20)
    private String id;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "CVV", length = 3)
    private String cvv;

    @ManyToOne(optional = false)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}