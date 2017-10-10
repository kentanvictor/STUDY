package com.dell.example.linearlist;

import java.util.Scanner;

/*
 * Created by JohnnyTan on 2017/10/10.
 */
public class StackList {
    public static void main(String[] args) {
        StackType st = new StackType();
        Information data = new Information();
        //初始化
        StackType stack = st.STInit();
        Scanner input = new Scanner(System.in);
        System.out.println("入栈操作:\n");
        System.out.println("输入姓名 年龄进行入栈操作:");
    }
}
class Information
{
    String name;
    int age;
}

class StackType
{
    static final int MAX = 50;
    Information[] data = new Information[MAX + 1];
    int top;

    //申请栈内存,并初始化
    StackType STInit()
    {
        StackType p;
        if ((p = new StackType()) != null)
        {
            p.top = 0;
            return p;
        }
        return null;
    }

    //判断栈是否为空
    boolean STIsEmpty(StackType s)
    {
        boolean t;
        t = (s.top == 0);
        return t;
    }

    //判断栈是否已满
    boolean STIsNUll(StackType s)
    {
        boolean t;
        t =(s.top == MAX);
        return t;
    }

    //清空栈
    void STClear(StackType s)
    {
        s.top = 0;
    }

    //释放栈所占用的空间
    void STFree(StackType s)
    {
        if(s != null)
        {
            s = null;
        }
    }

    //入栈操作
    int PushST(StackType s,Information data)
    {
        if((s.top+1) > MAX)
        {
            System.out.println("栈溢出!\n");
            return 0;
        }
        s.data[++s.top] = data;
        return 1;
    }

    //出栈操作
    Information PopST(StackType s)
    {
        if(s.top == 0)
        {
            System.out.println("栈为空!\n");
            System.exit(0);
        }
        return (s.data[s.top]);
    }
}

