package com.example.service;

import java.util.Collection;

public interface InvoicingService {
	Collection<Recharge> fillInvoicesWithQRCode(Collection<Recharge> invoices);

	void notifyCreatedInvoice(String invoiceKey);


}
