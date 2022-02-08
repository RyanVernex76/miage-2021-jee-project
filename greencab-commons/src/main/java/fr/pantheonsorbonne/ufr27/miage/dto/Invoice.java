package fr.pantheonsorbonne.ufr27.miage.dto;

public class Invoice {

    private static final double pricePerMinute = 3.0;

    int timeCharge;
    int juicerId;
    int carId;
    double price;

    public Invoice(int timeCharge, int juicerId, int carId)
    {
        this.timeCharge = timeCharge;
        this.juicerId = juicerId;
        this.carId = carId;
        this.price = this.calculatePrice();
    }

    public int getTimeCharge() {
        return timeCharge;
    }

    public int getJuicerId() {
        return juicerId;
    }

    public int getCarId() {
        return carId;
    }

    public double getPrice() {
        return price;
    }

    private double calculatePrice() {return pricePerMinute * this.timeCharge;}


}
