package com.example.dao;

import com.example.exception.RechargeNotFoundException;
import com.example.model.RechargeWaiting;

public interface RechargeDao {

    RechargeWaiting getRecharge(int rechargeId) throws RechargeNotFoundException;

    RechargeWaiting[] getRechargesWaiting();

    void insertRechargeToQueue(RechargeWaiting f);

    boolean hasNext();

    void removeRechargeFromQueue(RechargeWaiting f);
}
