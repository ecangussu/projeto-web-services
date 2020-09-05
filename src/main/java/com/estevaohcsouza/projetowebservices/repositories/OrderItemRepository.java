package com.estevaohcsouza.projetowebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estevaohcsouza.projetowebservices.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
