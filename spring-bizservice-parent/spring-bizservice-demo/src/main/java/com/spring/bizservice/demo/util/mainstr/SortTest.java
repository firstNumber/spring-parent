package com.spring.bizservice.demo.util.mainstr;

/**
 * @author liyongzhen
 * @create 2019-03-01 11:20
 **/
public class SortTest {
    public static void main(String[] args) {
        int arg[] = new int[] { 2, 1, 4, 5, 8, 7, 6, 3, 9, 0 };
        new SortTest().sort(arg);

    }

    public void sort(int [] args){
        int time1=0 ,time2=0;

        for(int i = 0;i<args.length;i++){
            ++time1;
            for(int j = i+1;j<args.length;j++){
                ++time2;
                int temp;
                if(args[i]>args[j]){
                    temp =args[j];
                    args[j]=args[i];
                    args[i] = temp;
                }
            }
        }

        System.out.println("外层循环次数"+time1+"内层循环次数"+time2);

        for(int n:args){
            System.out.println(n);
        }
    }
}
