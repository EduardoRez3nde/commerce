package com.rezende.commerce.repositories;

import com.rezende.commerce.dto.ProductMinDTO;
import com.rezende.commerce.entities.Product;
import com.rezende.commerce.projections.ProductMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContainingIgnoreCase(Pageable pageable, String name);
}
