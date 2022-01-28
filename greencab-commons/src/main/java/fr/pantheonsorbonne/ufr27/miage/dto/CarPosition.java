package fr.pantheonsorbonne.ufr27.miage.dto;

public class CarPosition {

    int carId;
    String addressPos;

    public CarPosition(int carId, String address) {
        this.carId = carId;
        this.addressPos = address;
    }

    public CarPosition() {
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getPos() {
        return this.addressPos;
    }

    public void setPosition(String address) {
        this.addressPos = address;
    }
}