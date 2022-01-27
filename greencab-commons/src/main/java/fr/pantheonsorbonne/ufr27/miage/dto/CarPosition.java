package fr.pantheonsorbonne.ufr27.miage.dto;

public class CarPosition {

    int carId;
    Position pos;

    public CarPosition(int carId, Position p) {
        this.carId = carId;
        this.pos = p;
    }

    public CarPosition() {
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Position getPosition(){ return pos;}

    public  void setPosition(Position p) { this.pos = p;}

}
