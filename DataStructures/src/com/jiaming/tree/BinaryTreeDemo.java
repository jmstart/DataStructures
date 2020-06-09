package com.jiaming.tree;

/**
 * @author jmstart
 * @create 2020-06-09 11:54
 *
 * 模拟二叉树:
 *      遍历二叉树
 *      使用递归完成下列遍历
 *      前序遍历(DLR)
 *      中序遍历(LDR)
 *      后序遍历(LRR)
 *      节点用水浒传英雄表示
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        //先创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "武松");
        HeroNode node3 = new HeroNode(3, "林冲");
        HeroNode node4 = new HeroNode(4, "李逵");
        HeroNode node5 = new HeroNode(5, "关胜");

        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root); //合并节点

        //测试
        System.out.println("前序遍历:"); //1,2,3,5,4
        binaryTree.perOrder();
        System.out.println("中序遍历:"); //2,1,5,3,4
        binaryTree.midOrder();
        System.out.println("后序遍历:"); //2,5,4,3,1
        binaryTree.postOrder();
    }
}

//创建二叉树 BinaryTree
class BinaryTree {
    private HeroNode root; //根节点

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //调用前序遍历
    public void perOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空,无法遍历！");
        }
    }

    //调用中序遍历
    public void midOrder() {
        if (this.root != null) {
            this.root.midOrder();
        } else {
            System.out.println("当前二叉树为空,无法遍历！");
        }
    }

    //调用后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空,无法遍历！");
        }
    }

}

//先创建 HeroNode结点
class HeroNode {

    private int no;
    private String name;
    private HeroNode left; //左节点,默认null
    private HeroNode right; //右节点,默认null

    //前序遍历(DLR)
    public void preOrder() {
        //输出父节点
        System.out.println(this);
        //递归左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历(LDR)
    public void midOrder() {
        //递归左子树
        if (this.left != null) {
            this.left.midOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归右子树
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    //后序遍历(LRD)
    public void postOrder() {
        //递归左子树
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归右子树
        if (this.right != null) {
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

}