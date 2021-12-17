package com.example.cli;

import fr.pantheonsorbonne.ufr27.miage.dto.Gig;
import org.apache.camel.model.dataformat.BarcodeDataFormat;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.function.BiConsumer;

public class QRCodeInterfaceImpl implements QRCodeInterfaceCLI {
	private TextIO textIO;
	TextTerminal<?> terminal;

	@Override
	public void getQRCodeInformations(String infoQRCode) {
		terminal.println("JuicerId="+infoQRCode);
		for (Gig gig : vendorService.getGigs(vendorId)) {
			terminal.println("[" + gig.getVenueId() + "] " + gig.getArtistName() + " " + gig.getDate().format(DateTimeFormatter.ISO_DATE) + " " + gig.getLocation());
		}
	}

	@Override
	public void accept(TextIO textIO, RunnerData runnerData) {
		this.textIO = textIO;
		terminal = textIO.getTextTerminal();
	}

	@Override
	public BiConsumer<TextIO, RunnerData> andThen(BiConsumer<? super TextIO, ? super RunnerData> after) {
		return QRCodeInterfaceCLI.super.andThen(after);
	}

	@Override
	public void showErrorMessage(String errorMessage) {
		terminal.getProperties().setPromptColor(Color.RED);
		terminal.println(errorMessage);
		terminal.getProperties().setPromptColor(Color.white);
	}

	@Override
	public void showSuccessMessage(String s) {
		terminal.getProperties().setPromptColor(Color.GREEN);
		terminal.println(s);
		terminal.getProperties().setPromptColor(Color.white);
	}

}
