#include<stdio.h>
#include<malloc.h>
#include<stdlib.h>
#define MAX 100
typedef int datatype;
typedef struct
{
	datatype a[MAX];
	int front;
	int rear;
}SqQue;
/*队列(顺序存储)的初始化*/
void init(SqQue *sq)
{
	sq->front = sq->rear = 0;
}
/*判断队列是否为空(0为空,1为非空)*/
void empty(SqQue sq)
{
	return (sq.front == sq.rear ? 0:1);
}
/*打印队列(顺序存储)中的结点值*/
void display(SqQue sq)
{
	int i;
	if(empty(sq))
	{
		printf("\n队列是空的!");
	}
	else
	{
		for(i=sq.front;i<sq.rear;i++)
		{
			printf("%5d\n",sq.ai[i]);
		}
	}
}
/*取得队列队首结点的值*/
datatype get(SqQue sq)
{
	if(empty(sq))
	{
		printf("\n顺序表是空的,无法取得队首结点的值!"); 
		exit(1);
	}
	return (sq.a[sq.front]); 
}
/*队列的插入操作*/
void insert(sqQue *sq,datatype x)
{
	int i;
	if(sq->rear == MAX)
	{
		printf("\n顺序队列是满的！")；
		exit(1); 
	}
	sq->a[sq->rear]=x;
	sq->rear = sq->rear+1;
} 
/*队列删除操作*/
void dele(SqSue *sq)
{
	if(sq->front == sq->rear)
	{
		printf("\n顺序表是空的！不能做删除操作！");
		exit(1);
	}
	sq->front++;
}
int main()
{
	int i;
	SqSue sq1;
	return 0;
}
