package fr.pantheonsorbonne.ufr27.miage.model;

import fr.pantheonsorbonne.ufr27.miage.dto.Position;

import javax.persistence.*;

@Table(name = "Positionable_element")
@Entity
public class PositionableElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "element_id", nullable = false)
    private Integer id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    public PositionableElement(int elementId, Position pos) {
        this.id = elementId;
        this.latitude = pos.getLat();
        this.longitude = pos.getLon();
    }

    public PositionableElement() {

    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}