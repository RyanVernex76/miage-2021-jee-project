package fr.pantheonsorbonne.ufr27.miage.dto;

public class JuicerAvailable {

    int juicerId;
    boolean available;

    public JuicerAvailable(int juicerId, boolean available) {
        this.juicerId = juicerId;
        this.available = available;
    }

    public int getJuicerId() {
        return juicerId;
    }

    public void setJuicerId(int juicerId) {
        this.juicerId = juicerId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
