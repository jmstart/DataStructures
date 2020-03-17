package com.jiaming.search;

import java.util.Arrays;

/**
 * @author jmstart
 * @create 2020-03-17 13:31
 * 插值查找(优化的二分查找)
 * 如果要返回多个值的索引,参考这个二分查找 BinarySearch2
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        //原始数组,使用for循环生成这个数组,傻子才自己敲
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100};
        System.out.println(Arrays.toString(arr));

        //测试插值查找
        int index = insertValueSearch(arr, 0, arr.length - 1, 100);
        if (index == -1) {
            System.out.println("没有找到该值...");
        } else {
            System.out.println("找到了,索引值为: " + (index +1));
        }
    }

    /**
     * 插值查找
     * 思路:其实是和二分查找是一样的
     * 唯一变化之处就是找中间值这块,优化了查找中间值的方式,是为了提高查找效率
     * mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left])
     * @param arr
     * @param left  左边索引
     * @param right 右边索引
     * @param value 待查找的值
     * @return 待查找的下标
     */
    public static int insertValueSearch(int[] arr, int left, int right, int value) {
        //退出查找
        //后面这俩个判断条件很关键,不要觉得没有用,如果要找的值,不在数组范围内,就没有找的必要了
        if (left > right || value < arr[0] || value > arr[arr.length -1]) {
            return -1;
        }
        //中间索引(关键)
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        //中间值
        int midVal = arr[mid];
        //判断,向哪里递归
        if (midVal < value) { //向右递归
            return insertValueSearch(arr, mid + 1, right, value);
        } else if (midVal > value) { //向左递归
            return insertValueSearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }

    }
}
