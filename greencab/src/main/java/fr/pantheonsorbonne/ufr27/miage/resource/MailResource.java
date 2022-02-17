package fr.pantheonsorbonne.ufr27.miage.resource;

import fr.pantheonsorbonne.ufr27.miage.dao.JuicerDao;
import fr.pantheonsorbonne.ufr27.miage.dao.PassengerDao;
import fr.pantheonsorbonne.ufr27.miage.exception.JuicerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Juicer;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

@Path("/mail")
public class MailResource {

    @Inject
    Mailer mailer;

    @Inject
    PassengerDao passengerDao;

    @Inject
    JuicerDao juicerDao;

    @Path("/passenger/{id}")
    @GET
    @Blocking
    public void sendEmailPassenger(@PathParam("id") int id) {
        try {
            Passenger passenger = passengerDao.getPassenger(id);
            mailer.send(
                    Mail.withText(passenger.getEmailAddress(),
                            "Fare reseservation confirmation",
                            "A simple email sent from GreenCab."
                    )
            );
        } catch (PassengerNotFoundException e){
            throw new WebApplicationException("index invalide ", 404);
        }

    }

    @Path("/juicer/{id}")
    @GET
    @Blocking
    public void sendEmailJuicer(@PathParam("id") int id) {
        try{
            Juicer j = juicerDao.getJuicer(id);
            mailer.send(
                    Mail.withText(j.getEmailAddress(),
                            "Ahoy from Quarkus",
                            "A simple email sent from a Quarkus application."
                    )
            );
        }catch (JuicerNotFoundException e){
            throw new WebApplicationException("index invalide ", 404);
        }
    }
}