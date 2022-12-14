package com.example.vmg.service;

import com.example.vmg.Repository.CategoryRepository;
import com.example.vmg.exception.CategoryNotFoundException;
import com.example.vmg.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Category getCategory (Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException(id));
    }
}
