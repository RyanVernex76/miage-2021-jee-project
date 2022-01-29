package fr.pantheonsorbonne.ufr27.miage.exception;

public class PassengerNotFoundException extends Exception {

    public PassengerNotFoundException(int id){
        super("No passenger found for id : " + id);
    }
}
