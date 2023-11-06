package com.rezende.commerce.repositories;

import com.rezende.commerce.entities.Order;
import com.rezende.commerce.entities.OrderItem;
import com.rezende.commerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
