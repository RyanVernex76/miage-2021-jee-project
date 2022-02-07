package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Autonomous_Car")
@Entity
public class AutonomousCar {
    @Id
    @Column(name = "autonomous_car_id", nullable = false)
    private Integer id;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "battery_level")
    private Integer batteryLevel;

    @Column(name = "brand", length = 100)
    private String brand;

    @Column(name = "license_plate", nullable = false, length = 20)
    private String licensePlate;

    @Column(name = "year")
    private Integer year;

    @Column(name = "need_recharge")
    private Boolean needRecharge;

    public AutonomousCar(int carId, boolean available, String plate) {
        this.id = carId;
        this.available = true;
        this.licensePlate = plate;
    }

    public AutonomousCar() {

    }

    public Boolean getNeedRecharge() {
        return needRecharge;
    }

    public void setNeedRecharge(Boolean needRecharge) {
        this.needRecharge = needRecharge;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}