package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Invoice;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;
import fr.pantheonsorbonne.ufr27.miage.model.Juicer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class InvoiceServiceImpl implements InvoiceService{

    @PersistenceContext
    EntityManager em;


    @Override
    @Transactional
    public Invoice register(Invoice invoice) {
        try {
            Juicer juicer = em.find(Juicer.class, invoice.getJuicerId());
            AutonomousCar car = em.find(AutonomousCar.class, invoice.getCarId());
        }
        catch (NonUniqueResultException | NoResultException e) {
            //throw new UnsuficientQuotaForVenueException(booking.getVenueId());
        }
        return invoice;
    }
}
