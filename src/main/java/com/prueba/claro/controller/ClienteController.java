/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.controller;

/**
 *
 * @author tiffa
 */

import com.prueba.claro.dto.ClienteRequestDTO;
import com.prueba.claro.dto.ClienteResponseDTO;
import com.prueba.claro.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(
        name = "Clientes",
        description = "Operaciones para gestión de clientes"
)
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(
            summary = "Crear cliente"
    )
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crear(
            @Valid
            @RequestBody
            ClienteRequestDTO request
    ) {

        ClienteResponseDTO response =
                clienteService.crear(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(
            summary = "Listar clientes"
    )
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {

        return ResponseEntity.ok(
                clienteService.listar()
        );
    }

    @Operation(
            summary = "Buscar cliente por ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                clienteService.buscarPorId(id)
        );
    }

    @Operation(
            summary = "Actualizar cliente"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizar(
            @PathVariable Long id,

            @Valid
            @RequestBody
            ClienteRequestDTO request
    ) {

        return ResponseEntity.ok(
                clienteService.actualizar(
                        id,
                        request
                )
        );
    }

    @Operation(
            summary = "Eliminar cliente"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id
    ) {

        clienteService.eliminar(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}