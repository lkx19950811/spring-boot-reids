package com.lkx.springbootRedis.controller;

import com.lkx.springbootRedis.dao.OrderRepository;
import com.lkx.springbootRedis.dao.OrderUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee Cather
 * @date 2018-10-09 11:18
 * desc :
 */
@RestController
public class OrderTestController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderUserRepository orderUserRepository;
    @GetMapping("orderUserTest")
    public Object orderUserTest(){
        return orderUserRepository.findById(1L);
    }
    @GetMapping("orderTest")
    public Object orderTest(){
        return orderRepository.findAll();
    }
}
