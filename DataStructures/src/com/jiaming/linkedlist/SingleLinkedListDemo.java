package com.jiaming.linkedlist;


/**
 * @author jmstart
 * @create 2020-01-14 19:16
 * 用水浒传人物 模拟单链表
 */
public class SingleLinkedListDemo {

    //测试链表
    public static void main(String[] args) {

        //创建子节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        //创建一个链表
        SingleLinkedList linkedList = new SingleLinkedList();

        //添加 无序
      /*  linkedList.add(heroNode1);
        linkedList.add(heroNode2);
        linkedList.add(heroNode3);
        linkedList.add(heroNode4);*/

        //添加 有序
        linkedList.addByOrder(heroNode1);
        linkedList.addByOrder(heroNode4);
        linkedList.addByOrder(heroNode2);
        linkedList.addByOrder(heroNode3);
//        linkedList.addByOrder(heroNode3);

        //显示
        linkedList.list();

        //测试修改链表的方法
        HeroNode newHeroNode = new HeroNode(3, "小吴", "阿智...");
        linkedList.update(newHeroNode);
        System.out.println("修改后的链表情况...");

        //测试删除节点
        linkedList.del(1);
//        linkedList.del(4);
//        linkedList.del(3);
//        linkedList.del(2);


        //显示
        linkedList.list();

        //测试获取单链表节点的个数
        System.out.println("有效的节点个数: " + getLength(linkedList.getHead()));
    }

    //面试题1. 获取单链表节点的个数 (如果有带头节点,则不计算带头结点)
    public static int getLength(HeroNode head){
        //判空
        if (head.next == null){
            return 0;
        }

        int length = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        //遍历
        while (cur != null){
            length++;
            //向下遍历
            cur = cur.next;
        }

        return length;
    }

}

//创建SingleLinkedList 来管理我们的人物
class SingleLinkedList {
    //先初始化一个头节点 不存放具体的数据 而且头节点不能动 防止以后找不到这个链表
    private HeroNode head = new HeroNode(0, "", "");

    //获取 head头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到链表
    //思路: 当不考虑编号顺序时
    //1. 找到当前链表的最后一个节点
    //2. 将最后节点的next 指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动 所以需要一个辅助变量 temp
        HeroNode temp = head; //temp 指向 head
        //遍历链表 找到最后
        while (true) {
            //找到了最后一个节点
            if (temp.next == null) {
                break;
            }
            //如果没有找到,就将 temp后移
            temp = temp.next;
        }
        //当退出这个while循环时 temp就到了链表最后
        //2. 将最后节点的next 指向新的节点
        temp.next = heroNode;
    }

    //第二种添加方法,根据排名来指定英雄插入的位置
    public void addByOrder(HeroNode heroNode){
        //因为head节点不能动 所以需要一个辅助变量 temp
        //而且我们要找 temp还的是添加节点的前一个 要不然添加不进去
        HeroNode temp = head;
        //flag标志添加的英雄是否存在,默认false 不存在
        boolean flag = false;
        while (true){
            //temp到达链表最后了
            if (temp.next == null){
                break;
            }
            //插入位置找到了      heroNode是插入的英雄
            if (temp.next.no > heroNode.no){
                break;
                        //temp 节点已存在
            } else if (temp.next.no == heroNode.no){
                //说明编号存在
                flag = true;
                break;
            }
            //向下遍历
            temp = temp.next;
        }
        //判断 flag  true代表存在不添加 反之添加
        if (flag){
            System.out.printf("英雄编号 %d 已存在,不能添加了\n",heroNode.no);
        }else {
            //可以加入了
            //让新的节点的next 指向原来temp的next
            heroNode.next = temp.next;
            //然后在把temp的next 指向新的节点
            temp.next = heroNode;
        }

    }

    //修改节点英雄人物的信息 根据 newHeroNode的 no 来修改
    public void update(HeroNode newHeroNode){
        //判空
        if (head.next == null){
            System.out.println("链表为空...");
            return;
        }
        //定义一个辅助节点
        HeroNode temp = head.next;
        //表示是否找到
        boolean flag = false;
        while (true){
            //遍历完毕
            if (temp == null){
                break;
            }
            //找到节点
            if (temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            //向下继续找
            temp = temp.next;
        }
        //判断是否需要修改
        if (flag){
            //找到了
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            //没有找到
            System.out.printf("没有找到编号 %d 的人物,不能修改...\n", newHeroNode.no);
        }

    }

    //删除节点
    //思路:
    //head不能动 所以需要一个辅助节点temp
    //要删除节点 需要找到它的前一个节点temp 防止坐标丢失
    public void del(int no){
        //辅助节点
        HeroNode temp = head;
        //标志是否找到
        boolean flag = false;
        while (true){
            //到达链表尾部
            if (temp.next == null){
                break;
            }
            //找到 待删除节点的前一个节点temp
            if (temp.next.no == no){
                flag = true;
                break;
            }
            //继续查找
            temp = temp.next;
        }
        //判断 flag
        if (flag){
            //找到temp 待删除节点的前一个节点
            //这样要删除的节点 就不会有引用指向它了 会被Java垃圾回收机制回收
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的节点 %d 不存在\n", no);
        }

    }

    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //因为head节点不能动 所以需要一个辅助变量 来遍历链表
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后了
            if (temp == null) {
                break;
            }
            //不为空 输出节点的信息
            System.out.println(temp);
            //将next后移 不后移 会是死循环
            temp = temp.next;
        }
    }

}

//创建一个HeroNode类
class HeroNode {
    public int no; //编号
    public String name; //姓名
    public String nickname; //花名
    public HeroNode next; //指向下一个节点

    //创建构造函数
    public HeroNode(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    //为了显示方便 重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
