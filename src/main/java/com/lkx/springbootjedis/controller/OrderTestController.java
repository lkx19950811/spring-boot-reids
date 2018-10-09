package com.lkx.springbootjedis.controller;

import com.lkx.springbootjedis.dao.OrderRepository;
import com.lkx.springbootjedis.dao.OrderUserRepository;
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
        return orderUserRepository.findOne(1l);
    }
    @GetMapping("orderTest")
    public Object orderTest(){
        return orderRepository.findAll();
    }
}
