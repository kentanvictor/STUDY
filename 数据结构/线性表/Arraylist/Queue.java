package com.dell.example.linearlist;

/*
 * Created by JohnnyTan on 2017/10/15.
 */
public class Queue {
}

class DATA
{
    String name;
    int age;
}

class SQType
{
    static final int QUEUELENN = 15;
    DATA[] data = new DATA[QUEUELENN];
    int head;
    int tail;

    SQType Init()   //初始化
    {
        SQType q;
        if((q = new SQType()) != null)
        {
            q.head = 0;
            q.tail = 0;
            return q;
        }
        else
        {
            return null;
        }
    }

    int SQTypeIsEmpty(SQType q)     //判断空队列
    {
        int temp = 0;
        if(q.head == q.tail)
        {
            temp = 1;
        }
        return temp;
    }

    int SQTypeIsFull(SQType q)      //判断满队列
    {
        int temp = 0;
        if(q.tail == QUEUELENN)
        {
            temp = 1;
        }
        return temp;
    }

    void SQTypeClear(SQType q)      //清空队列
    {
        q.head = 0;
        q.tail = 0;
    }

    void SQTypeFree(SQType q)       //入队列
    {
        if(q != null)
        {
            q = null;
        }
    }

    int InSQType(SQType q,DATA data)
    {
        if(q.tail == QUEUELENN)
        {
            System.out.println("队列已满！操作失败！\n");
            return 0;
        }
        else
        {
            q.data[q.tail++] = data;    //将元素入队列
            return 1;
        }
    }

    DATA OutSQType(SQType q)        //出队列
    {
        if(q.head == q.tail)
        {
            System.out.println("\n队列已空！操作失败！\n");

            System.exit(0);
        }
        else
        {
            return q.data[q.head++];
        }
        return null;
    }
}
