package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.*;

@Table(name = "Charging_point")
@Entity
public class ChargingPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charging_point_id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}