/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author tiffa
 */

public record CreateOrderRequest(

        @NotBlank(message = "El numero de orden es obligatorio")
        @Size(max = 50, message = "El numero de orden no puede exceder 50 caracteres")
        String orderNumber,

        @NotNull(message = "El identificador del cliente es obligatorio")
        Long clientId,


        @NotBlank(message = "Rl tipo de servicio es obligatorio")
        @Size(max = 100, message = "El tipo de servicio no puede exceder 100 caracteres")
        String serviceType) {
}
