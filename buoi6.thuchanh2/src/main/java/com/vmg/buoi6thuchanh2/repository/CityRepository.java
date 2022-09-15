package com.vmg.buoi6thuchanh2.repository;

import com.vmg.buoi6thuchanh2.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
}
