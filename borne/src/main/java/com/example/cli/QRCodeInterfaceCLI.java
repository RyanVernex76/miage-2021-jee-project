package com.example.cli;
import org.beryx.textio.TextIO;

import java.util.function.BiConsumer;

public interface QRCodeInterfaceCLI extends BiConsumer<TextIO, RunnerData> {
	void showErrorMessage(String errorMessage);

	void showSuccessMessage(String s);
	void getQRCodeInformations(String s);

}
