package com.lkx.springbootjedis.dao;

import com.lkx.springbootjedis.pojo.OrderUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author lee Cather
 * @date 2018-10-09 11:30
 * desc :
 */
public interface OrderUserRepository extends JpaRepository<OrderUser,Long>,JpaSpecificationExecutor<OrderUser> {
    @Query("from OrderUser o join fetch o.order")
    public OrderUser findOne(Long id);
}
