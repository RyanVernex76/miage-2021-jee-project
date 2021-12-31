package com.example.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Invoice;

public interface BorneGateway {
    void notifyJuicerAvailable(int juicerId, boolean available);
    void notifyCarAvailable(int carId, boolean available);
    void sendInvoiceToGreenCab(Invoice invoice);
}
