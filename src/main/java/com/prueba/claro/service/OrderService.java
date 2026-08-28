/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.service;

import com.prueba.claro.dto.CreateOrderRequest;

import com.prueba.claro.dto.OrderHistoryResponse;
import com.prueba.claro.dto.OrderResponse;
import com.prueba.claro.dto.UpdateOrderStatusRequest;

import java.util.List;

/**
 *
 * @author tiffa
 */
public interface OrderService {
    
    OrderResponse createOrder(CreateOrderRequest request);
    
    OrderResponse getOrderById(Long id);
    
    List<OrderResponse> getAllOrders();
    
    OrderResponse updateOrderStatus(Long id, UpdateOrderStatusRequest request);
    
    List<OrderHistoryResponse> getOrderHistory(Long id);

}
