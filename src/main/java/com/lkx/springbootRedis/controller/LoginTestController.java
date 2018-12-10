package com.lkx.springbootRedis.controller;

import com.lkx.springbootRedis.dao.OrderRepository;
import com.lkx.springbootRedis.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * desc :
 *
 * @author : lee Cather
 * @date : 2018-12-07 17:47
 */
@RestController
public class LoginTestController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    OrderRepository repository;
    @GetMapping("/testNetty")
    public String setRedis(){
        String test = new Random().nextInt(100) + System.currentTimeMillis() + "";
        redisTemplate.opsForValue().set(test,test);
        redisTemplate.expire(test,10,TimeUnit.SECONDS);
        return test;
    }
    @GetMapping("/testMysql")
    public Object setMysql(){
        Order order = new Order();
        order.setOderName("12");
        repository.save(order);
        return order;
    }
}
