package fr.pantheonsorbonne.ufr27.miage.model;

import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;
import fr.pantheonsorbonne.ufr27.miage.model.Juicer;

import javax.persistence.*;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id", nullable = false)
    private Integer invoiceId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "juicer_id", nullable = false)
    private Juicer juicerId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    private AutonomousCar carId;

    @Column(name = "price", nullable = false)
    private Double price;

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Juicer getJuicerId() {
        return juicerId;
    }

    public void setJuicerId(Juicer juicerId) {
        this.juicerId = juicerId;
    }

    public AutonomousCar getCarId() {
        return carId;
    }

    public void setCarId(AutonomousCar carId) {
        this.carId = carId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}