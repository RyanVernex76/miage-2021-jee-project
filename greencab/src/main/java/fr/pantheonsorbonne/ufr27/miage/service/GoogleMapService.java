package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Position;

public interface GoogleMapService {

    Position getTranslatedCoordonates(String address);

    String getAddress(Position p);

    double getDistance(String dep, String arv);
}
