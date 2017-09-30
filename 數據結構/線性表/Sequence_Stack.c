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
/*顺序栈的初始化*/
void init(SqStack *st)
{
    st->top = 0;
    printf("顺序栈初始化完毕!\n");
}
/*判断顺序栈是否为空*/
int empty(SqStack st)
{
    return(st.top? 1:0);
}
/*取得栈顶结点的值*/
datatype read(SqStack st)
{
    if(empty(st))
    {
        printf("\n栈是空的!");
        exit(1);
    }
    else
    {
        return(st.top-1);
    }
}
/*顺序栈的插入操作*/
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
/*顺序栈的删除操作*/
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
    printf("请输入需要往顺序栈中插入的元素(并且以输入-1结束):\n");
    scanf("%d",&temp);
    while(temp!=-1)
    {
        push(&st1,temp);
        i++;
        scanf("%d",&temp);
    }
    printf("你一共输入了%d个数.",i);
    return 0;
}
