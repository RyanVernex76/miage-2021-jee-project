package top.nextnet.cli;

import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import picocli.CommandLine.Command;
import top.nextnet.exception.CarNotFoundException;
import top.nextnet.model.Car;
import top.nextnet.model.FareInfo;
import top.nextnet.service.CarGateway;


import javax.inject.Inject;
import java.io.IOException;

@Command(name = "greeting", mixinStandardHelpOptions = true)
public class Main implements Runnable {

    @Inject
    CamelContext context;

    @Override
    public void run() {


        //TextIO textIO = TextIoFactory.getTextIO();
        //carInterface.accept(textIO, new RunnerData(""));
        this.testFare();

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
