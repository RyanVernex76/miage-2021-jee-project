package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Recharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recharge_id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    private AutonomousCar car;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "time")
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "charging_point_id")
    private ChargingPoint chargingPoint;

    @ManyToOne(optional = false)
    @JoinColumn(name = "juicer_id", nullable = false)
    private Juicer juicer;

    @Lob
    @Column(name = "state", nullable = false)
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Juicer getJuicer() {
        return juicer;
    }

    public void setJuicer(Juicer juicer) {
        this.juicer = juicer;
    }

    public ChargingPoint getChargingPoint() {
        return chargingPoint;
    }

    public void setChargingPoint(ChargingPoint chargingPoint) {
        this.chargingPoint = chargingPoint;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
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