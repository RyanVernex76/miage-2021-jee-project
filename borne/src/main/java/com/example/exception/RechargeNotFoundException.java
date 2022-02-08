package com.example.exception;

public class RechargeNotFoundException extends Exception{
    public RechargeNotFoundException(int id){
        super("No recharge found for id : " + id);
    }
}
