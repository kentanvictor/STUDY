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
    int a;//存放输入的链表个数，用来判断到哪里结束 
    head=NULL;
    for(i=1;i<=n;i++)
    {
        p1=(struct LNote *)malloc(sizeof(struct LNote));
		//p1用来指向新分配出来的空间（用malloc开辟空间，然后强制转换成自定义结构体类型）
		pritnf("请输入第%d个数",i);
		scanf("%d",&a);
		p1->data=a;//指向新创立的数据元素，每个数据元素都包含一个数组 
		if(head=NULL)
		{
			head=p1;//如果没有新的头结点就直接将新创立的p1作为第一个头结点，进行链表的传递 
			p2=p1;//然后后面的数据进行连接 
		}
		else
		{
			p->next=p1;
		}
    }
}

