package com.jiaming.search;

import java.util.Arrays;

/**
 * @author jmstart
 * @create 2020-03-18 13:12
 * 斐波那契查找
 */
public class Fibonacci {

    public static void main(String[] args) {
        //原始数组
        int[] arr = {1, 5, 8, 80, 1000, 1223};

        //输出斐波那契数列
        //System.out.println(Arrays.toString(fib()));
        //测试斐波那契查找
        int index = fibSearch(arr, 5);
        System.out.println("索引: " + (index + 1));
    }

    //查找时需要用到斐波那契数列,所以构建一个
    //采用非递归的方式
    public static int[] fib() {
        //构件数列大小
        int[] f = new int[20];
        //定义f[0]和f[1]
        f[0] = 1;
        f[1] = 1;
        //开始循环获取值
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找
     * 使用非递归编写
     *
     * @param arr   待查数组
     * @param value 待查值
     * @return 返回索引
     */
    public static int fibSearch(int[] arr, int value) {
        //定义最低位和最高位索引
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //斐波那契分割数值的下标
        int mid = 0; //存放中间值索引
        int[] f = fib(); //获取数列
        //获取斐波那契分割数值的下标(k)
        while (high > f[k] - 1) {
            k++;
        }
        //原数组可能不够大,需要扩充
        int[] temp = Arrays.copyOf(arr, f[k]);
        //填充后面的值
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //开始查找
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (value < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (value > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        //没有找到
        return -1;
    }
}
