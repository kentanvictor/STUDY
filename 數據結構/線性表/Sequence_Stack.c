#include<stdio.h>
#include<malloc.h>
#include<stdlib.h>

#define MAX 100
typedef int datatype;
typedef struct
{
	datatype a[MAX];
	int top;
}SqStack;
/*˳��ջ�ĳ�ʼ��*/
void init(SqStack *st)
{
    st->top = 0;
    printf("˳��ջ��ʼ�����!\n");
}
/*�ж�˳��ջ�Ƿ�Ϊ��*/
int empty(SqStack st)
{
    return(st.top? 1:0);
}
/*ȡ��ջ������ֵ*/
datatype read(SqStack st)
{
    if(empty(st))
    {
        printf("\nջ�ǿյ�!");
        exit(1);
    }
    else
    {
        return(st.top-1);
    }
}
/*˳��ջ�Ĳ������*/
void push(SqStack *st,datatype x)
{
    if(st->top  == MAX)
    {
        printf("\nThe sequence stack is full!");
        exit(1);
    }
    st->a[st->top] = x;
    st->top++;
}
/*˳��ջ��ɾ������*/
void dele(SqStack *st)
{
    if(st->top == 0)
    {
        printf("\nThe Sequence stack is empty!");
        exit(1);
    }
    st->top--;
}
int main()
{
    int i = 0;
    datatype temp;
    SqStack st1;
    init(&st1);
    printf("��������Ҫ��˳��ջ�в����Ԫ��(����������-1����):\n");
    scanf("%d",&temp);
    while(temp!=-1)
    {
        push(&st1,temp);
        i++;
        scanf("%d",&temp);
    }
    printf("��һ��������%d����.",i);
    return 0;
}
