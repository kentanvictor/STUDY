#include<stdio.h>
#include<malloc.h>
#include<stdlib.h>
typedef struct LNote
{
    int data;
    struct LNote *next;
}LNote;//定义一个结构体类型，没有赋值，没给定任何值
//构造创造链表函数
LNote *creat(int n)
{
    int i;
    LNote *head,*p1,*p2;//创立头链表，p1指向新分配的内存空间，p2指向尾结点
    int a;//
    head=NULL;
    for(i=1;i<=n;i++)
    {
        p1=(struct LNote *)malloc(sizeof(struct LNote));//
    }
}

