package com.lkx.springbootjedis;

import com.lkx.springbootjedis.pojo.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJedisApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void contextLoads() {
        Order order = new Order();
        order.setOderName("test");
        order.setOrderNum("45789654");
        redisTemplate.opsForValue().setIfAbsent(order.getOrderNum(),order);
        System.out.println(redisTemplate.opsForValue().get(order.getOrderNum()));
    }
    @Test
    public void testRedisCurrLock(){
        Order order = new Order();
        order.setOderName("test");
        order.setOrderNum("32455435");
        redisTemplate.opsForValue().set("1","2");
        Thread thread1 = new Thread(() -> {
            if (redisLock(order.getOrderNum(),System.currentTimeMillis()+"")){
                System.out.println("t1获得锁");
            }
        });
        Thread thread2 = new Thread(() ->{
            if (redisLock(order.getOrderNum(),System.currentTimeMillis()+"")){
                System.out.println("t2获得锁");
            }
        });
        thread1.start();
        thread2.start();
    }
    private boolean redisLock(String key, String value){
        if (redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }
        //判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁
        String currentValue = (String) redisTemplate.opsForValue().get(key);
        //如果锁过期
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){//currentValue不为空且小于当前时间
            //获取上一个锁的时间value
            String oldValue = (String) redisTemplate.opsForValue().getAndSet(key,value);//对应getset，如果key存在

            //假设两个线程同时进来这里，因为key被占用了，而且锁过期了。获取的值currentValue=A(get取的旧的值肯定是一样的),两个线程的value都是B,key都是K.锁时间已经过期了。
            //而这里面的getAndSet一次只会一个执行，也就是一个执行之后，上一个的value已经变成了B。只有一个线程获取的上一个值会是A，另一个线程拿到的值是B。
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue) ){
                //oldValue不为空且oldValue等于currentValue，也就是校验是不是上个对应的商品时间戳，也是防止并发
                return true;
            }
        }
        return false;
    }
}
