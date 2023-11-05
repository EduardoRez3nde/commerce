package com.rezende.commerce.controllers;

import com.rezende.commerce.dto.OrderDTO;
import com.rezende.commerce.dto.ProductDTO;
import com.rezende.commerce.dto.ProductMinDTO;
import com.rezende.commerce.entities.OrderItemPK;
import com.rezende.commerce.services.OrderService;
import com.rezende.commerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        OrderDTO result = orderService.findById(id);
        return ResponseEntity.ok(result);
    }
}
