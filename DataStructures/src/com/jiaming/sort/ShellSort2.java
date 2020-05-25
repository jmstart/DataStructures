package com.jiaming.sort;

import java.util.Arrays;

/**
 * @author jmstart
 * @create 2020-03-03 20:13
 * 希尔排序(移位法)
 */
public class ShellSort2 {

    public static void main(String[] args) {
        //原始数组
        int[] arr = {8, 9, 1, 7, 6, 3, 5, 4, 2, 0};

        //测试希尔排序
        shellSort2(arr);

    }

    //希尔排序
    public static void shellSort2(int[] arr){

        //gap:增量,间隔的步数
        for (int gap = arr.length / 2; gap > 0; gap /= 2){

            //从gap个元素开始,对各个组进行 "插入排序"
            //插入排序
            for (int i = gap; i < arr.length; i++){

                //定义待插入的数,每组比较的后面那个数就是大索引的数
                int j = i;
                //取出每组比较的后面那个数
                int temp = arr[j];

                //判断,每组的后面元素是否小于前面元素
                if (arr[j] < arr[j - gap]){
                    //成立,移位
                    while (j - gap >= 0 && temp < arr[j - gap]){
                        //大的数移位到后面
                        arr[j] = arr[j - gap];
                        //进行下一组比较
                        j -= gap;
                    }
                    //当退出while循环,就代表找到了"小数"该插入的位置,把小数放到前面
                    //当前这j是在while循环中减完步数后的,相当于是arr[j-gap]
                    arr[j] = temp;
                }
            }

        }
        System.out.println(Arrays.toString(arr));

    }

}
