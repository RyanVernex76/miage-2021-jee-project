package top.nextnet.cli;

import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import picocli.CommandLine.Command;
import top.nextnet.dao.FareWaitingDao;
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

    @Override
    public void run() {
        this.testFare();


        FareWaiting f;
        while (fareDao.hasNext()) {
            f = fareDao.getFareWaiting();
            fareService.handleFare(f);
            fareDao.removeFareFromQueue(f);
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
