/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.controller;


import com.prueba.claro.dto.CreateOrderRequest;

import com.prueba.claro.dto.OrderHistoryResponse;
import com.prueba.claro.dto.OrderResponse;
import com.prueba.claro.dto.UpdateOrderStatusRequest;
import com.prueba.claro.service.OrderService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author tiffa
 */

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    
    private final OrderService orderService; 
    
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
     @Valid @RequestBody CreateOrderRequest request
    ){
        
        OrderResponse response = this.orderService.createOrder(request);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
        
    }
    
    
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        return ResponseEntity.ok(this.orderService.getAllOrders());
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                this.orderService.getOrderById(id)
        );
    }
    
    
    
    @PatchMapping("{id}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(
     @PathVariable Long id,
     @Valid @RequestBody UpdateOrderStatusRequest request
    ){
     return ResponseEntity.ok(
             this.orderService.updateOrderStatus(id, request)
     );
    }
    
    
    
    
    @GetMapping("/{id}/history")
    public ResponseEntity<List<OrderHistoryResponse>> getOrderHistory(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                this.orderService.getOrderHistory(id)
        );
    }
    
            
            
            
    
}
