package com.dell.example.linked;

/*
 * Created by JohnnyTan on 2017/10/4.
 */
public class Linked {
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
    CLType CLAddEnd(CLType head,Data nodeData)
    {
        CLType node,htemp;
        if((node = new CLType()) == null)
        {
            System.out.println("申请内存失败!\n");
            return null;
        }
        else
        {
            node.nodeData = nodeData;//保存数据
            node.nextNode = null;//设置结点引用为空,即为表尾
            if(head == null)
            {
                head = node;
                return head;
            }
            htemp = head;
            while (htemp.nextNode!=null)
            {
                htemp = htemp.nextNode;
            }
            htemp.nextNode = node;
            return head;
        }
    }
    //头部增加结点
    CLType CLAddFirst(CLType head,Data nodeData)
    {
        CLType node;
        if((node = new CLType()) == null)
        {
            System.out.println("申请内存失败!\n");//分配内存失败
            return null;
        }
        else
        {
            node.nodeData = nodeData;
            node.nextNode = head;
            head = node;
            return head;
        }
    }
    //查找结点
    CLType CLFindNode(CLType head,String key)
    {
        CLType htemp;
        htemp = head;
        while (htemp != null)
        {
            if(htemp.nodeData.key.compareTo(key) == 0)
            {
                return htemp;
            }
            htemp = htemp.nextNode;
        }
        return null;
    }
    //插入结点
    CLType CLInsertNode(CLType head,String findkey,Data nodeData)
    {
        CLType node,nodeTemp;
        if((node = new CLType()) == null)
        {
            System.out.println("申请内存失败!\n");
            return null;
        }
        node.nodeData = nodeData;
        nodeTemp = CLFindNode(head,findkey);
        if(nodeTemp != null)
        {
            node.nextNode = nodeTemp.nextNode;//新插入结点指向关键结点的下一结点
            nodeTemp.nextNode = node;//设置关键结点指向新的结点
        }
        else
        {
            System.out.println("未找到正确的插入位置!\n");
//            free(node);      //释放内存
        }
        return head;
    }
    //删除某结点
    int CLDeleNode(CLType head,String key)
    {
        CLType node,htemp;         //node保存删除结点的前一个结点
        htemp = head;
        node = head;
        while (htemp != null)
        {
            if(htemp.nodeData.key.compareTo(key) == 0)//找到关键字,执行删除操作
            {
                node.nextNode = htemp.nextNode; //使前一个结点指向当前结点的下一个结点
                return 1;
            }
            else
            {
                node = htemp;   //指向当前结点
                htemp = htemp.nextNode;  //指向下一个结点
            }
        }
        return 0;
    }

    //计算链表的长度
    int CLength(CLType head)
    {
        CLType htemp;
        int Len = 0;
        htemp = head;
        while (htemp != null)
        {
            Len++;
            htemp = htemp.nextNode;
        }
        return Len;
    }
}