/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.dto;

/**
 *
 * @author tiffa
 */
public record ClienteResponseDTO(
        Long id,
        String nombre,
        String correo,
        String telefono
) {
}