package com.jiaming.doublelinkedlist;

/**
 * @author jmstart
 * @create 2020-01-29 17:57
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试开始:");

        //创建子节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        //创建一个链表
        DoubleLinkedList linkedList = new DoubleLinkedList();

        //添加
        System.out.println("--------添加-----------");
        linkedList.add(heroNode1);
        linkedList.add(heroNode2);
        linkedList.add(heroNode3);
        linkedList.add(heroNode4);
        //遍历
        linkedList.list();


        //修改
        System.out.println("--------修改3-----------");
        HeroNode newHeroNode = new HeroNode(3, "小吴", "阿智...");
        linkedList.update(newHeroNode);
        //遍历
        linkedList.list();

        //删除
        System.out.println("--------删除4-----------");
        linkedList.del(4);
        //遍历
        linkedList.list();
    }
}

//创建一个双向链表类
class DoubleLinkedList {

    //先初始化一个头节点 不存放具体的数据 而且头节点不能动 防止以后找不到这个链表
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加方法
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
        //双向链表的添加
        temp.next = heroNode;
        heroNode.pre = temp;
    }


    //修改节点(和单链表一样)
    public void update(HeroNode newHeroNode) {
        //判空
        if (head.next == null) {
            System.out.println("链表为空...");
            return;
        }
        //定义一个辅助节点
        HeroNode temp = head.next;
        //表示是否找到
        boolean flag = false;
        while (true) {
            //遍历完毕
            if (temp == null) {
                break;
            }
            //找到节点
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            //向下继续找
            temp = temp.next;
        }
        //判断是否需要修改
        if (flag) {
            //找到了
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            //没有找到
            System.out.printf("没有找到编号 %d 的人物,不能修改...\n", newHeroNode.no);
        }

    }

    //删除节点
    //双向链表可以直接删除节点 不用在找待删除的前一个节点了
    public void del(int no) {

        //判空
        if (head.next == null) {
            System.out.println("链表为空,不能删除！");
            return;
        }

        //辅助节点
        HeroNode temp = head.next;

        //标志是否找到
        boolean flag = false;
        while (true) {
            //到达链表尾部
            if (temp == null) {
                break;
            }
            //找到待删除节点
            if (temp.no == no) {
                flag = true;
                break;
            }
            //继续查找
            temp = temp.next;
        }
        //判断 flag
        if (flag) {
            //让前面节点指向后面节点(中间节点以删除)
            temp.pre.next = temp.next;
            //将在后面节点指向前面节点(中间节点以删除)
            //判断是否是最后一个节点
            if (temp.next != null) {
                //此句代码有风险(如果是删除最后一个节点 这句话执行后会报空指针异常)
                //所以要保证删除最后一个节点时不执行这句代码,上面那就代码就能实现删除最后一个节点
                temp.next.pre = temp.pre;
            }

        } else {
            System.out.printf("要删除的节点 %d 不存在\n", no);
        }

    }

    //遍历双向链表(和单链表一样)
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

//创建一个HeroNode类(和单链表一样,就是加了一个前驱节点)
class HeroNode {
    public int no; //编号
    public String name; //姓名
    public String nickname; //花名
    public HeroNode next; //指向下一个节点(默认为null)
    public HeroNode pre; //指向前一个节点(默认为null)

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
