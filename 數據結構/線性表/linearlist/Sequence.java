package com.dell.example.linearlist;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Created by JohnnyTan on 2017/10/2.
 */
public class Sequence {
    public static void main(String[] args) {
        int i;
        SLType SL = new SLType();//定义顺序表变量
        Data pdata;//定义结点保存引用变量
        String key;//保存关键字
        System.out.println("顺序表操作演示!\n");
        SL.Init(SL);//初始化顺序表
        System.out.println("初始化顺序表完成!\n");
        Scanner input = new Scanner(System.in);
        do //循环添加结点数据
        {
            System.out.println("输入添加的结点(学号 姓名 年龄):");
            Data data = new Data();
            data.key = input.next();
            data.name = input.next();
            data.age = input.nextInt();

            if (data.age != 0) {
                if (SL.SLAdd(SL, data) == 0) {
                    break;
                }
            } else {
                break;
            }
        }
        while (true);
        System.out.println("\n順序表中的结点顺序为:\n");
        SL.SLALL(SL);//显示所有结点数据

        System.out.println("\n要取出的结点的序号:");
        i = input.nextInt();
        pdata = SL.SLFindByNum(SL, i);
        if (pdata != null) {
            System.out.println("第" + i + "个结点为:" + pdata.key + "," + pdata.name + "," + pdata.age + "\n");
        }

        System.out.println("\n" + "要查找的关键字:");
        key = input.next();
        i = SL.SLFindByCont(SL,key);
        pdata = SL.SLFindByNum(SL,i);
        if(pdata != null)
        {
            System.out.println("第" + i+"个结点为:" + pdata.key+"," + pdata.name+"," +pdata.age+ ",");
        }
    }
}

class Data {
    String key;
    String name;
    int age;
}

class SLType {
    static final int MAX = 100;
    Data[] ListData = new Data[MAX + 1];
    int ListLen;

    void Init(SLType SL) {
        SL.ListLen = 0;
    }

    int SLLength(SLType SL) {
        return (SL.ListLen);
    }

    int SLInsert(SLType SL, int n, Data data) {
        int i;
        if (SL.ListLen >= MAX)//顺序表结点数量已经超过最大数量
        {
            System.out.println("顺序表已经满了,不能插入结点了!\n");
            return 0;
        }
        if (n < 1 || n > SL.ListLen - 1)//插入结点序号不正确
        {
            System.out.println("插入元素序号错误,不能插入元素!\n");
            return 0;
        }
        for (i = SL.ListLen; i >= n; i--)//将顺序表
        {
            SL.ListData[i + 1] = SL.ListData[i];
        }
        SL.ListData[n] = data;
        SL.ListLen++;
        return 1;
    }

    int SLAdd(SLType SL, Data data)//添加元素到顺序表的尾部
    {
        if (SL.ListLen >= MAX)//顺序表已满
        {
            System.out.println("顺序表已满,无法再添加结点!\n");
            return 0;
        }
        SL.ListData[++SL.ListLen] = data;
        return 1;
    }

    int SLDele(SLType SL, int n)//删除顺序表中的元素
    {
        int i;
        if (n < 1 || n > SL.ListLen + 1)//删除结点错误
        {
            System.out.println("删除结点序号错误,不能删除结点!\n");
            return 0;
        }
        for (i = n; i < SL.ListLen; i++)//将顺序表中的数据向前移动
        {
            SL.ListData[i] = SL.ListData[i + 1];
        }
        SL.ListLen--;
        return 1;
    }

    Data SLFindByNum(SLType SL, int n)//按序号返回数据元素
    {
        if (n < 1 || n > SL.ListLen + 1)//元素序号不正确
        {
            System.out.println("结点序号错误,不能返回结点!\n");
            return null;
        }
        return SL.ListData[n];
    }

    int SLFindByCont(SLType SL, String key)//按照关键字查询结点
    {
        int i;
        for (i = 1; i <= SL.ListLen; i++) {
            if (SL.ListData[i].key.compareTo(key) == 0) {
                return i;
            }
        }
        return 0;
    }

    int SLALL(SLType SL)//显示顺序表中的所有的结点
    {
        int i;
        for (i = 1; i <= SL.ListLen; i++) {
            System.out.println(SL.ListData[i].key + "," + SL.ListData[i].name + "," + SL.ListData[i].age);
        }
        return 0;
    }
}

