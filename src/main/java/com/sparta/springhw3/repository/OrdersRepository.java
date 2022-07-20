package com.sparta.springhw3.repository;

import com.sparta.springhw3.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
