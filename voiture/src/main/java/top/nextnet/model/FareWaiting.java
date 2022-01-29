package top.nextnet.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Fare_Waiting")
@Entity
public class FareWaiting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "passenger_id", nullable = false)
    private Integer passengerId;

    @Column(name = "date")
    private Date date;

    @Lob
    @Column(name = "departure")
    private String departure;

    public FareWaiting(int passengerId, String departure, Date date) {
        this.passengerId = passengerId;
        this.departure = departure;
        this.date = date;
    }

    public FareWaiting() {

    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}