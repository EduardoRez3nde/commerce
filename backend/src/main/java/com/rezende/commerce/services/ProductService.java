package com.rezende.commerce.services;

import com.rezende.commerce.dto.ProductDTO;
import com.rezende.commerce.dto.ProductMinDTO;
import com.rezende.commerce.entities.Category;
import com.rezende.commerce.entities.Product;
import com.rezende.commerce.repositories.CategoryRepository;
import com.rezende.commerce.repositories.ProductRepository;
import com.rezende.commerce.services.exceptions.DataBaseException;
import com.rezende.commerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductService (ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product result = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource Not Found"));
        return new ProductDTO(result, result.getCategories());
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(Pageable pageable, String name) {
        Page<Product> result = repository.findByNameContainingIgnoreCase(pageable, name);
        return result.map(ProductMinDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getReferenceById(id);
            copyToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity, entity.getCategories());
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Resource Not Found");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity Violation Error");
        }
    }

    public void copyToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity.setDescription(dto.getDescription());

        entity.getCategories().clear();
        dto.getCategories().forEach(catDTO -> {
            Category category = categoryRepository.getReferenceById(catDTO.getId());
            entity.getCategories().add(category);
        });
    }
}
