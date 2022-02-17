package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;

public interface CarService {

    CarPosition setAvailable(CarPosition car);

    CarPosition initRecharge(CarPosition car);

    boolean checkDistance(int carId, String dest);
}
