/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.dto;


import com.prueba.claro.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
/**
 *
 * @author tiffa
 */
public record UpdateOrderStatusRequest(

        @NotNull(message = "El nuevo estado es obligatorio")
        OrderStatus status) {


}
