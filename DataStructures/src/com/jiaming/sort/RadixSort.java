package com.jiaming.sort;

import java.util.Arrays;

/**
 * @author jmstart
 * @create 2020-03-08 18:43
 * 基数排序
 */
public class RadixSort {

    public static void main(String[] args) {
        //原始数组
        int[] arr = {8888, 52, 1, 567, 3, 789, 12};

        //测试基数排序
        radixSort(arr);
    }

    //基数排序方法实现,不支持负数
    public static void radixSort(int[] arr){

        //先得到原始数组中,最大的数的位数
        int max = arr[0]; //假定,第一个是最大的
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]){
                max = arr[i];
            }
        }
        //得到最大的位数,有几位就循环几轮
        int maxLength = (max + "").length();

        //定义一个二维数组,表示十个桶和每个桶可以放多少数据,一个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];

        //定义一个索引值,来表示每个桶都有几个数据
        //数组的十个值,代表每个桶的数据数量
        int[] bucketElementCount = new int[10];

        //排序开始
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //1.帮数字找到自己的桶,第一次是从个位找,第二次是从十位找,第三次百位找,依次类推
            for (int j = 0; j < arr.length; j++){
                //取出每个数相应位数(个十百千位等等),数字是几,就放入编号是几的桶
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];
                bucketElementCount[digitOfElement]++;
            }
            //2.把桶中的数据依次取出来,放到原始数组中
            int index = 0;
            for (int k = 0; k < bucket.length; k++){
                //如果桶中有数据,则放入原始数组中
                if (bucketElementCount[k] != 0){
                    //遍历这个有数据的桶,把数据放入原始数组
                    for (int l = 0; l < bucketElementCount[k]; l++){
                        //放入
                        arr[index++] = bucket[k][l];
                    }
                }
                //每个桶的数据放入后,需要清空这个桶,为下次排序使用
                bucketElementCount[k] = 0;
            }
        }
        //输出结果
        System.out.println(Arrays.toString(arr));
    }

}
