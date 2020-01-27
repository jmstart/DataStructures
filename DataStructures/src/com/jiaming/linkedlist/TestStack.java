package com.jiaming.linkedlist;

import java.util.Stack;

/**
 * @author jmstart
 * @create 2020-01-27 16:01
 * 演示栈的基本使用
 */
public class TestStack {

    public static void main(String[] args) {

        //创建一个栈
        Stack<String> stack = new Stack<String>();

        //入栈
        stack.add("孙悟空");
        stack.add("孙悟饭");
        stack.add("孙悟天");

        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }
}
