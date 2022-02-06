package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.JuicerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Juicer;

public interface JuicerDao {

    Juicer getJuicer(int id) throws JuicerNotFoundException;

    Juicer[] getJuicers();

    void insertNewJuicer(Juicer j);
}
