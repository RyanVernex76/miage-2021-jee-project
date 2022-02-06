package fr.pantheonsorbonne.ufr27.miage.exception;

public class JuicerNotFoundException extends Exception{
    public JuicerNotFoundException(int id){
        super("No passenger found for id : " + id);
    }
}
