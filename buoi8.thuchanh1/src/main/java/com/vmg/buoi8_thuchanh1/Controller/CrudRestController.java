package com.vmg.buoi8_thuchanh1.Controller;

import com.vmg.buoi8_thuchanh1.entity.Company;
import com.vmg.buoi8_thuchanh1.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CrudRestController {

    @Autowired
    private CrudService crudService;

    @GetMapping("/companyList")
    public ResponseEntity<List<Company>> getCompanyList() {
        return new ResponseEntity<List<Company>>(crudService.getCompanyList(), HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Integer id) {
        return new ResponseEntity<Company>(crudService.getCompanyId(id), HttpStatus.OK);
    }

    @PostMapping("/company/save")
    public ResponseEntity<Void> saveOrUpdateCompany(@RequestBody Company company) {
        crudService.saveOrUpdateCompany(company);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/company/delete/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id) {
        crudService.deleteCompany(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
