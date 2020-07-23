package com.dell.example.treeDemo;

import java.util.Scanner;

/*
 * Created by JohnnyTan on 2017/11/2.
 */
public class TreeDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CBTType root = null;        //root为指向二叉树根结点的引用
        int menusel;
        TreeOperation tree = new TreeOperation();
        //设置根元素
        root = tree.InitTree();
        //添加结点
        do {
            System.out.printf("请选择菜单添加二叉树的结点\n");
            System.out.printf("0.退出\t");            //显示菜单
            System.out.printf("1.添加二叉树的结点\n");
            menusel = input.nextInt();
            switch (menusel)
            {
                case 1:
                    tree.AddTreeNode(root);
                    break;
                case 0:
                    break;
                default:
                    ;
            }
        }
        while (menusel != 0);

        //遍历
        do {
            System.out.printf("请选择菜单遍历二叉树，输入0表示推出：\n");
            System.out.printf("1.先序遍历DLR\t");
            System.out.printf("2.中序遍历LDR\t");
            System.out.printf("3.后序遍历LRD\t");
            System.out.printf("4.按层遍历\n");
            menusel = input.nextInt();
            switch (menusel)
            {
                case 0:
                    break;
                case 1:                                         //先序遍历
                    System.out.printf("\n先序遍历DLR的结果：");
                    tree.DLRTree(root);
                    System.out.printf("\n");
                    break;
                case 2:                                         //中序遍历
                    System.out.printf("\n中序遍历的结果：");
                    tree.LDRTree(root);
                    System.out.printf("\n");
                    break;
                case 3:                                         //后序遍历
                    System.out.printf("\n后序遍历LRD的结果：");
                tree.LRDTree(root);
                    System.out.printf("\n");
                    break;
                case 4:
                    System.out.printf("\n按层遍历的结果：");
                    tree.LevelTree(root);
                    System.out.printf("\n");
                    break;
                default:
                    break;
            }
        }while (menusel != 0);
        //深度
        System.out.printf("\n二叉树深度为：%d\n",tree.TreeDepth(root));
        tree.ClearTree(root);
        root = null;
    }
}

class CBTType   //二叉树结点类型
{
    String data;        //元素数据
    CBTType left;       //左子树结点引用
    CBTType right;      //右子树结点引用
}

class TreeOperation {
    static final int MAX = 20;
    static Scanner input = new Scanner(System.in);

    CBTType InitTree() {
        CBTType node;
        if ((node = new CBTType()) != null) {
            System.out.printf("请先输入一个根结点数据：\n");
            node.data = input.next();
            node.left = null;
            node.right = null;
            if (node != null)        //如果二叉树的根结点不为空
            {
                return node;
            } else {
                return null;
            }
        }
        return null;
    }

    void AddTreeNode(CBTType treenode) {
        CBTType pnode, parent;
        String data;
        int menusel;

        if ((pnode = new CBTType()) != null)     //分配内存
        {
            System.out.printf("输入二叉树结点数据：\n");
            pnode.data = input.next();
            pnode.left = null;      //设置左右子树为空
            pnode.right = null;

            System.out.printf("输入该结点的父结点数据：");
            data = input.next();

            parent = TreeFindNode(treenode, data);                  //查找指定数据的结点
            if (parent == null) {
                System.out.printf("未找到该父结点！\n");
                pnode = null;
                return;
            }
            System.out.printf("1.添加该结点到左子树\n2.添加该结点到右子树\n");
            do {
                menusel = input.nextInt();                            //输入选择项
                if (menusel == 1 || menusel == 2) {
                    if (parent == null) {
                        System.out.printf("不存在父结点，请先设置父结点！\n");
                    } else {
                        switch (menusel) {
                            case 1:                                   //  添加到左结点
                                if (parent.left != null)             //  左子树不为空
                                {
                                    System.out.printf("左子树结点不为空！\n");
                                } else {
                                    parent.left = pnode;
                                }
                                break;
                            case 2:                                     //添加到右结点
                                if (parent.right != null)              //右子树不为空
                                {
                                    System.out.printf("左子树结点不为空！\n");
                                } else {
                                    parent.right = pnode;
                                }
                                break;
                            default:
                                System.out.printf("无效参数！\n");
                        }
                    }
                }
            }
            while (menusel != 1 && menusel != 2);
        }
    }

