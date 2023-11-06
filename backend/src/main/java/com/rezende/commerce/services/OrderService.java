package com.rezende.commerce.services;

import com.rezende.commerce.dto.OrderDTO;
import com.rezende.commerce.entities.Order;
import com.rezende.commerce.entities.OrderItem;
import com.rezende.commerce.entities.Product;
import com.rezende.commerce.entities.User;
import com.rezende.commerce.entities.enums.OrderStatus;
import com.rezende.commerce.repositories.OrderItemRepository;
import com.rezende.commerce.repositories.OrderRepository;
import com.rezende.commerce.repositories.ProductRepository;
import com.rezende.commerce.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, UserService userService, ProductRepository productRepository,
                        OrderItemRepository itemRepository) {
        this.userService = userService;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order result = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource Not Found"));
        return new OrderDTO(result);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        User user = userService.authenticated();
        order.setClient(user);

        dto.getItems().forEach(x ->{
            Product product = productRepository.getReferenceById(x.getProductId());
            OrderItem orderItem = new OrderItem(x.getQuantity(), product.getPrice(), order, product);
            order.getItems().add(orderItem);
        });
        orderRepository.save(order);
        itemRepository.saveAll(order.getItems());
        return new OrderDTO(order);
    }
}
