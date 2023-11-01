package com.rezende.commerce.dto;

import com.rezende.commerce.entities.Category;
import com.rezende.commerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDTO {

    private Long id;

    @NotBlank(message = "Campo Requerido")
    @Size(min = 3, max = 80, message = "Nome deve ter entre 5 e 50 caracteres")
    private String name;

    @Positive(message = "Preço deve ser positivo")
    private Double price;

    private String imgUrl;

    @NotBlank(message = "Campo Requerido")
    @Size(min = 10, message = "Description deve ter mais de 10 caracteres")
    private String description;

    private final List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO() {}

    public ProductDTO(Long id, String name, Double price, String imgUrl, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
        description = entity.getDescription();
    }

    public ProductDTO(Product entity, Set<Category> categories) {
        this(entity);
        categories.stream().map(cat -> this.categories.add(new CategoryDTO(cat))).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
