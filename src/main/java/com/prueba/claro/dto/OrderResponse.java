/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.dto;


import com.prueba.claro.enums.OrderStatus;
import java.time.LocalDateTime;

/**
 *
 * @author tiffa
 */
public record OrderResponse(
    Long id,
    String orderNumber,
    Long clienteId,
    String serviceType,
    OrderStatus status,
    LocalDateTime createdAt,
    LocalDateTime updateAt) {
    
                            
    
}
