package com.rezende.commerce.repositories;

import com.rezende.commerce.entities.Category;
import com.rezende.commerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
