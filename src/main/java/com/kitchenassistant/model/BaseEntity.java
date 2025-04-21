package com.kitchenassistant.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public abstract class BaseEntity
 {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    


    public Long getId() {        return id;    }    
    public void setId(Long id) {        this.id = id;    }}