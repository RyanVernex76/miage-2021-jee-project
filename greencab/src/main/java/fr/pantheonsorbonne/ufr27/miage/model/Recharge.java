package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Recharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recharge_id", nullable = false)
    private Integer id;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    private AutonomousCar car;

    @ManyToOne(optional = false)
    @JoinColumn(name = "juicer_id", nullable = false)
    private Juicer juicer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "charging_point_id", nullable = false)
    private ChargingPoint chargingPoint;

    public ChargingPoint getChargingPoint() {
        return chargingPoint;
    }

    public void setChargingPoint(ChargingPoint chargingPoint) {
        this.chargingPoint = chargingPoint;
    }

    public Juicer getJuicer() {
        return juicer;
    }

    public void setJuicer(Juicer juicer) {
        this.juicer = juicer;
    }

    public AutonomousCar getCar() {
        return car;
    }

    public void setCar(AutonomousCar car) {
        this.car = car;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}