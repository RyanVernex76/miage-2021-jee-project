package fr.pantheonsorbonne.ufr27.miage.exception;

public class ElementNotFoundException extends Exception{
    public ElementNotFoundException(int id){
        super("No element found for id : " + id);
    }
}