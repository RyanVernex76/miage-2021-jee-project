package com.example.service;

import com.example.model.Borne;
import com.example.model.RechargeWaiting;

public interface RechargeService {

    void handleRecharge(RechargeWaiting rw, Borne b);
}
