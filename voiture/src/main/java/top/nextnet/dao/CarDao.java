package top.nextnet.dao;

import top.nextnet.exception.CarNotFoundException;

public interface CarDao {

    int getMaxKmBeforeRecharge(int carId) throws CarNotFoundException;

    int getCurrentKm(int carId) throws CarNotFoundException;
}
