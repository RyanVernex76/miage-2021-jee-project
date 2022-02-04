package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fare_id", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    private AutonomousCar car;

    @ManyToOne(optional = false)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @Lob
    @Column(name = "`from`")
    private String from;

    @Lob
    @Column(name = "`to`")
    private String to;

    public Fare(AutonomousCar c, Passenger p, String from, String to, double price, Date d){
        this.car = c;
        this.passenger = p;
        this.from = from;
        this.to = to;
        this.price = price;
        this.date = d;
    }

    public Fare(){}

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}