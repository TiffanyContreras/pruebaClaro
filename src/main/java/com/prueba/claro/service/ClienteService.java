/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.service;

/**
 *
 * @author tiffa
 */
import com.prueba.claro.dto.ClienteRequestDTO;
import com.prueba.claro.dto.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {

    ClienteResponseDTO crear(
            ClienteRequestDTO request
    );

    List<ClienteResponseDTO> listar();

    ClienteResponseDTO buscarPorId(
            Long id
    );

    ClienteResponseDTO actualizar(
            Long id,
            ClienteRequestDTO request
    );

    void eliminar(
            Long id
    );
}
