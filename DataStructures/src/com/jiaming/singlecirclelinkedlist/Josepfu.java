package com.jiaming.singlecirclelinkedlist;

/**
 * @author jmstart
 * @create 2020-02-01 17:18
 */
public class Josepfu {

    public static void main(String[] args) {

        //测试
        SingleCircleLinkedList linkedList = new SingleCircleLinkedList();

        //添加节点
        linkedList.addBoy(5);

        //遍历
        linkedList.list();

    }
}

//创建单向环形链表
class SingleCircleLinkedList {
    //创建一个first节点,当前没有编号
    private Boy first = null;

    //添加节点,构建环形链表
    //nums:添加的人数
    public void addBoy(int nums) {
        //验证nums是否合法
        if (nums < 1) {
            System.out.println("nums值不正确...");
            return;
        }

        //辅助指针,帮助构建链表
        Boy curBoy = null;

        //创建环形链表
        for (int i = 1; i <= nums; i++) {

            //根据编号,创建节点
            Boy boy = new Boy(i);

            //如果是第一个节点,构建环形
            if (i == 1) {
                first = boy;
                //构成环
                first.setNext(first);
                //辅助节点指向第一个节点向下走,first不能动
                curBoy = first;
            } else {

                //添加节点
                curBoy.setNext(boy);
                //连成环
                boy.setNext(first);
                //辅助指针后移
                curBoy = boy;
            }

        }

    }

    //遍历链表
    public void list() {
        //判空
        if (first == null) {
            System.out.println("链表为空...");
            return;
        }

        //辅助指针上
        Boy curBoy = first;

        while (true) {
            System.out.printf("编号是:%d \n", curBoy.getNo());

            //遍历完毕
            if (curBoy.getNext() == first) {
                break;
            }

            //继续遍历
            curBoy = curBoy.getNext();
        }

    }

}


//创建一个Boy类,表示一个节点
class Boy {
    //编号
    private int no;
    //指向下一个节点,默认为null
    private Boy next;

    //构造方法
    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
