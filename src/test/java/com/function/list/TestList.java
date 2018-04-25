package com.function.list;


import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestList {

    @Test
    public void testLambda(){
        List<String> items = new ArrayList<String>(){{
            add("A");
            add("B");
            add("C");
            add("D");
        }};

        items.forEach(item-> System.out.println(item));

        items.forEach( System.out::println);

        items.stream().filter(s->s.contains("B")).forEach(System.out::println);

    }
}
