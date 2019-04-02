package com.spring.bizservice.demo.util.mainstr.sone;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

/**
 * @author liyongzhen
 * @create 2019-03-19 9:34
 **/
public class QuickSort {
    public static void main(String[] args) {
        int arrs[] = {56, 48, 71, 13, 25, 66, 54};
        int first = 0;
        int end = arrs.length - 1;
        sort(arrs, first, end);
        for (int c : arrs) {
            System.out.println(c);
        }


    }

    public static void sort(int[] e, int first, int end) {
        int i = first;
        int j = end;
        int temp = e[first];

        while (i < j) {
            while (i < j && e[j] >= temp) {
                j--;
            }
            e[i] = e[j];
            while (i < j && e[i] <= temp) {
                i++;
            }
            e[j] = e[i];
        }
        e[i] = temp;

        if (first < i - 1) {
            sort(e, first, i - 1);
        }
        if (end > i + 1) {
            sort(e, i + 1, end);
        }
    }
}
