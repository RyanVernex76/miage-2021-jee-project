package com.example.cli;

import com.example.dao.BorneDao;
import com.example.exception.BorneNotFoundException;
import com.example.model.Borne;
import com.example.model.RechargeWaiting;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@ApplicationScoped
public class UserInterfaceCLIImpl implements UserInterfaceCLI{

    TextTerminal<?> terminal;
    TextIO textIO;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.timeRecharge")
    int rechargeTime;

    @Inject
    BorneDao borneDao;

    @Override
    public void accept(TextIO textIO, RunnerData runnerData) {
        this.textIO = textIO;
        terminal = textIO.getTextTerminal();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        terminal.getProperties().setPromptColor(Color.RED);
        terminal.println(errorMessage);
        terminal.getProperties().setPromptColor(Color.white);
    }

    @Override
    public void showSuccessMessage(String s) {
        terminal.getProperties().setPromptColor(Color.GREEN);
        terminal.println(s);
        terminal.getProperties().setPromptColor(Color.white);
    }

    @Override
    public void showInfoMessage(String infoMessage) {
        terminal.getProperties().setPromptColor(Color.YELLOW);
        terminal.println(infoMessage);
        terminal.getProperties().setPromptColor(Color.white);
    }

    @Override
    public int checkIdentity() {
        terminal.println("Please scan the QR Code GreenCab sent you for this recharge.");

        // Add QR Code checks

        return this.textIO.newIntInputReader().read("juicerId");
    }

    @Override
    public int getCarId() {
        terminal.println("Please enter the car ID.");

        // Add Car ID check.

        return this.textIO.newIntInputReader().read("carId");
    }

    @Override
    public int getAmountToCharge()
    {
        terminal.println("Please enter the % of battery you want to charge.");

        int amount = this.textIO.newIntInputReader().read("chargingAmount");

        while(amount > 100 || amount < 0)
        {
            terminal.println("Please enter a valid amount between 0 and 100.");
            amount = this.textIO.newIntInputReader().read("chargingAmount");
        }

        return amount;
    }

    @Override
    public void charging(int batteryToRecharge) {
        try {
            terminal.println("**** Starting to recharge " + batteryToRecharge + "% of battery ****\n");
            Thread.sleep(batteryToRecharge * 500L);
            terminal.println("**** Car done recharging ****");
            terminal.println("**** Sending bill to GreenCab ****\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void chargingFull() {
        try {
            terminal.println("**** Starting to recharge the battery ****\n");
            Thread.sleep(rechargeTime);
            terminal.println("**** Car done recharging ****");
            terminal.println("**** Sending bill to GreenCab ****\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Borne getBorne(){
        try{
            terminal.println("Please enter the id of the charging point you want to use : ");
            int id = textIO.newIntInputReader().read("ID");
            return borneDao.getBorne(id);
        }catch (BorneNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void showBorneInfo(Borne b) {
        String msg = "Borne n°"+ b.getId() + " - " + b.getName();
        showInfoMessage(msg);
    }

    @Override
    public int chooseRechargeToHandle(RechargeWaiting[] recharges) {
        terminal.println("Please enter the id of the recharge GreenCab gave you : ");

        int[] rechargesIds = new int[recharges.length];
        for(int i = 0; i < recharges.length; i++){
            rechargesIds[i] = recharges[i].getId();
        }

        int input = textIO.newIntInputReader()
                .read("Recharge ID");

        int i = 3;
        while(!this.contains(rechargesIds, input)){
            this.showErrorMessage("Wrong id ! ");
            i--;
            if(i!=0){
                this.showErrorMessage("Please try again. You have " + i + " chance(s) left.");
                input = this.textIO.newIntInputReader().read("Recharge ID");
            }
            else
                return 0;
        }
        return input;
    }

    private boolean contains(int array[], int a){
        for(int i = 0; i < array.length; i++){
            if(array[i] == a)
                return true;
        }
        return false;
    }

}
