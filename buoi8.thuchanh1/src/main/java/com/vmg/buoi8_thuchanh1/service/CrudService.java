package com.vmg.buoi8_thuchanh1.service;

import com.vmg.buoi8_thuchanh1.entity.Company;
import com.vmg.buoi8_thuchanh1.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudService {

    @Autowired
    private CrudRepository crudRepository;

    public List<Company> getCompanyList() {
        return crudRepository.findAll();
    }

    public Company getCompanyId(Integer id) {
        return crudRepository.findById(id).get();
    }

    public void saveOrUpdateCompany(Company company) {
        crudRepository.save(company);
    }

    public void deleteCompany(Integer id) {
        crudRepository.deleteById(id);
    }
}
