package com.example.userstories.repository;

import com.example.userstories.entity.Orders;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    @EntityGraph(attributePaths = {"user", "stocks", "user.cashBalance"})
    List<Orders> findAll();

}
