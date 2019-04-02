package com.spring.bizservice.demo.util.mainstr;

/**
 * @author liyongzhen
 * @create 2019-03-25 15:04
 **/
public class ArrDemo {
    public static void main(String[] args) {
        int arr[]  = {1,3,5,2,4,6,7,8};
        int max = 0;
        int maxLen = arr.length;
        for(int i = arr.length-1;i>0;i--){
            int [] newArr = new int[i];
            System.arraycopy(arr,0,newArr,0,i);
            int currentLength =1+dp(newArr,arr[i]);
            if(currentLength>max){
                max= currentLength;
            }
            if(max==maxLen){
                break;
            }
        }
        System.out.println(max);

    }

    public static int dp(int[] arr,int end){
        if(arr.length == 1){
            if(arr[0] <= end){
                return 1;
            }else{
                return 0;
            }
        }
        for(int i =arr.length-1;i>=0;i--){
            if(arr[i]<=end){
                int[] newArr = new int[i];
                System.arraycopy(arr,0,newArr,0,i);
                int containLen = dp(newArr,arr[i])+1;
                int notContainLen = dp(newArr,end);
                return containLen>notContainLen?containLen:notContainLen;
            }
        }
        return 0;
    }
}
