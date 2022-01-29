package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Invoice;
import fr.pantheonsorbonne.ufr27.miage.service.InvoiceService;

import javax.inject.Inject;

public class InvoiceGateway {
    @Inject
    InvoiceService invoiceService;

    public Invoice register(Invoice invoice) {
        return invoiceService.register(invoice);
    }
}
