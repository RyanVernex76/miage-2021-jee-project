package fr.pantheonsorbonne.ufr27.miage.dto;

public class CarAvailable {

    int carId;
    boolean available;

    public CarAvailable(int carId, boolean available) {
        this.carId = carId;
        this.available = available;
    }

    public CarAvailable() {
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
