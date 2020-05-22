package com.jiaming.sort;

import java.util.Arrays;

/**
 * @author jmstart
 * @create 2020-02-29 18:15
 * 选择排序 时间复杂度 O(n^2)
 */
public class SelectSort {

    public static void main(String[] args) {
        //原始数组
        int[] arr = {101, 33, 119, 1,66,73,-1,28};

        //测试选择排序(从小到大排序)
        selectSort(arr);

        System.out.println("------------华丽的分割线--------------");

        //测试选择排序(从大到小排序)
        selectSort1(arr);
    }

    //选择排序
    public static void selectSort(int[] arr) {

        //遍历的轮数
        for (int i = 0; i < arr.length - 1; i++) {

            //第一轮找最小值
            int minIndex = i; //假定最小数下标是0
            int min = arr[minIndex];

            //循环找最小值
            for (int j = i + 1; j < arr.length; j++) {
                //判断
                if (min > arr[j]) {
                    //重置最小值
                    min = arr[j];
                    minIndex = j;
                }
            }

            //交换最小值,最小值和第一个数交换
            if (minIndex != i) { //如果minIdex = i,就说明第一个值就是最小值,那就不用交换了
                //arr[i]就是前面定义的数组第一个值,假定最小的那个值
                //arr[minIndex]是经过上面比较得到的真正最小值
                //交换双方位置
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            System.out.println("第" + (i + 1) +"轮后...");
            System.out.println(Arrays.toString(arr));
        }

    }

    //从大到小
    public static void selectSort1(int[] arr) {

        //遍历的轮数
        for (int i = 0; i < arr.length - 1; i++) {

            //第一轮找最小值
            int minIndex = i; //假定最小数下标是0
            int min = arr[minIndex];

            //循环找最小值
            for (int j = i + 1; j < arr.length; j++) {
                //判断
                if (min < arr[j]) {     //就改一下这里就OK了
                    //重置最小值
                    min = arr[j];
                    minIndex = j;
                }
            }

            //交换最小值,最小值和第一个数交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            System.out.println("第" + (i + 1) +"轮后...");
            System.out.println(Arrays.toString(arr));
        }

    }
}


