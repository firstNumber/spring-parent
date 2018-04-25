package com.function.two;


import com.java.common.function.two.FunctionInterfaceTwo;
import org.junit.Test;

public class FunctionInterfaceTwoTest {

    @Test
    public void testLamdba(){
        func((x)-> System.out.println("Hello World  "+x));
    }

    private void func(FunctionInterfaceTwo o) {
        int x =1;
        o.test(x);
    }
}
