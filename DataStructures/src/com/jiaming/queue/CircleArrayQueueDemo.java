package com.jiaming.queue;

import java.util.Scanner;

/**
 * @author jmstart
 * @create 2020-01-11 18:03
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        //测试环形队列
        //创建队列
        //4个大小 有效数据最多3个 一个空间是留出来的
        CircleArrayQueue queue = new CircleArrayQueue(4);
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
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数:");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
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
class CircleArrayQueue {
    private int maxSize; //数组的最大容量
    private int front; //队列头
    private int rear; //队列尾
    private int[] arr; //该数组用于存放数据，模拟队列

    //创建队列构造器
    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0; //指向队列头的位置(是队头)
        rear = 0; //指向队列尾最后一个数据的后一个位置(不是队尾)
    }

    //判断队列是否满
    public boolean isFull() {
        //*
        return (rear + 1) % maxSize == front;
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
        //rear指向后一个数据 加加就不用了
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //出队
    public int getQueue() {
        //判断是否空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能获取数据！");
        }
        //front 现在代表第一个元素 要先找个变量保存其值 在指针后移 在返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        //判空
        if (isEmpty()) {
            System.out.println("队列为空！");
            return;
        }
        //要从front开始遍历 遍历队列有效元素
        for (int i = front; i < front + size(); i++) {  //*
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出队列有效元素的个数
    public int size(){ //*
        return (rear + maxSize - front) % maxSize;
    }

    //获取头数据
    public int headQueue() {
        //判空
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }

        return arr[front];
    }

}