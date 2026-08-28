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
public record OrderHistoryResponse(
    Long id,
    Long orderId,
    OrderStatus previousStatus,
    OrderStatus newStatus,
    LocalDateTime changeAt
) {



}
