package fr.pantheonsorbonne.ufr27.miage.dto;

public class CarRecharge {

    int carId;
    boolean recharge;

    public CarRecharge(int carId, boolean recharge) {
        this.carId = carId;
        this.recharge = recharge;
    }

    public CarRecharge() {
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public boolean isrecharge() {
        return recharge;
    }

    public void setrecharge(boolean recharge) {
        this.recharge = recharge;
    }
}
