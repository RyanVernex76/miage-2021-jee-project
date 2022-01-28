package top.nextnet.dao;

import top.nextnet.model.FareWaiting;

public interface FareWaitingDao {

    FareWaiting getFareWaiting();

    void insertFareToQueue(FareWaiting f);

    boolean hasNext();

    void removeFareFromQueue(FareWaiting f);
}
