package com.rezende.commerce.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @EmbeddedId
    private final OrderItemPK pk = new OrderItemPK();

    private Integer quantity;

    private Double price;

    public OrderItem() {}

    public OrderItem(Integer quantity, Double price, Order order, Product product) {
        this.quantity = quantity;
        this.price = price;
        pk.setOrder(order);
        pk.setProduct(product);
    }

    public Product getProduct() {
        return this.pk.getProduct();
    }

    public void setProduct(Product product) {
        this.pk.setProduct(product);
    }

    public Order getOrder() {
        return this.pk.getOrder();
    }

    public void setOrder(Order order) {
        this.pk.setOrder(order);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
