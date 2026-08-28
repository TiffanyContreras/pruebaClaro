/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.claro.entity;

import com.prueba.claro.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
/**
 *
 * @author tiffa
 */

@Entity
@Table(name = "ORDER_HISTORY")
public class OrderHistoryEntity {
     @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private OrderEntity order;
    
    
    @Enumerated(EnumType.STRING)
    @Column(name = "PREVIOUS_STATUS", length = 30)
    private OrderStatus previousStatus;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "NEW_STATUS", nullable = false, length = 30)
    private OrderStatus newStatus;
    
    @Column(name = "CHANGED_AT", nullable = false)
    private LocalDateTime changedAt;
    
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
       
    public OrderEntity getOrder(){
        return order;
    }
    
    public void setOrder(OrderEntity order){
        this.order = order;
    }
    
        
    public OrderStatus getPreviousStatus(){
        return previousStatus;
    }
    
    public void setPreviousStatus(OrderStatus previousStatus){
        this.previousStatus = previousStatus;
    } 
    
    public OrderStatus getNewStatus(){
        return newStatus;
    }
    
    public void setNewStatus(OrderStatus newStatus){
        this.newStatus = newStatus;
    }
          
    public LocalDateTime getChangedAt(){
        return changedAt;
    }
    
    public void setChangedAt(LocalDateTime changedAt){
        this.changedAt = changedAt;
    }
        
}
