package top.nextnet.model;

public class FareInfo {

    private int distance;
    private int duration;

    public FareInfo(int distance, int duration) {
        this.distance = distance;
        this.duration = duration;
    }

    public FareInfo(){}

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
