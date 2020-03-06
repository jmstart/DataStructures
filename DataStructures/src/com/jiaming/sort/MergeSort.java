package com.jiaming.sort;

import java.util.Arrays;

/**
 * @author jmstart
 * @create 2020-03-06 18:31
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        //待排序数组
        int[] arr = {5, 2, 7, 1, 0, 9, 3, 8, 4, 6, -1, 2};
        //临时数组
        int[] temp = new int[arr.length];

        //测试归并排序算法
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    //分解 + 合并 = 归并排序
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        //判断
        if (left < right) {
            int mid = (left + right) / 2; //计算中间索引
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            //分解完毕后,合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并
     *
     * @param arr   待排序数组
     * @param left  最左边索引
     * @param mid   中间索引
     * @param right 最右边索引
     * @param temp  临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; //左边有序序列的初始索引
        int j = mid + 1; //右边有序序列的初始索引
        int t = 0; //临时数组的初始索引

        //1.先把左右二组有序的数据依次判断大小放入临时数组直到一组放完
        while (i <= mid && j <= right) {
            //如条件成立,则判断二组的初始值大小,小的开始放入临时数组
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //2.在把剩下的一组数据依次放入临时数组中
        while (i <= mid) { //说明左边那组数据还有剩余的
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) { //说明右边那组数据还有剩余的
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //3.最后将临时数组导入原始数组
        t = 0;
        int tempLeft = left; //变化的临时索引
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }

}
