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
/*����(˳��洢)�ĳ�ʼ��*/
void init(SqQue *sq)
{
	sq->front = sq->rear = 0;
}
/*�ж϶����Ƿ�Ϊ��(0Ϊ��,1Ϊ�ǿ�)*/
void empty(SqQue sq)
{
	return (sq.front == sq.rear ? 0:1);
}
/*��ӡ����(˳��洢)�еĽ��ֵ*/
void display(SqQue sq)
{
	int i;
	if(empty(sq))
	{
		printf("\n�����ǿյ�!");
	}
	else
	{
		for(i=sq.front;i<sq.rear;i++)
		{
			printf("%5d\n",sq.ai[i]);
		}
	}
}
/*ȡ�ö��ж��׽���ֵ*/
datatype get(SqQue sq)
{
	if(empty(sq))
	{
		printf("\n˳����ǿյ�,�޷�ȡ�ö��׽���ֵ!"); 
		exit(1);
	}
	return (sq.a[sq.front]); 
}
/*���еĲ������*/
void insert(sqQue *sq,datatype x)
{
	int i;
	if(sq->rear == MAX)
	{
		printf("\n˳����������ģ�")��
		exit(1); 
	}
	sq->a[sq->rear]=x;
	sq->rear = sq->rear+1;
} 
/*����ɾ������*/
void dele(SqSue *sq)
{
	if(sq->front == sq->rear)
	{
		printf("\n˳����ǿյģ�������ɾ��������");
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
