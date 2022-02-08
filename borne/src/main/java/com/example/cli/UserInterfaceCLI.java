package com.example.cli;

import com.example.model.Borne;
import com.example.model.RechargeWaiting;
import org.beryx.textio.TextIO;

import java.util.function.BiConsumer;

public interface UserInterfaceCLI extends BiConsumer<TextIO, RunnerData>, UserInterface {
    void charging(int batteryToRecharge);

    void chargingFull();

    void showBorneInfo(Borne b);

    Borne getBorne();

    int chooseRechargeToHandle(RechargeWaiting[] recharges);
}
