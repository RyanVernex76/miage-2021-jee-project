package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fare_id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    private AutonomousCar car;

    @ManyToOne(optional = false)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "date", nullable = false)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public AutonomousCar getCar() {
        return car;
    }

    public void setCar(AutonomousCar car) {
        this.car = car;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}