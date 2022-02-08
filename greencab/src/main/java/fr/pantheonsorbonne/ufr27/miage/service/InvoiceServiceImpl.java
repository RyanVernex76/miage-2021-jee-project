package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Invoice;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;
import fr.pantheonsorbonne.ufr27.miage.model.Juicer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class InvoiceServiceImpl implements InvoiceService{

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Invoice register(Invoice invoice) {
        try {
            Juicer juicer = em.find(Juicer.class, invoice.getJuicerId());
            AutonomousCar car = em.find(AutonomousCar.class, invoice.getCarId());
            fr.pantheonsorbonne.ufr27.miage.model.Invoice i = new fr.pantheonsorbonne.ufr27.miage.model.Invoice();
            i.setCarId(car);
            i.setJuicerId(juicer);
            i.setPrice(invoice.getPrice());

            em.persist(i);
        }
        catch (NonUniqueResultException | NoResultException e) {
            //throw new UnsuficientQuotaForVenueException(booking.getVenueId());
        }
        return invoice;
    }
}
