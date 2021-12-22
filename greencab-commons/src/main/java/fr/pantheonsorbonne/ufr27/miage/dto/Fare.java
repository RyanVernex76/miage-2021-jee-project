package fr.pantheonsorbonne.ufr27.miage.dto;

import java.util.Date;

public class Fare {
    double price;
    Date date;
    int passengerId;
    int carId;

    public Fare(double price, Date date, int passengerId, int carId) {
        this.price = price;
        this.date = date;
        this.passengerId = passengerId;
        this.carId = carId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
