package com.jiaming.singlecirclelinkedlist;

/**
 * @author jmstart
 * @create 2020-02-01 17:18
 */
public class Josephu {

    public static void main(String[] args) {

        //测试
        SingleCircleLinkedList linkedList = new SingleCircleLinkedList();

        //添加节点
        linkedList.addBoy(5);

        //遍历
        linkedList.list();

        //测试出圈
        linkedList.countBoy(1,2,5); //2 4 1 5 3

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
                first.next = first;
                //辅助节点指向第一个节点向下走,first不能动
                curBoy = first;
            } else {

                //添加节点
                curBoy.next = boy;
                //连成环
                boy.next = first;
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
            System.out.printf("编号是:%d \n", curBoy.no);

            //遍历完毕
            if (curBoy.next == first) {
                break;
            }

            //继续遍历
            curBoy = curBoy.next;
        }

    }

    //计算出圈的顺序

    /**
     * @param startNo  表示从第几个开始数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少在圈
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums || nums < 1) {
            System.out.println("参数输入有误,请重新输入...");
            return;
        }

        //日常辅助节点
        Boy helper = first;

        //1.给helper节点找最后一个节点
        while (true) {

            //helper指向了最后节点,找到了
            if (helper.next == first) {
                break;
            }
            //如果没有到最后一个节点,继续向下找
            helper = helper.next;
        }

        //2.报数前,先让first和 helper,移动 startNo-1次,到达要开始数的位置
        for (int i = 0; i < startNo -1; i++) {
            first = first.next;
            helper = helper.next;
        }

        //3.开始报数,first和 helper都移动 countNum-1次, 到达要出圈的位置
        //4.然后出圈
        while (true){

            //判断圈里是否剩下最后一人
            if (helper == first){
                break;
            }

            //开始数,数到出圈的节点
            for (int i = 0; i < countNum -1; i++) {
                first = first.next;
                helper = helper.next;
            }

            //出圈
            System.out.printf("%d 号出圈\n",first.no);

            //删除出圈节点
            first = first.next;
            helper.next = first;

        }

        //最后一个节点
        System.out.printf("%d 号最后一个节点\n", helper.no);

    }

}


//创建一个Boy类,表示一个节点
class Boy {
    //这是公有属性,可以这样调用.next 如是私有属性,就要用set和get方法
    //编号
    public int no;
    //指向下一个节点,默认为null
    public Boy next;

    //构造方法
    public Boy(int no) {
        this.no = no;
    }

}
