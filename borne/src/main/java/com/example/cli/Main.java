package com.example.cli;

import com.example.dao.BorneDao;
import com.example.service.BorneGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.Invoice;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import picocli.CommandLine.Command;

import javax.inject.Inject;

@Command(name = "greeting", mixinStandardHelpOptions = true)
public class Main implements Runnable{

    @Inject
    UserInterfaceCLI borneInterface;

    @Inject
    BorneGateway borneGateway;

    @Inject
    BorneDao borneDao;

    @Override
    public void run() {
        TextIO textIO = TextIoFactory.getTextIO();
        borneInterface.accept(textIO, new RunnerData(""));

        try {
            while(true)
            {
                try
                {
                    int juicerId = borneInterface.checkIdentity();
                    int carId = borneInterface.getCarId();
                    int amountToCharge = borneInterface.getAmountToCharge();

                    borneGateway.notifyJuicerAvailable(juicerId, false);

                    borneInterface.charging(amountToCharge);

                    Invoice invoice = new Invoice(amountToCharge, juicerId, carId);

                    borneGateway.sendInvoiceToGreenCab(invoice);
                    borneInterface.showInfoMessage("Price of this charge is " + invoice.getPrice() + "€. GreenCab will charge you in a few minutes.\n Have a nice day !");

                    borneGateway.notifyCarAvailable(carId, true);
                    borneGateway.notifyJuicerAvailable(juicerId, true);
                }
                catch(Exception e)
                {
                    borneInterface.showErrorMessage(e.getMessage());
                }
            }
        }
        catch(Exception e)
        {
            borneInterface.showErrorMessage(e.getMessage());
        }
    }
}
