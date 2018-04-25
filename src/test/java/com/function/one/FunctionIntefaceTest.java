package com.function.one;

import com.java.common.function.one.FunctionInterface;
import org.junit.Test;

public class FunctionIntefaceTest {
    @Test
    public void testLamdda(){
        func(new FunctionInterface() {
            @Override
            public void test() {
                System.out.println("Hello World");
            }
        });
        //使用lambda表达式代替上面的匿名函数
        func(()-> System.out.println("杨幂"));
    }

    private void func(FunctionInterface functionInterface) {
        functionInterface.test();
    }
}
