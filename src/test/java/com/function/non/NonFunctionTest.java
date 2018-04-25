package com.function.non;

import com.java.common.function.non.NonFunction;
import org.junit.Test;


public class NonFunctionTest {
    @Test
    public void testNon(){
        new NonFunction(){
            void callParents(){
                super.test();
            }
        }.callParents();
    }
}
