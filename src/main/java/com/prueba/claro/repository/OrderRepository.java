/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.repository;

import com.prueba.claro.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;




/**
 *
 * @author tiffa
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    
    boolean existsByOrderNumber(String orderNumber);
    
}
