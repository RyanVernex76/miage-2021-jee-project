package com.example.cli;

import com.example.dao.BorneDao;
import com.example.exception.BorneNotFoundException;
import com.example.model.Borne;
import com.example.service.BorneGateway;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

import javax.inject.Inject;
import java.awt.*;

public class UserInterfaceCLIImpl implements UserInterfaceCLI{

    TextTerminal<?> terminal;
    TextIO textIO;

    @Inject
    BorneGateway borneGateway;

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

        return this.textIO.newIntInputReader().read("passengerId");
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


}
