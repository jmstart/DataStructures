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

        //将后缀表达式转换成集合形式,方便运算
        List<String> listString = getListString(suffixExpression);

        //获得表达式运算结果
        int calculate = calculate(listString);

        //输出
        System.out.printf("%s = %d ",suffixExpression,calculate);
    }

   //转换suffixExpression 
   public static List<String> getListString(String suffixExpression){
        // 1.分割suffixExpression
        String[] split = suffixExpression.split(" ");
       
        List<String> list = new ArrayList();

        //取出表达式元素,添加到集合中
        for (String s : split) {
            list.add(s);
        }

        return list;
    }

    //计算后缀表达式结果
    public static int calculate(List<String> ls){
        //创建栈
        Stack<String> stack = new Stack<>();
        //遍历后缀表达式 ls
        for (String s : ls) {
            //使用正则验证数据
            if (s.matches("\\d+")){ //判断否是数字,是则入栈
                //入栈
                stack.push(s);
            }else {
                //如是符号,则pop出二个数计算,结果入栈
                int num2 = Integer.parseInt(stack.pop()); //栈顶
                int num1 = Integer.parseInt(stack.pop()); //次栈顶
                //结果
                int res = 0;
                //判断是什么符号
                if (s.equals("+")){
                    res = num1 + num2;
                }else if (s.equals("-")){
                    //用后弹出栈的数据减去先弹出栈的数据
                    res = num1 - num2;
                }else if (s.equals("*")){
                    res = num1 * num2;
                }else if (s.equals("/")){
                    res = num1 /num2;
                }else {
                    throw new RuntimeException("运算符有误...");
                }

                //结果入栈 (将数据转换成字符串小技巧,加双引号)
                stack.push(res + "");
            }
        }
        //返回结果
        return Integer.parseInt(stack.pop());
    }

}
