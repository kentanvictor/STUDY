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

    SQType Init()
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
}
