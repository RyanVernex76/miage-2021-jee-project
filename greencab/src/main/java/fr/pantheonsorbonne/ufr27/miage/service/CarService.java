package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;

public interface CarService {

    public CarPosition setAvailable(CarPosition car);

    public CarPosition notifyRecharge(CarPosition car);
}
