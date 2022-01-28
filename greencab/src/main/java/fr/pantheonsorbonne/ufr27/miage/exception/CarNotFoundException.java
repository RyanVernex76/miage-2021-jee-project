package fr.pantheonsorbonne.ufr27.miage.exception;

public class CarNotFoundException extends Exception {

    public CarNotFoundException(int id){
        super("No car found for id : " + id);
    }
}