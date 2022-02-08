package fr.pantheonsorbonne.ufr27.miage.exception;

public class ChargingPointNotFoundException extends Exception{
    public ChargingPointNotFoundException(int id){
        super("No Charging point found for id : " + id);
    }
}