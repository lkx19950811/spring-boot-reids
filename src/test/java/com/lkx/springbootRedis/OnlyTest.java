package com.lkx.springbootRedis;

import com.lkx.springbootRedis.pojo.HashPojo;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lee Cather
 * @date 2018-10-19 11:25
 * desc :
 */
public class OnlyTest {
    @Test
    public void con(){
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("a","a");
        HashPojo o1 = new HashPojo(2);
        HashPojo o2 = new HashPojo(4);
        HashMap hashMap = new HashMap();
        hashMap.put("aaa","aaa");
        hashMap.put("bbb","bbb");
        hashMap.put(o1,o1);
        hashMap.put(o2,o2);
        Object object= hashMap.get(o1);
        System.out.println(object);
    }
}
