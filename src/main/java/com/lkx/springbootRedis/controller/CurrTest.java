package com.lkx.springbootjedis.controller;

import com.lkx.springbootjedis.dao.OrderRepository;
import com.lkx.springbootjedis.pojo.Order;
import io.lettuce.core.RedisAsyncCommandsImpl;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCommands;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author lee Cather
 * @date 2018-10-08 15:36
 * desc : 并发测试
 */
@RestController
public class CurrTest {
    private static int count = 0;
    private Logger logger = LoggerFactory.getLogger(CurrTest.class);
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    OrderRepository orderRepository;
    @GetMapping("testCurr")
    public String testCurr(String num) {
        Boolean c = redisTemplate.opsForValue().setIfAbsent(num,"123");
        if (c!=null && c){
            redisTemplate.expire(num,30,TimeUnit.SECONDS);
            System.out.println("锁住了");
            return "锁住了";
        }else {
            System.out.println("没获得锁");
            return "没获得锁";
        }
//        redisTemplate.execute((RedisCallback) connection -> {
//             Boolean b = connection.set("123".getBytes(),"123".getBytes(),Expiration.from(300000,TimeUnit.MILLISECONDS),RedisStringCommands.SetOption.SET_IF_ABSENT);
//
////            RedisAsyncCommandsImpl connect =  (RedisAsyncCommandsImpl) connection.getNativeConnection();
////            StatefulRedisConnection ct = connect.getStatefulConnection();
////            RedisCommands rc =  ct.sync();
////            String result  = rc.set("abc", "1", SetArgs.Builder.nx().px(80000));
////            ct.close();
////            return result;
//            System.out.println(b);
//            return null;
//        });
    }

    /**
     *
     * @param num 余额
     * @return
     */
    @GetMapping("takeTicket")
    public String takeTicket(Integer num){
        Long ticket;
        try {//自增 返回自增后的键值
            ticket =  redisTemplate.opsForValue().increment("ticket",1);
        }catch (Exception e){
            logger.error(e.getMessage());
            return e.getMessage();
        }
      if (ticket<=num){
          Order order = new Order();
          order.setOrderNum(String.valueOf(UUID.randomUUID()));
          order.setOderName(ticket.toString());
          orderRepository.save(order);
//          synchronized (this){
              count++;
              logger.info("抢到了 位数:{}---->毫秒数{}!",count,System.currentTimeMillis());
//          }
            return "成功抢到";
      }else {
            logger.error("秒杀结束"+System.currentTimeMillis());
            return "秒杀结束";
      }
    }
}
