package com.example.vmg.service;

import com.example.vmg.Repository.CoverRepository;
import com.example.vmg.model.Cover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoverService {

    @Autowired
    private CoverRepository coverRepository;

    public Cover save(Cover cover) {
        return coverRepository.save(cover);
    }
}
