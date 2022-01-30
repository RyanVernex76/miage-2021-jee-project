package fr.pantheonsorbonne.ufr27.miage.service;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dto.CarPosition;

import java.io.IOException;

public interface CarService {

    public CarPosition setAvailable(CarPosition car);

    public CarPosition notifyRecharge(CarPosition car);

    public boolean checkDistance(int carId, String dest) throws IOException, InterruptedException, ApiException;
}
