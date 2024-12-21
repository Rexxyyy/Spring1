package com.ecom.demo.eecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    public int id;
    public String name;
    public String mobile;
    public String email;
    public String password;


}
