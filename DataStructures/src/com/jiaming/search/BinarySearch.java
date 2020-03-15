package com.jiaming.search;

/**
 * @author jmstart
 * @create 2020-03-15 14:50
 * 二分查找法(递归实现)
 */
public class BinarySearch {

    public static void main(String[] args) {
        //原始数组
        int[] arr = {1, 23, 56, 78, 89, 100, 666, 888};

        //测试二分查找算法
        int index = binarySearch(arr, 0, arr.length, 888);
        System.out.println("索引为: " + (index + 1));

    }

    /**
     * 二分查找递归实现
     *
     * @param arr
     * @param left  左边索引
     * @param right 右边索引
     * @param value 待查找的值
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int value) {
        //找不到的情况
        if (left > right){
            return -2;
        }
        //中间索引
        int mid = (left + right) / 2;
        //中间值
        int midVal = arr[mid];
        //判断,向哪里递归
        if (midVal < value) { //向右递归
            return binarySearch(arr, mid + 1, right, value);
        } else if (midVal > value) { //向左递归
            return binarySearch(arr, left, mid - 1, value);
        } else { //找到了
            return mid;
        }

    }

}
