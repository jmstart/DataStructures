package com.jiaming.search;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author jmstart
 * @create 2020-03-15 18:36
 * 二分查找法
 * 实现找到相同数据,都返回下标
 */
public class BinarySearch2 {

    public static void main(String[] args) {
        //原始数组
        int[] arr = {1, 23, 56, 78, 89, 100, 666, 666, 666, 888};
        System.out.println(Arrays.toString(arr));

        //测试二分查找算法
        ArrayList<Integer> list = binarySearch(arr, 0, arr.length, 666);
        System.out.println("相同数据的索引值: " + list);
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
    public static ArrayList<Integer> binarySearch(int[] arr, int left, int right, int value) {
        //找不到的情况
        if (left > right){
            return new ArrayList<>(); //返回空集合,代表没有找到数据
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
        } else {
            //返回相同数据的全部下标:
            //找到数据后,先不要返回,再继续左右遍历找看有没有和 value相同的值,如果找到了,就都放入集合中
            ArrayList<Integer> indexList = new ArrayList<>();

            //向左扫描
            int temp = mid - 1; //临时变量
            while (true){
                if (temp < 0 || arr[temp] != value){
                    break;
                }
                //如果找到,则放入集合
                indexList.add(temp);
                temp -= 1; //temp左移
            }
            indexList.add(mid); //把找到数据的索引放入集合,这个索引是相同数据的中间索引

            //向右扫描
            temp = mid + 1; //临时变量
            while (true){
                if (temp > arr.length -1 || arr[temp] != value){
                    break;
                }
                //如果找到,则放入集合
                indexList.add(temp);
                temp += 1; //temp右移
            }

            return indexList;
        }

    }

}
