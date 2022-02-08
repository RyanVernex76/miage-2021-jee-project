package fr.pantheonsorbonne.ufr27.miage.dto;

public class Recharge {

    int id;
    int carId;
    int juicerId;
    double cost;
    String state;
    int chargintPointId;

    public Recharge(){}

    public Recharge(int id, int juicerId, int carId){
        this.id = id;
        this.carId = carId;
        this.juicerId = juicerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getJuicerId() {
        return juicerId;
    }

    public void setJuicerId(int juicerId) {
        this.juicerId = juicerId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getChargintPointId() {
        return chargintPointId;
    }

    public void setChargintPointId(int chargintPointId) {
        this.chargintPointId = chargintPointId;
    }
}
