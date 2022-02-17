package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Juicer;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MailServiceImpl implements MailService{

    @Inject
    Mailer mailer;

    @Override
    public void sendEmailPassenger(Passenger passenger){
        mailer.send(
                Mail.withText(passenger.getEmailAddress(),
                        "Fare reseservation confirmation",
                        "A simple email sent from GreenCab."
                )
        );
    }

    @Override
    public void sendEmailJuicer(Juicer juicer){
        Mail mail = Mail.withText(juicer.getEmailAddress(),
                "A car need your help !",
                "A simple email sent from a Quarkus application."
        );
        mailer.send(mail);
    }
}
