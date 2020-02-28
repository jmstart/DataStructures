package com.jiaming.sort;

import java.util.Arrays;

/**
 * @author jmstart
 * @create 2020-02-26 18:35
 * 冒泡排序(优化) 时间复杂度 O(n^2)
 */
public class BubbleSort {

    public static void main(String[] args) {
        //创建数组
        int arr[] = {3, 9, -1, 10, 20};

        //临时变量,交换时使用
        int temp = 0;

        //标识符,表示数字是否交换过(如果一趟中没有交换则代表已经排好顺序了)
        boolean flag = false;

        //比较几轮
        for (int i = 0; i < arr.length - 1; i++) {
            //每一轮比较的次数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //判断
                if (arr[j] > arr[j + 1]){
                    //执行到这里,则代表有数据需要交换
                    flag = true;
                    //交换数据
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //输出
            System.out.println("第"+ (i + 1) +"趟排序");
            System.out.println(Arrays.toString(arr));

            //判断这趟排序中是否一次都没交换过数据
            if (!flag){
                //退出大循环
                break;
            }else {
                //重置flag,进行下一次判断
                flag = false;
            }
        }


    }

}
