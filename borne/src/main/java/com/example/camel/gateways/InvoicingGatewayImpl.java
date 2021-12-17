package com.example.camel.gateways;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import javax.inject.Inject;
import java.io.IOException;

public class InvoicingGatewayImpl implements InvoicingGateway {
	@Inject
	CamelContext camelContext;

	@Override
	public void submitInvoicing(Recharge recharge) {
		try (ProducerTemplate template = camelContext.createProducerTemplate()) {
			template.sendBody("direct:invoice", recharge);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
