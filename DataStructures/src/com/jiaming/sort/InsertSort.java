package com.jiaming.sort;

import java.util.Arrays;

/**
 * @author jmstart
 * @create 2020-03-01 8:37
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {

        //原始数组
        int[] arr = {101, 33, 119, 1,66,73,-1,28};

        //测试插入排序
        insertSort(arr);
    }

    //插入排序
    public static void insertSort(int[] arr) {

        //执行的轮数
        for (int i = 1; i < arr.length; i++) {

            //定义待插入的数
            int insertVal = arr[i];
            //定义前面有序的数据索引
            int insertIndex = i - 1;

            //确定数据位置              //这里要是改成大于 那么排序结果就是从大到小了
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                //如果判断成立,则把前面的大数挪到后面
                arr[insertIndex + 1] = arr[insertIndex];
                //继续循环
                insertIndex--;
            }

            //放置小数  当退出 while循环时 insertIndex是减一的 所以要加一
            if ((insertIndex + 1) != i) {
                arr[insertIndex + 1] = insertVal;
            }

            System.out.println("第" + i +"轮后...");
            System.out.println(Arrays.toString(arr));
        }

    }
}
