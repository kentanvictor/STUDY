package com.dell.example.linearlist;

/*
 * Created by JohnnyTan on 2017/10/2.
 */
public class Sequence {
    static final int MAX = 100;

    class Data {
        String key;
        String name;
        int age;
    }

    class SLType {
        Data[] ListData = new Data[MAX + 1];
        int ListLen;
    }

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
}
