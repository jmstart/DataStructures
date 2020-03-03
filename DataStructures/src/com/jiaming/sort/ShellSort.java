package com.jiaming.sort;

import java.util.Arrays;

/**
 * @author jmstart
 * @create 2020-03-03 18:11
 * 希尔排序(交换法,移动法)
 */
public class ShellSort {

    public static void main(String[] args) {
        //原始数组
        int[] arr = {8, 9, 1, 7, 6, 3, 5, 4, 2, 0};

        //测试希尔排序
        shellSort(arr);

    }

    //希尔排序
    public static void shellSort(int[] arr){

        //gap:循环的步数 分成gap组
        for (int gap = arr.length / 2; gap > 0; gap /= 2){
            //分组循环比较
            for (int i = gap; i < arr.length; i++){
                for (int j = i - gap; j >= 0; j -= gap){
                    //判断每组的大小
                    if (arr[j] > arr[j + gap]){
                        //判断成立 交换
                        int temp = arr[j + gap];
                        arr[j + gap] = arr[j];
                        arr[j]  = temp;
                    }
                }
            }

        }
        System.out.println(Arrays.toString(arr));

    }

}
