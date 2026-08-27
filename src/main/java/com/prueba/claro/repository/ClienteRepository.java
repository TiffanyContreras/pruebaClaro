/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.repository;

/**
 *
 * @author tiffa
 */

import com.prueba.claro.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository
        extends JpaRepository<ClienteEntity, Long> {

    boolean existsByCorreo(String correo);
}