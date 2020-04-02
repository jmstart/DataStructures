package com.jiaming.stack;

/**
 * @author jmstart
 * @create 2020-02-06 14:21
 * 用栈模拟多项式计算
 * 使用的数据结构:
 * 栈 中缀表达式
 */
public class Calculator {

    public static void main(String[] args) {
        //操作栈完成功能

        //操作的表达式
        //大体上没毛病,但细节有瑕疵如 70+2*6-4 就得不到正确答案(已解决)68行处
        //String expression = "7+2*6-4";  //15
        String expression = "70+2*6-4";  //78

        //创建二个栈 一个数栈 一个符号栈
        ArrayStack1 numStack = new ArrayStack1(10);
        ArrayStack1 operStack = new ArrayStack1(10);

        //定义需要的相关变量
        int index = 0; //用于扫描表达式的
        int num1 = 0;
        int num2 = 0;
        char oper = 0;
        int res = 0;
        char ch = ' '; //保存每次扫描出来的符号
        String keepNum = ""; //变量字符串,来拼接多位数

        //用 while 扫描 expression
        while (true) {
            //取字符
            ch = expression.substring(index, index + 1).charAt(0);

            //判断ch是什么,然后做相应处理
            //如果是运算符
            if (operStack.isOper(ch)) {
                //判断当前符号栈是否空
                if (!operStack.isEmpty()) {
                    //不为空
                    //1.如要入栈符号优先级小于等于栈中符号优先级
                    // 则符号栈内符号出栈,数栈也出二个数来进行运算
                    // 则结果入数栈, 要入栈的符号入符号栈
                    if (operStack.priority(ch) <= operStack.priority((char) operStack.peek())) {
                        //数栈出
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        //符号栈出
                        oper = (char) operStack.pop();
                        //运算
                        res = numStack.cal(num1, num2, oper);
                        //结果入数栈, 符号入栈
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        //2.如要入栈符号优先级大于栈中符号优先级 入栈
                        operStack.push(ch);
                    }

                } else {
                    //为空 入栈
                    operStack.push(ch);
                }
            } else {
                //如果是数字直接入栈
                //按照 码表对照 1是49

                //numStack.push(ch -48);
                //这个会导致 70+2*6-4 出错

                //当处理多位数时,不能直接入栈,要向下在看下,如是符号则入栈,如是数字继续遍历
                //因此要用一个变量字符串,来拼接多位数

                // 1.先拼接字符串
                keepNum += ch;

                // 2.如果ch 是expression的最后一位 直接入栈
                if (index == expression.length() - 1) {

                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    // 3.判断 如是数字继续扫描 是字符则入栈
                    //看index后面一位是否是字符
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如是字符 则数字进栈
                        numStack.push(Integer.parseInt(keepNum));

                        //清空 keepNum
                        keepNum = "";
                    }
                }
            }
            //index +1 并判断是否扫描到expression是否到最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //扫描完毕, 数和符号依次出栈运算, 最后结果保留在数栈中
        while (true) {
            //如符号栈为空,结束
            if (operStack.isEmpty()) {
                break;
            }

            //数栈出
            num1 = numStack.pop();
            num2 = numStack.pop();
            //符号栈出
            oper = (char) operStack.pop();
            //运算
            res = numStack.cal(num1, num2, oper);

            numStack.push(res);
        }
        //输出结果
        System.out.printf("表达式: %s = %d", expression, numStack.pop());
    }
}

//栈
class ArrayStack1 {

    //栈的最大容量
    private int maxSize;
    //模拟栈
    private int[] stack;
    //栈顶
    private int top = -1;

    //构造器
    public ArrayStack1(int maxSize) {
        this.maxSize = maxSize;
        //初始化数组
        stack = new int[this.maxSize];
    }

    //构造一个 能看到栈顶的方法 但不pop这个元素
    public int peek() {
        return stack[top];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈(push)
    public void push(int value) {

        //判满
        if (isFull()) {
            System.out.println("栈满...");
            return;
        }

        //入栈
        top++;
        stack[top] = value;
    }

    //出栈(pop)
    public int pop() {

        //判空
        if (isEmpty()) {
            //用异常结束方法
            throw new RuntimeException("栈空...");
        }

        //出栈
        int value = stack[top];
        top--;

        return value;
    }

    //遍历栈
    public void list() {

        //判空
        if (isEmpty()) {
            System.out.println("栈空...");
            return;
        }

        //遍历
        for (int i = top; i >= 0; i--) {

            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }

    }

    //判断优先级,优先级自己决定
    //优先级: 数字越大,优先级越高 假定现在只有 + - * /
    public int priority(char oper) {
        //判断
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {

        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, char oper) {
        //结果
        int res = 0;

        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                //后弹出数栈的数 减去 先弹出的数
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}
