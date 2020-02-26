package com.jiaming.sort;

import java.util.Arrays;

/**
 * @author jmstart
 * @create 2020-02-26 18:35
 * 冒泡排序 时间复杂度 O(n^2)
 */
public class BubbleSort {

    public static void main(String[] args) {
        //创建数组
        int arr[] = {3, 9, -1, 10, -2};

        //临时变量,交换时使用
        int temp = 0;

        //比较几轮
        for (int i = 0; i < arr.length - 1; i++) {
            //每一轮比较的次数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //判断
                if (arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }

}
