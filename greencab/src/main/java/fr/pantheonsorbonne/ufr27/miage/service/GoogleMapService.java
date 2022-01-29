package fr.pantheonsorbonne.ufr27.miage.service;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dto.Position;

import java.io.IOException;

public interface GoogleMapService {

    Position getTranslatedCoordonates(String address) throws IOException, InterruptedException, ApiException;
}
