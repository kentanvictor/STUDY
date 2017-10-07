package com.dell.example.linked;

import java.util.Scanner;

/*
 * Created by JohnnyTan on 2017/10/4.
 */
public class Linked {
    public static void main(String[] args) {
        CLType node, head = null;
        CLType CL = new CLType();
        String key, findkey;
        Scanner input = new Scanner(System.in);
        System.out.println("链表测试.先输入链表中的数据,格式为:关键字 姓名 年龄\n");
        do {
            Data nodeData = new Data();
            nodeData.key = input.next();
            if (nodeData.key.equals("0")) {
                break;//若输入0,则退出
            } else {
                nodeData.name = input.next();
                nodeData.age = input.nextInt();
                head = CL.CLAddEnd(head, nodeData);//在链表尾部添加结点
            }
        } while (true);
        CL.CLALLNode(head);
        System.out.println("\n演示插入结点,输入插入位置的关键字:");
        findkey = input.next();//输入插入位置的关键字
        System.out.println("输入插入结点的数据(关键字 姓名 年龄):");
        Data nodeData = new Data();
        nodeData.key = input.next();
        nodeData.name = input.next();
        nodeData.age = input.nextInt();//输入插入结点的数据
        head = CL.CLInsertNode(head, findkey, nodeData);//调用插入方法
        CL.CLALLNode(head);//显示所有结点
        System.out.println("\n演示删除结点,输入要删除的关键字:");
        key = input.next(); //输入需要删除的结点关键字
        CL.CLDeleNode(head,key);//调用删除方法
        CL.CLALLNode(head);//显示所有结点
        System.out.println("\n演示在链表中查找,输入查找关键字:");
        key = input.next();
        node = CL.CLFindNode(head,key);
        if(node != null)
        {
            nodeData = node.nodeData;//获取结点数据
            System.out.printf("关键字%s对应的结点为(%s.%s,%d)\n",key,nodeData.key,nodeData.name,nodeData.age);
        }
        else
        {
            System.out.printf("在链表中未找到关键字为%s的结点!\n",key);
        }
    }
}

class Data//结点的关键字
{
    String key;
    String name;
    int age;
}

class CLType//定义链表结构
{
    Data nodeData = new Data();
    CLType nextNode;

    //追加结点
    CLType CLAddEnd(CLType head, Data nodeData) {
        CLType node, htemp;
        if ((node = new CLType()) == null) {
            System.out.println("申请内存失败!\n");
            return null;
        } else {
            node.nodeData = nodeData;//保存数据
            node.nextNode = null;//设置结点引用为空,即为表尾
            if (head == null) {
                head = node;
                return head;
            }
            htemp = head;
            while (htemp.nextNode != null) {
                htemp = htemp.nextNode;
            }
            htemp.nextNode = node;
            return head;
        }
    }

    //头部增加结点
    CLType CLAddFirst(CLType head, Data nodeData) {
        CLType node;
        if ((node = new CLType()) == null) {
            System.out.println("申请内存失败!\n");//分配内存失败
            return null;
        } else {
            node.nodeData = nodeData;
            node.nextNode = head;
            head = node;
            return head;
        }
    }

    //查找结点
    CLType CLFindNode(CLType head, String key) {
        CLType htemp;
        htemp = head;
        while (htemp != null) {
            if (htemp.nodeData.key.compareTo(key) == 0) {
                return htemp;
            }
            htemp = htemp.nextNode;
        }
        return null;
    }

    //插入结点
    CLType CLInsertNode(CLType head, String findkey, Data nodeData) {
        CLType node, nodeTemp;
        if ((node = new CLType()) == null) {
            System.out.println("申请内存失败!\n");
            return null;
        }
        node.nodeData = nodeData;
        nodeTemp = CLFindNode(head, findkey);
        if (nodeTemp != null) {
            node.nextNode = nodeTemp.nextNode;//新插入结点指向关键结点的下一结点
            nodeTemp.nextNode = node;//设置关键结点指向新的结点
        } else {
            System.out.println("未找到正确的插入位置!\n");
//            free(node);      //释放内存
        }
        return head;
    }

    //删除某结点
    int CLDeleNode(CLType head, String key) {
        CLType node, htemp;         //node保存删除结点的前一个结点
        htemp = head;
        node = head;
        while (htemp != null) {
            if (htemp.nodeData.key.compareTo(key) == 0)//找到关键字,执行删除操作
            {
                node.nextNode = htemp.nextNode; //使前一个结点指向当前结点的下一个结点
                return 1;
            } else {
                node = htemp;   //指向当前结点
                htemp = htemp.nextNode;  //指向下一个结点
            }
        }
        return 0;
    }

    //计算链表的长度
    int CLength(CLType head) {
        CLType htemp;
        int Len = 0;
        htemp = head;
        while (htemp != null) {
            Len++;
            htemp = htemp.nextNode;
        }
        return Len;
    }

    //遍历链表
    void CLALLNode(CLType head) {
        CLType htemp;
        Data nodeData;
        htemp = head;
        System.out.println("当前链表共有" + CLength(head) + "个结点.链表所有数据如下:\n");
        while (htemp != null) {
            nodeData = htemp.nodeData;
            System.out.printf("结点(%s,%s,%d)\n", nodeData.key, nodeData.name, nodeData.age);
            htemp = htemp.nextNode;
        }
    }
}