package com.MindtechApps.MindtechApps.repository;

import com.MindtechApps.MindtechApps.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
