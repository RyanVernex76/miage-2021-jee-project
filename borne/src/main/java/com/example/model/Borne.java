package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Borne {

    @Id
    @Column(name="borne_id", nullable = false)
    private Integer id;
}
