package com.function.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyongzhen
 * @create 2018-04-25 15:36
 **/
public class TestMap {

    @Test
    public void testLamdba(){
        Map<String,Object> map = new HashMap<String,Object>(){{
            put("A","A");
            put("B","B");
            put("C","C");
            put("D","D");
            put("E","E");
        }};
        map.forEach((k,v)-> System.out.println(k+"\t"+v));

    }
}
