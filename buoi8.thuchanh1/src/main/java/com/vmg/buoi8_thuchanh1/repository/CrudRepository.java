package com.vmg.buoi8_thuchanh1.repository;

import com.vmg.buoi8_thuchanh1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudRepository extends JpaRepository<Company, Integer> {
}
