package com.rezende.commerce.dto;

import com.rezende.commerce.entities.Product;
import com.rezende.commerce.projections.ProductMinProjection;

public class ProductMinDTO {

    private String name;

    public ProductMinDTO() {}

    public ProductMinDTO(String name) {
        this.name = name;
    }

    public ProductMinDTO(ProductMinProjection projection) {
        name = projection.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
