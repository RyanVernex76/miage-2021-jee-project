package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.AutonomousCarDao;
import fr.pantheonsorbonne.ufr27.miage.dao.PassengerDao;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;
import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;
import fr.pantheonsorbonne.ufr27.miage.model.Juicer;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MailServiceImpl implements MailService{

    @Inject
    Mailer mailer;

    @Inject
    AutonomousCarDao carDao;

    @Inject
    PassengerDao passengerDao;

    @Override
    public void sendMailValidationFare(Fare fare){
        try{
            mailer.send(
                    Mail.withText(passengerDao.getPassenger(fare.getPassengerId()).getEmailAddress(),
                            "Fare reservation confirmed !",
                                    "Please wait for a car at the indicated location : " +
                                    fare.getDeparture()
                    )
            );
        }catch(PassengerNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void sendNotfifRechargeJuicer(Juicer juicer, CarPosition car){
        try{
            AutonomousCar c = carDao.getCar(car.getCarId());
            Mail mail = Mail.withText(juicer.getEmailAddress(),
                    "A car need your help !",
                    "License plate : " + c.getLicensePlate() + "\n" +
                            "Located at : " + car.getPosition()
            );
            mailer.send(mail);
        }catch (CarNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
