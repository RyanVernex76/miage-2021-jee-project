package fr.pantheonsorbonne.ufr27.miage.exception;

public class BorneNotFoundException extends Exception{
    public BorneNotFoundException(int id){
        super("No borne found for id : " + id);
    }
}
