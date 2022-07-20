package com.sparta.springhw3.repository;

import com.sparta.springhw3.domain.Orders;
import com.sparta.springhw3.domain.OrdersDetail;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Long> {
    List<OrdersDetail> findAllByOrder(Orders order);
}
