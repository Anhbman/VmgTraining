package com.vmg.buoi8_thuchanh1.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "company")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Company(){}

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
