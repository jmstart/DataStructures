package com.jiaming.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author jmstart
 * @create 2020-02-08 14:11
 * 逆波兰表达式(后缀表达式)实现多项式计算
 */
public class PolandNotation {

    public static void main(String[] args) {
        //先定义逆波兰表达式 (3+4)*5-6 => 3 4 + 5 * 6 -
        String suffixExpression = "3 4 + 5 * 6 -";

        List<String> listString = getListString(suffixExpression);

        int calculate = calculate(listString);

        System.out.printf("%s = %d ",suffixExpression,calculate);
    }

    //将 suffixExpression 放到一个 ArrayList里 在配合 栈来计算
    public static List<String> getListString(String suffixExpression){
        // 1.分割suffixExpression
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList();

        //取出表达式元素 添加到集合中
        for (String s : split) {
            list.add(s);
        }

        return list;
    }

    //计算
    public static int calculate(List<String> ls){
        //创建栈
        Stack<String> stack = new Stack<>();
        //遍历 ls
        for (String s : ls) {
            //使用正则取数据
            if (s.matches("\\d+")){ //判断否是数字 是则入栈
                //入栈
                stack.push(s);
            }else {
                //如是符号 则pop出二个数 计算 结果入栈
                int num2 = Integer.parseInt(stack.pop()); //栈顶
                int num1 = Integer.parseInt(stack.pop()); //次栈顶
                //结果
                int res = 0;
                //判断是什么符号
                if (s.equals("+")){
                    res = num1 + num2;
                }else if (s.equals("-")){
                    res = num1 - num2;
                }else if (s.equals("*")){
                    res = num1 * num2;
                }else if (s.equals("/")){
                    res = num1 /num2;
                }else {
                    throw new RuntimeException("运算符有误...");
                }

                //结果入栈
                stack.push(res + "");
            }
        }
        //返回结果
        return Integer.parseInt(stack.pop());
    }

}
