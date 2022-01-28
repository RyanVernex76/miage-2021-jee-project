package fr.pantheonsorbonne.ufr27.miage.dto;

import java.util.Date;

public class Fare {

    private static final double pricePerKm = 5.0;

    String departure;
    String destination;
    int distance;
    double price;
    Date date;
    int passengerId;
    int carId;

    public Fare(){}

    public Fare(String dep, int passengerId){
        this.departure = dep;
        this.passengerId = passengerId;
        this.date = new Date();
    }

    public Fare(int distance, int passengerId, int carId) {
        this.distance = distance;
        this.date = new Date();
        this.passengerId = passengerId;
        this.carId = carId;
        this.price = this.calculatePrice();
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double calculatePrice(){
        return pricePerKm * this.distance;
    }
}
