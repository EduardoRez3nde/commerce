package com.rezende.commerce.services;

import com.rezende.commerce.dto.OrderDTO;
import com.rezende.commerce.entities.Order;
import com.rezende.commerce.entities.OrderItemPK;
import com.rezende.commerce.repositories.OrderRepository;
import com.rezende.commerce.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order result = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource Not Found"));
        return new OrderDTO(result);
    }
}
