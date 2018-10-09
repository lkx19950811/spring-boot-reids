package com.lkx.springbootjedis.dao;

import com.lkx.springbootjedis.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lee Cather
 * @date 2018-10-09 10:13
 * desc :
 */
public interface OrderRepository extends JpaRepository<Order, Long> , JpaSpecificationExecutor<Order> {
}
