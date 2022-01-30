package top.nextnet.cli;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import picocli.CommandLine.Command;
import top.nextnet.dao.FareWaitingDao;
import top.nextnet.exception.CarNotFoundException;
import top.nextnet.model.Car;
import top.nextnet.model.FareWaiting;
import top.nextnet.service.FareService;


import javax.inject.Inject;
import java.io.IOException;

@Command(name = "greeting", mixinStandardHelpOptions = true)
public class Main implements Runnable {

    @Inject
    CamelContext context;

    @Inject
    FareService fareService;

    @Inject
    FareWaitingDao fareDao;

    @Inject
    UserInterfaceCLI cli;

    @Override
    public void run() {

        TextIO textIO = TextIoFactory.getTextIO();
        cli.accept(textIO, new RunnerData(""));

        try {
            Car c = cli.connexionCar();
            cli.showCarState(c);
            FareWaiting[] fares;
            while(fareDao.hasNext()) {
                fares = fareDao.getFaresWaiting();
                FareWaiting f = cli.chooseFareToHandle(fares, c);
                fareService.handleFare(f, c);
                fareDao.removeFareFromQueue(f);
            }
        }catch (CarNotFoundException e){
            e.printStackTrace();
        } catch (ApiException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //ONLY FOR TEST - NEED TO REMOVE AFTER
    public void testFare(){
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:bookFare",
                    new Fare("12 Avenue Condorcet Athis-Mons", 2));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
