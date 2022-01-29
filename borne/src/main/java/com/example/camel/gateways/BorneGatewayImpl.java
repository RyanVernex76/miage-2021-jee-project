package com.example.camel.gateways;

import com.example.dao.BorneDao;
import com.example.service.BorneGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.Invoice;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class BorneGatewayImpl implements BorneGateway {

    @Inject
    CamelContext context;

    @Inject
    BorneDao borneDao;

    @Override
    public void notifyJuicerAvailable(int juicerId, boolean available) {
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:available", juicerId);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyCarAvailable(int carId, boolean available) {
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:available", carId);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void sendInvoiceToGreenCab(Invoice invoice)
    {
        try(ProducerTemplate producer = context.createProducerTemplate()){
            producer.sendBody("direct:invoice", invoice);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
