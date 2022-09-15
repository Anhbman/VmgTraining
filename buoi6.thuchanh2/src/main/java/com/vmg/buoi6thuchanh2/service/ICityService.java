package com.vmg.buoi6thuchanh2.service;

import com.vmg.buoi6thuchanh2.model.City;

import java.util.List;

public interface ICityService {

    City findById(Long id);
    City save(City city);
    List<City> findAll();
}
