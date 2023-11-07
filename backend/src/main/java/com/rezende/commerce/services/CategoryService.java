package com.rezende.commerce.services;


import com.rezende.commerce.dto.CategoryDTO;
import com.rezende.commerce.entities.Category;
import com.rezende.commerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> findByCategory() {
        List<Category> category = categoryRepository.findAll();
        return category.stream().map(CategoryDTO::new).toList();
    }
}
