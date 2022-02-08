package com.example.exception;

public class BorneNotFoundException extends Exception{
    public BorneNotFoundException(int borneId) {
        super("No borne found for id: " + borneId);
    }
}
