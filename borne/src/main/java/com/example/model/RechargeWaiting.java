package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Recharge_Waiting")
@Entity
public class RechargeWaiting {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "juicer_id", nullable = false)
    private Integer juicerId;

    @Column(name = "car_id", nullable = false)
    private Integer carId;

    public RechargeWaiting(int id, int juicerId, int carId){
        this.id = id;
        this.juicerId = juicerId;
        this.carId = carId;
    }

    public RechargeWaiting(){}

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getJuicerId() {
        return juicerId;
    }

    public void setJuicerId(Integer juicerId) {
        this.juicerId = juicerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}