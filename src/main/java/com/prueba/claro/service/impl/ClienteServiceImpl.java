/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.service.impl;
/**
 *
 * @author tiffa
 */
import com.prueba.claro.dto.ClienteRequestDTO;
import com.prueba.claro.dto.ClienteResponseDTO;
import com.prueba.claro.entity.ClienteEntity;
import com.prueba.claro.exception.ResourceNotFoundException;
import com.prueba.claro.repository.ClienteRepository;
import com.prueba.claro.service.ClienteService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl
        implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponseDTO crear(
            ClienteRequestDTO request
    ) {

        if (clienteRepository.existsByCorreo(request.correo())) {
            throw new IllegalArgumentException(
                    "Ya existe un cliente con ese correo"
            );
        }

        ClienteEntity clienteEntity = ClienteEntity.builder()
                .nombre(request.nombre())
                .correo(request.correo())
                .telefono(request.telefono())
                .build();

        ClienteEntity clienteGuardado =
                clienteRepository.save(clienteEntity);

        return convertirAResponseDTO(clienteGuardado);
    }

    @Override
    public List<ClienteResponseDTO> listar() {

        return clienteRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    @Override
    public ClienteResponseDTO buscarPorId(
            Long id
    ) {

        ClienteEntity clienteEntity =
                clienteRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cliente no encontrado con id: " + id
                                )
                        );

        return convertirAResponseDTO(clienteEntity);
    }

    @Override
    public ClienteResponseDTO actualizar(
            Long id,
            ClienteRequestDTO request
    ) {

        ClienteEntity clienteEntity =
                clienteRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cliente no encontrado con id: " + id
                                )
                        );

        clienteEntity.setNombre(request.nombre());
        clienteEntity.setCorreo(request.correo());
        clienteEntity.setTelefono(request.telefono());

        ClienteEntity clienteActualizado =
                clienteRepository.save(clienteEntity);

        return convertirAResponseDTO(clienteActualizado);
    }

    @Override
    public void eliminar(
            Long id
    ) {

        ClienteEntity clienteEntity =
                clienteRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cliente no encontrado con id: " + id
                                )
                        );

        clienteRepository.delete(clienteEntity);
    }

    private ClienteResponseDTO convertirAResponseDTO(
            ClienteEntity clienteEntity
    ) {

        return new ClienteResponseDTO(
                clienteEntity.getId(),
                clienteEntity.getNombre(),
                clienteEntity.getCorreo(),
                clienteEntity.getTelefono()
        );
    }
}