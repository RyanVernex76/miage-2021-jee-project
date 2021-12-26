package top.nextnet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    @Column(name = "car_id", nullable = false)
    private Integer id;

    @Column(name = "max_km")
    private Integer maxKm;

    @Column(name = "current_km")
    private Integer currentKm;

    public Integer getCurrentKm() {
        return currentKm;
    }

    public void setCurrentKm(Integer currentKm) {
        this.currentKm = currentKm;
    }

    public Integer getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(Integer maxKm) {
        this.maxKm = maxKm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}