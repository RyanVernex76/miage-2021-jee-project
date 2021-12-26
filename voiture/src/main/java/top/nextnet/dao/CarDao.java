package top.nextnet.dao;

import top.nextnet.exception.CarNotFoundException;
import top.nextnet.model.Car;

public interface CarDao {

    int getMaxKmBeforeRecharge(int carId) throws CarNotFoundException;

    int getCurrentKm(int carId) throws CarNotFoundException;

    Car getCar(int carId) throws CarNotFoundException;

    void updateCurrentKm(int carId, int add) throws CarNotFoundException;
}
