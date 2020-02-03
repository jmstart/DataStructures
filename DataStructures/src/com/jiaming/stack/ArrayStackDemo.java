package com.jiaming.stack;

import java.util.Scanner;

/**
 * @author jmstart
 * @create 2020-02-03 18:55
 * 用数组模拟栈
 */
public class ArrayStackDemo {

    public static void main(String[] args) {

        //测试
        ArrayStack stack = new ArrayStack(5);
        //输入
        String key = "";
        //循环标志
        boolean loop = true;
        //获得扫描器
        Scanner scanner = new Scanner(System.in);

        //制作菜单面板
        while (loop){

            System.out.println("show: 表示显示栈");
            System.out.println("exit: 表示退出");
            System.out.println("push: 表示添加数据(入栈)");
            System.out.println("pop : 表示取出数据(出栈)");
            System.out.println("请输入你的选择: ");
            //获得指令
            key = scanner.next();
            //判断指令
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数字: ");
                    stack.push(scanner.nextInt());
                    break;
                case "pop":
                    try {
                        int value = stack.pop();
                        System.out.printf("出栈的数据: %d\n",value);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出...");

    }
}

//栈
class ArrayStack{

    //栈的最大容量
    private int maxSize;
    //模拟栈
    private int[] stack;
    //栈顶
    private int top = -1;

    //构造器
    public ArrayStack (int maxSize){
        this.maxSize = maxSize;
        //初始化数组
        stack = new int[this.maxSize];
    }

    //判断栈满
    public boolean isFull(){
        return top == maxSize -1;
    }

    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈(push)
    public void push(int value){

        //判满
        if (isFull()){
            System.out.println("栈满...");
            return;
        }

        //入栈
        top ++;
        stack[top] = value;
    }

    //出栈(pop)
    public int pop(){

        //判空
        if (isEmpty()){
            //用异常结束方法
            throw new RuntimeException("栈空...");
        }

        //出栈
        int value = stack[top];
        top --;

        return value;
    }

    //遍历栈
    public void list(){

        //判空
        if (isEmpty()){
            System.out.println("栈空...");
            return;
        }

        //遍历
        for (int i = top; i >= 0; i--) {

            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }

    }

}
