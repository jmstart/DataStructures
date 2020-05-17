package com.jiaming.hashtab;

import java.util.Scanner;

/**
 * @author jmstart
 * @create 2020-03-21 12:30
 * 哈希表实现Google上机题:
 * 有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,住址..),
 * 当输入该员工的id时,要求查找到该员工的 所有信息.
 * 要求: 不使用数据库,尽量节省内存,速度越快越好 暗示使用=>哈希表(散列)
 */
public class GoogleHashTab {
    public static void main(String[] args) {
        //哈希表 = 链表 + 数组
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建员工
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//HashTab类
class HashTab {
    //放员工链表的数组,也就是哈希表
    private EmpLinkedList[] empLinkedListArrays;
    private int size; //表示有几条链表

    //定义一个构造器
    public HashTab(int size) {
        this.size = size;
        //代表这个哈希表里有几个链表
        empLinkedListArrays = new EmpLinkedList[size];
        //注意: 要把哈希表表里面的每一个链表都初始化
        for (int i = 0; i < size; i++) {
            empLinkedListArrays[i] = new EmpLinkedList();
        }
    }

    //添加员工
    public void add(Emp emp) {
        //根据散列函数,得到员工要去的链表编号
        int empLinkedListNO = hashFun(emp.id);

        //添加对应链表
        empLinkedListArrays[empLinkedListNO].addEmp(emp);
    }

    //编写散列函数,该函数方式有很多,我采用取模法
    public int hashFun(int id) {
        return id % size;
    }

    //遍历所有链表,也就是遍历哈希表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArrays[i].listEmp(i);
        }
    }

    //根据 id查找员工
    public void findById(int id) {
        //根据散列函数,得出去哪个链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArrays[empLinkedListNO].findEmpById(id);
        //判断是否找到员工
        if (emp != null) {
            System.out.printf("在第%d条链表中,找到了id为%d的员工\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中,没有找到该员工...");
        }
    }

}

//员工类
class Emp {
    //为了方便演示,就给员工二个属性,id 和 name
    public int id;
    public String name;
    //指向下一个员工引用
    public Emp next; //默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//链表类 存放员工信息
class EmpLinkedList {
    //头指针,指向第一个员工,head指向第一个员工
    private Emp head; //默认为空

    //添加员工
    public void addEmp(Emp emp) {
        //如果是添加第一个员工
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个,则需要一个辅助指针,帮我定位到链表最后
        Emp curEmp = head;
        while (true) {
            //已经找到链表最后
            if (curEmp.next == null) {
                break;
            }
            //向下查找
            curEmp = curEmp.next;
        }
        //添加员工
        curEmp.next = emp;
    }

    //遍历链表员工信息
    public void listEmp(int no) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("第 " + (no + 1) + " 条链表的信息为空...");
            return;
        }
        System.out.print("第 " + (no + 1) + " 条链表的信息为:");
        //日常辅助
        Emp curEmp = head;
        while (true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据 id查找员工
    public Emp findEmpById(int id) {
        //判空
        if (head == null) {
            return null;
        }
        //日常辅助
        Emp curEmp = head;
        while (true) {
            //找到员工
            if (curEmp.id == id) {
                break;
            }
            //退出
            if (curEmp.next == null) {
                curEmp = null; //置空
                break;
            }
            //没有找到继续后移
            curEmp = curEmp.next;
        }
        //返回员工
        return curEmp;
    }
}
