package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Position;
import fr.pantheonsorbonne.ufr27.miage.exception.ElementNotFoundException;

public interface PositionDao {

    Position getPosition(int elementId) throws ElementNotFoundException;
}
