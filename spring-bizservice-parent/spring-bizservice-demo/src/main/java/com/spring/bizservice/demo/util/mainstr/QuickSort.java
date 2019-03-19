package com.spring.bizservice.demo.util.mainstr;

/**
 * @author liyongzhen
 * @create 2019-03-01 11:34
 **/
public class QuickSort {

    public static void main(String[] args) {
        int arr[] = {33, 38, 65, 97, 76, 13, 27, 49};

        int len = arr.length - 1;
        int i = 0;

        sort(arr, i, len);

        for (int n : arr) {
            System.out.println(n);
        }

    }


    public static void sort(int e[], int first, int end) {
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
