#include<stdio.h>
#include<malloc.h>
#include<stdlib.h>
#include<sq.h>
void init(SqStack *st)
{
    st->top = 0;
    printf("˳��ջ��ʼ�����!\n");
}
/*�ж�˳��ջ�Ƿ�Ϊ��(����0��ʾ˳��ջΪ��,����1��ʾ˳��ջ��Ϊ��)*/
int empty(SqStack st)
{
    return(st.top? 0:1);
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
/*����ƥ��*/
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
    printf("��������Ҫ��˳��ջ�в����Ԫ��(����������-1����):\n");
    scanf("%d",&temp);
    while(temp!=-1)
    {
        push(&st1,temp);
        i++;
        scanf("%d",&temp);
    }
    printf("��һ��������%d����.\n",i);
    printf("���������ű��ʽ,��'#'��ʾ��������:\n");
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
		printf("��������ű��ʽƥ��!\n");
	}
	else
	{
		printf("��������ű��ʽ��ƥ��!\n");
	}
    return 0;
}
