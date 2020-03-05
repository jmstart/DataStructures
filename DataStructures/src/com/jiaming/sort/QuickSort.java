package com.jiaming.sort;

import java.util.Arrays;

/**
 * @author jmstart
 * @create 2020-03-05 17:35
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] arr = {-1, 8, 6, 0, -3, 2, -5, 7, 2};

        //测试快速排序
        quickSort(arr, 0, arr.length - 1);
        //输出排序结果
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序
     * @param arr  待排序的数组
     * @param left  最左边的索引
     * @param right  最右边的索引
     */
    public static void quickSort(int[] arr, int left, int right){
        //记录索引
        int l = left;
        int r = right;
        //设置中间值
        int pivot = arr[(left + right) / 2];
        //临时变量
        int temp = 0;
        //while循环的功能将比pivot小的值放在pivot的左边,比pivot值大的放在pivot的右边
        while (l < r){
            //这个while的功能是在pivot的左边找到一个大于等于pivot的值
            while (arr[l] < pivot){
                l += 1;
            }
            //这个while的功能是在pivot的右边找到一个小于等于pivot的值
            while (arr[r] > pivot){
                r -= 1;
            }
            //判断是否退出
            if (l >= r){
                break;
            }
            //如果在左边找到比pivot大的或者在右边找到比pivot小的,则交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //下面这个二个判断是负责比较数据中相等的值而准备的
            //交换完成后判断,如果arr[l] == pivot,则r--
            if (arr[l] == pivot){
                r -= 1;
            }
            //交换完成后判断,如果arr[r] == pivot,则l++
            if (arr[r] == pivot){
                l += 1;
            }

        }

        //如果,l == r,则 l++,r--,错开 l 和 r,否则后栈溢出StackOverflowError
        if (l == r){
            l += 1;
            r -= 1;
        }

        //向左递归,排序pivot左边数据顺序
        //left和right是不变的,而l和r是变化的
        if (left < r){
            quickSort(arr, left, r);
        }
        //向右递归,排序pivot右边数据顺序
        //left和right是不变的,而l和r是变化的
        if (right > l){
            quickSort(arr, l, right);
        }
    }

}
