package com.estevaohcsouza.projetowebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estevaohcsouza.projetowebservices.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
