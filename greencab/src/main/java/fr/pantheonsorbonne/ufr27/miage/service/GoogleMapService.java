package fr.pantheonsorbonne.ufr27.miage.service;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dto.Position;

import java.io.IOException;

public interface GoogleMapService {

    Position getTranslatedCoordonates(String address) throws IOException, InterruptedException, ApiException;

    String getAddress(Position p) throws IOException, InterruptedException, ApiException;

    double getDistance(String dep, String arv) throws IOException, InterruptedException, ApiException;
}
