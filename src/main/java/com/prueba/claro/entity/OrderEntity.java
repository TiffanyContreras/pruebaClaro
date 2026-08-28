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
@Table(name = "ORDERS")
public class OrderEntity {
    
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    
    
    @Column(name = "ORDER_NUMBER", nullable = false, unique = true, length = 50)
    private String orderNumber;
    
    
    @Column(name = "CLIENT_ID", nullable = false)
    private Long clientId;
    
    
    @Column(name = "SERVICE_TYPE", nullable = false, length = 100)
    private String serviceType;
    
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 30)
    private OrderStatus status;
    
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updateAt;
    
    
    
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long clientId){
        this.clientId = clientId;
    }
      
    public Long getClienteId(){
        return clientId;
    }
    
    public void setClienteId(Long clientId){
        this.clientId = clientId;
    }
    
    public String getOrderNumber(){
        return orderNumber;
    }
    
    public void setOrderNumber(String orderNumber){
        this.orderNumber = orderNumber;
    }
    
    public String getServiceType(){
        return serviceType;
    }
    
    public void setServiceType(String serviceType){
        this.serviceType = serviceType;
    }
          
    public OrderStatus getStatus(){
        return status;
    }
    
    public void setStatus(OrderStatus status){
        this.status = status;
    }
          
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
          
    public LocalDateTime getUpdatedAt(){
        return updateAt;
    }
    
    public void setUpdatedAt(LocalDateTime updateAt){
        this.updateAt = updateAt;
    }
                
            
}
