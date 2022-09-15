package com.vmg.buoi6thuchanh2.Controller;

import com.vmg.buoi6thuchanh2.model.City;
import com.vmg.buoi6thuchanh2.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;

@RestController
public class MyController {

    @Autowired
    private ICityService cityService;

    @GetMapping(value = "/cities/{id}")
    public City getCity(@PathVariable("id") Long id) {
        return cityService.findById(id);
    }

    @PostMapping(value = "/cities", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public City createCity(@RequestBody @Valid City city) {
        return cityService.save(city);
    }

    @GetMapping(value = "/cities")
    public List<City> findAll() {
        return cityService.findAll();
    }
}
