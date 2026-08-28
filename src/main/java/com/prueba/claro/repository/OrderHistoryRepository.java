/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.repository;


import com.prueba.claro.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
/**
 *
 * @author tiffa
 */
public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, Long>{
    
    List<OrderHistoryEntity> findByOrderIdOrderByChangedAtAsc(Long orderId);
    
}
