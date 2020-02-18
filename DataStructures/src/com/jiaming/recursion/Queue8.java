package com.jiaming.recursion;

/**
 * @author jmstart
 * @create 2020-02-18 17:56
 * 八皇后问题
 * 算法:递归回溯
 */
public class Queue8 {

    //定义max有几个皇后
    int max = 8;
    //定义数组arr保存八皇后的位置   如 arr = {0,4,7,5,2,6,1,3}
    //数组值的作用   如 arr[i] = value
    //数组的下标 i+1代表的是第几行和代表是第几个皇后  value+1代表的是第几列
    int[] arr = new int[max];
    //统计有几种解法
    public static int count = 0;
    //统计判断冲突的次数
    public static int judgeCount = 0;

    public static void main(String[] args) {

        //测试八皇后递归算法
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d种解法\n",count);   //一共有92种解法
        System.out.printf("判断冲突%d次",judgeCount);  //判断冲突15720次
    }

    //摆放第 n个皇后
    private void check(int n){
        // n = 8, 就代表8个皇后都已经摆放好了,再摆就是第9个了,因为这是从0开始的
        if (n == max) {  //递归出口
            print(); //输出皇后顺序
            return;
        }

        //不等,那就依次判断皇后摆放位置是否冲突,直到最后一个皇后摆放完毕
        for (int i = 0; i < max; i++) {
            //先把当前第 n个皇后, 摆放在第一列,依次试探判断每一列
            arr[n] = i;
            //判断冲突
            if(judge(n)){
                //不冲突,摆放 n+1个皇后,开始递归
                check(n + 1);
            }
        }

    }

    /**
     * 查看当放置第 n个皇后,就去检查前面摆放好的皇后是否与此皇后冲突
     * @param n 第 n个皇后
     * @return
     */
    private boolean judge(int n){
        //统计判断冲突次数
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //判断是否在同一列和是否在同一斜线上
            // 1. arr[i] == arr[n] 是否在同一列
            // 2. Math.abs(n - i) == Math.abs(arr[n] - arr[i]) 是否在同一斜线上
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])){
                return false;
            }
        }
        return true;
    }

    //打印皇后摆放的位置
    private void print(){
        //统计正确的解法次数
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
