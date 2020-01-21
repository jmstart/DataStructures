package com.jiaming.queue;

import java.util.Scanner;

/**
 * @author jmstart
 * @create 2020-01-11 18:03
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        //这个队列的弊端不能复用
        //测试队列
        //创建队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列里取出数据");
            System.out.println("h(head): 查看队列头数据");
            key = scanner.next().charAt(0); //接收一个字符

            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数:");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    //关流
                    scanner.close();
                    loop = false; //退出循环
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");

    }
}

//使用数组模拟队列 写个类ArrayQueue
class ArrayQueue {
    private int maxSize; //数组的最大容量
    private int front; //队列头
    private int rear; //队列尾
    private int[] arr; //该数组用于存放数据，模拟队列

    //创建队列构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; //指向队列头的前一个位置(不是队头)
        rear = -1; //指向队列尾最后一个数据(是队尾)
    }

    //判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否空
    public boolean isEmpty() {
        return rear == front;
    }

    //入队
    public void addQueue(int n) {
        //判断是否满
        if (isFull()) {
            System.out.println("队列已满,不能在添加数据了！");
            return;
        }
        //rear后移 代表入队
        rear++;
        arr[rear] = n;
    }

    //出队
    public int getQueue() {
        //判断是否空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能获取数据！");
        }
        //front后移 代表出队
        front++;
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue() {
        //判空
        if (isEmpty()) {
            System.out.println("队列为空！");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //获取头数据
    public int headQueue() {
        //判空
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }

        return arr[front + 1];
    }

}