    CBTType TreeFindNode(CBTType treenode, String data) {       //查找结点
        CBTType ptr;
        if (treenode == null) {
            return null;
        } else {
            if (treenode.data.equals(data)) {
                return treenode;
            } else {
                if ((ptr = TreeFindNode(treenode.left, data)) != null) {
                    return ptr;
                } else if ((ptr = TreeFindNode(treenode.right, data)) != null) {
                    return ptr;
                } else {
                    return null;
                }
            }
        }
    }

    CBTType TreeLeftNode(CBTType treeNode)              // 获取左子树
    {
        if (treeNode != null) {
            return treeNode.left;                           //返回值
        } else {
            return null;
        }
    }

    CBTType TreeRightNode(CBTType treeNode)                 //  获取右子树
    {
        if (treeNode != null) {
            return treeNode.right;                  //返回值
        } else {
            return null;
        }
    }

    int TreeIsEmpty(CBTType treeNode)           //判断空树
    {
        if (treeNode != null) {
            return 0;
        } else {
            return 1;
        }
    }

    int TreeDepth(CBTType treeNode)
    {
        int depleft,depright;
        if(treeNode == null)
        {
            return 0;                       // 对于空树，深度为0
        }
        else
        {
            depleft = TreeDepth(treeNode.left);       //左子树深度（递归调用）
            depright = TreeDepth(treeNode.right);      //右子树深度（递归调用）
            if(depleft > depright)
            {
                return depleft + 1;
            }
            else
            {
                return depright + 1;
            }
        }
    }

    void ClearTree(CBTType treeNode)            //清空二叉树
    {
        if (treeNode != null) {
            ClearTree(treeNode.left);                   // 清空左子树
            ClearTree(treeNode.right);                  //清空右子树
            treeNode = null;                            // 释放当前结点所占据的内存
        }
    }

    void TreeNodeData(CBTType p)            //显示结点数据
    {
        System.out.printf("%s", p.data);             //输出结点数据
    }

    void LevelTree(CBTType treeNode)        //按层遍历
    {
        CBTType p;
        CBTType[] q = new CBTType[MAX];             //定义一个顺序栈
        int head = 0, tail = 0;
        if (treeNode != null) {
            tail = (tail + 1) % MAX;                //计算循环队列队尾序号
            q[tail] = treeNode;                     //将二叉树根引用进队
        }
        while (head != tail) {
            head = (head + 1) % MAX;
            p = q[head];
            TreeNodeData(p);                        //处理队首元素
            if (p.right != null) {
                tail = (tail + 1) % MAX;            //计算循环队列的队尾序号
                q[tail] = p.right;                  //将右子树引用进队
            }
        }
    }

    void DLRTree(CBTType treeNode)          //先序遍历
    {
        if (treeNode != null) {
            TreeNodeData(treeNode);                 //显示结点数据
            DLRTree(treeNode.left);
            DLRTree(treeNode.right);
        }
    }

    void LDRTree(CBTType treeNode) {
        if (treeNode != null) {
            LDRTree(treeNode);                      //中序遍历左子树
            TreeNodeData(treeNode);                 //显示结点数据
            LDRTree(treeNode.right);                // 中序遍历右子树
        }
    }

    void LRDTree(CBTType treeNode) {
        if (treeNode != null) {
            LRDTree(treeNode.left);                 //后序遍历左子树
            LRDTree(treeNode.right);                //后序遍历右子树
            TreeNodeData(treeNode);                 //显示结点数据
        }
    }

}
