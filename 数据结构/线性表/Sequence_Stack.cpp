#include<stdio.h>
#include<malloc.h>
#include<stdlib.h>
#include<sq.h>
void init(SqStack *st)
{
    st->top = 0;
    printf("顺序栈初始化完毕!\n");
}
/*判断顺序栈是否为空(返回0表示顺序栈为空,返回1表示顺序栈不为空)*/
int empty(SqStack st)
{
    return(st.top? 0:1);
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
/*括号匹配*/
int match_kuohao(char c[])
{
	int i = 0;
	SqStack st2;
	init(&st2);
	while(c[i] != '#')
    {
        switch(c[i])
        {
        case '{':
        case '[':
        case '(':
            push(&st2,c[i]);
            break;
        case '}':
            if(!empty(st2)&&read(st2)=='{')
                {
                    dele(&st2);
                    break;
                }
            else
                {
                    return 0;
                }
        case ']':
            if(!empty(st2)&&read(st2) == '[')
            {
                dele(&st2);
                break;
            }
            else
            {
                return 0;
            }
        case ')':
            if(!empty(st2)&&read(st2) == ')')
            {
                dele(&st2);
                break;
            }
            else
            {
                return 0;
            }
        }
        i++;
    }
    return (empty(st2));
}
int main()
{
    int i = 0;
    char *c[MAX];
    datatype temp;
    SqStack st1,st2;
    init(&st1);
    printf("请输入需要往顺序栈中插入的元素(并且以输入-1结束):\n");
    scanf("%d",&temp);
    while(temp!=-1)
    {
        push(&st1,temp);
        i++;
        scanf("%d",&temp);
    }
    printf("你一共输入了%d个数.\n",i);
    printf("请输入括号表达式,以'#'表示结束输入:\n");
    i = 0;
    scanf("%c",&c[i]);
    while(*c[i] != '#')
    {
        i++;
        scanf("%c",&c[i]);
	}
	i = match_kuohao(c[i]);
	if(i == 0)
	{
		printf("输入的括号表达式匹配!\n");
	}
	else
	{
		printf("输入的括号表达式不匹配!\n");
	}
    return 0;
}
