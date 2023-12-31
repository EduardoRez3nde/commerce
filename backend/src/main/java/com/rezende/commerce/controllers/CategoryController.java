package com.rezende.commerce.controllers;


import com.rezende.commerce.dto.CategoryDTO;
import com.rezende.commerce.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CategoryDTO>> findByCategory() {
        List<CategoryDTO> categoryDTO = categoryService.findByCategory();
        return ResponseEntity.ok(categoryDTO);
    }
}
