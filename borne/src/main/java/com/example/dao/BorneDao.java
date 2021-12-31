package com.example.dao;

import com.example.exception.BorneNotFoundException;
import com.example.model.Borne;

public interface BorneDao {

    Borne getBorne(int borneId) throws BorneNotFoundException;
}
