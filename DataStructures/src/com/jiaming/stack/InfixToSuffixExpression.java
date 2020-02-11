package com.jiaming.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author jmstart
 * @create 2020-02-11 15:10
 *
 * 中缀表达式转后缀表达式
 */
public class InfixToSuffixExpression {

    public static void main(String[] args) {
        //中缀表达式: 1+((2+3)*4)-5
        //后缀表达式: 1 2 3 + 4 * + 5 -

        String expression = "1+((2+3)*4)-5";

        List<String> InfixList = toInfixExpressionList(expression);
        System.out.println("中缀表达式: " + InfixList); //[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]

        List<String> SuffixExpression = parseSuffixExpression(InfixList);
        System.out.println("后缀表达式: " + SuffixExpression);//[1, 2, 3, +, 4, *, +, 5, -]


    }

    //将中缀表达式 List 转成 后缀表达式 List
    public static List<String> parseSuffixExpression(List<String> infixList){

        //定义运算符栈s1
        //定义最终结果栈s2——>可以用ArrayList替代 因为其在操作中没有出栈操作 最后还有逆序输出 用栈不方便
        //而List集合是有序添加直接输出来就是逆波兰表达式(后缀表达式)
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        //遍历 infixList
        for (String s : infixList) {
            //如果是数字 加入到s2
            if (s.matches("\\d+")){
                s2.add(s);
            } else if (s.equals("(")){
                s1.push(s);
            } else if (s.equals(")")){
                //如果是右括号则s1 字符出栈到左括号为止 出栈的字符都进入s2 左右括号消失
                if (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                //去掉左括号
                s1.pop();
            }else {
                //到了运算符优先级的问题了
                // 1. 当s的优先级小于等于s1栈顶 则栈顶出栈加入到s2中 循环判断
                while (s1.size() != 0 && Operation.getValue(s) <= Operation.getValue(s1.peek())){
                    s2.add(s1.pop());
                }

                //还需将 s入栈
                s1.push(s);
            }
        }

        //遍历完中缀表达式后 将s1中符号添加到s2中
        while (s1.size() != 0){
            s2.add(s1.pop());
        }

        return s2;
    }

    //将中缀表达式转成 List 这样做是为了更好的去遍历
    public static List<String> toInfixExpressionList(String Infix){

        //存放 中缀表达式
        List<String> ls = new ArrayList<>();
        //指针 遍历中缀表达式
        int i = 0;
        //拼接多位数
        String str ;
        //存放遍历的每一个字符
        char c ;

        //开始遍历
        do {
            //ASCII码表 48——>0   57——>9
            //c如果是符号直接进入集合 如果是数字则要考虑是不是多位数
            if ( (c = Infix.charAt(i)) < 48 || (c = Infix.charAt(i)) > 57 ){
                ls.add(c + "");
                i++;
            }else {
                //str 置空
                str = "";
                if ( i < Infix.length() && (c = Infix.charAt(i)) >= 48 && (c = Infix.charAt(i)) <= 57){
                    //拼接多位数
                    str += c;
                    i++;
                }
                ls.add(str);
            }

        }while (i < Infix.length());

        return ls;
    }

}

//编写一个 Operation类 返回对应运算符的优先级
class Operation{

    //加
    private static int ADD = 1;
    //减
    private static int SUB = 1;
    //乘
    private static int MUL = 2;
    //除
    private static int DIV = 2;

    //返回优先级的数字
    public static int getValue(String operation){

        //结果
        int result = 0;
        //判断
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;

            default:
                break;
        }

        return result;
    }

}
