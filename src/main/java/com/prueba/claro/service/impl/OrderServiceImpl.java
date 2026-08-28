/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.service.impl;

import com.prueba.claro.dto.CreateOrderRequest;
import com.prueba.claro.dto.OrderHistoryResponse;
import com.prueba.claro.dto.OrderResponse;
import com.prueba.claro.dto.UpdateOrderStatusRequest;

import com.prueba.claro.entity.OrderEntity;
import com.prueba.claro.entity.OrderHistoryEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.claro.enums.OrderStatus;

import com.prueba.claro.exception.ResourceNotFoundException;
import com.prueba.claro.exception.BusinessException;

import com.prueba.claro.repository.OrderHistoryRepository;
import com.prueba.claro.repository.OrderRepository;
import com.prueba.claro.service.OrderService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author tiffa
 */

@Service
public class OrderServiceImpl implements OrderService {
    
    
    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    
    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderHistoryRepository orderHistoryRepository
    ){
        this.orderRepository = orderRepository;
        this.orderHistoryRepository = orderHistoryRepository;
    };
    
    
    
    @Override
    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request){
        if(this.orderRepository.existsByOrderNumber(request.orderNumber())){
            throw new BusinessException(
                    "Ya existe una orden con el numero: " + request.orderNumber()
            );
        }
        
        
        LocalDateTime now = LocalDateTime.now();
        
        OrderEntity order = new OrderEntity();
        
        order.setOrderNumber(request.orderNumber());
        order.setClienteId(request.clientId());
        order.setServiceType(request.serviceType());
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(now);
        order.setUpdatedAt(now);
        
        OrderEntity saveOrder = this.orderRepository.save(order);
        
        return this.toOrderResponse(saveOrder);
        
    }
    
    @Override
    @Transactional
    public OrderResponse getOrderById(Long id){
        
        OrderEntity order = this.findOrderById(id);
        
        return this.toOrderResponse(order);
        
    }
    
    
    
    @Override
    @Transactional
    public List<OrderResponse> getAllOrders(){
        
        return this.orderRepository
                .findAll()
                .stream()
                .map(this::toOrderResponse)
                .toList();
    }
    
    
    
    @Override
    @Transactional
    public OrderResponse updateOrderStatus(
            Long id,
            UpdateOrderStatusRequest request
    ){
        OrderEntity order = this.findOrderById(id);
        
        OrderStatus currentStatus = order.getStatus();
        OrderStatus newStatus = request.status();
        
        this.validateStatusTransition(currentStatus, newStatus);
        
        LocalDateTime now = LocalDateTime.now();
        
        OrderHistoryEntity history = new OrderHistoryEntity();
        
        history.setOrder(order);
        history.setPreviousStatus(currentStatus);
        history.setNewStatus(newStatus);
        history.setChangedAt(now);
        
        order.setStatus(newStatus);
        order.setUpdatedAt(now);
        
        this.orderRepository.save(order);
        this.orderHistoryRepository.save(history);
        
        
        
        return this.toOrderResponse(order);
       
        
    }
    
    
    
    @Override
    @Transactional(readOnly = true)
    public List<OrderHistoryResponse> getOrderHistory(Long id){
        this.findOrderById(id);
        
        return this.orderHistoryRepository
                .findByOrderIdOrderByChangedAtAsc(id)
                .stream()
                .map(this::toHistoryResponse)
                .toList();
    }
    
    private OrderEntity findOrderById(Long id){
        
        return this.orderRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Orden no encontrada con id: " + id
                        )
                );
        
    }
    
    
    
    private void validateStatusTransition(
            OrderStatus currentStatus,
            OrderStatus newStatus){
       
        if(currentStatus == OrderStatus.COMPLETED || currentStatus == OrderStatus.CANCELLED){
             throw new BusinessException(
                    "La orden se encuentra en un estado final y no se puede modificar"
            );
        }
        
        if(currentStatus == newStatus){
             throw new BusinessException(
                    "La orden ya se encuentra en el estado " +currentStatus
            );
        }
        
        if(currentStatus == OrderStatus.CREATED && newStatus == OrderStatus.APPROVED){
             throw new BusinessException(
                    "Una orden CREATED debe pasar por VALIDATED antes de ser APPROVED"
            );
        }
    }
    
    
    
    private OrderResponse toOrderResponse(OrderEntity order){
        return new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getClienteId(),
                order.getServiceType(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
    
    
    private OrderHistoryResponse toHistoryResponse(
            OrderHistoryEntity history
    ){
        
        return new OrderHistoryResponse(
                history.getId(),
                history.getOrder().getId(),
                history.getPreviousStatus(),
                history.getNewStatus(),
                history.getChangedAt()
        );
    }
    
    
    
    
    
    
    
}
