package com.jiaming.search;

import java.util.Arrays;

/**
 * @author jmstart
 * @create 2020-03-14 17:35
 * 线性查找算法
 */
public class SeqSearch {

    public static void main(String[] args) {
        //原始数组
        int[] arr = {1, 32, 56, 0, 3, -1};
        System.out.println(Arrays.toString(arr));

        //测试方法
        int index = seqSearch(arr, 56);

        if (index != -1){
            System.out.println("数据找到了,下标为: " + (index + 1));
        }else {
            System.out.println("没有找到...");
        }
    }


    /**
     * 线性查找
     * @param arr  //待查找的数组
     * @param value //要查找的值
     * 思路:逐一比对,发现目标后,则返回数据下标
     */
    public static int seqSearch(int[] arr, int value){
        //遍历
        for (int i = 0; i < arr.length; i++) {
            //判断比对
            if (value == arr[i]){
                return i;
            }
        }
        //没有返回 -1
        return -1;
    }
}
