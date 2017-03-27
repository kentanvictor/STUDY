#include<stdio.h>
#include<malloc.h>
#include<stdlib.h>
typedef struct LNode
{
	int data;
	struct LNode *next;
}LNode;
	/*上面只是定义了一个结构体类型，并未实际分配内存空间
	只有定义了变量才分配内存空间*/
	/*“typedef”指的是自定义结构体；在上面自定义结构体，然后下面的可以直接调用结构体函数*/
LNode *creat(int n)
{
	int i;
	struct LNode *head,*p1,*p2;
	/*head用来标记链表，p1总是用来指向新分配的内存空间，
	p2总是指向尾结点，并通过p2来链入新分配的结点*/
	int a;
	head=NULL;
	for(i=1;i<=n;i++)
	{
		p1=(struct LNode *)malloc(sizeof(struct LNode));
		/*动态分配内存空间，并数据转换为(struct LNode)类型*/
		printf("请输入链表中的第%d个数：",i);
		scanf("%d",&a);
		p1->data=a;
		if(head==NULL)/*指定链表的头指针*/
	{
		head=p1;
		p2=p1;
	}
	else
	{
		p2->next=p1;
		p2=p1;
	}
	p2->next=NULL;/*尾结点的后继指针为NULL(空)*/
}
	return head;/*返回链表的头指针*/
}
int main()
{
	int n;
	struct LNode *q;
	printf("请输入链表的长度：/n");
	scanf("%d",&n);
	q=creat(n);/*链表的头指针(head)来标记整个链表*/
	printf("/n链表中的数据：/n");
	while(q)/*直到结点q为NULL结束循环*/
	{
		printf("%d ",q->data);/*输出结点中的值*/
		q=q->next;/*指向下一个结点*/
	}
}
