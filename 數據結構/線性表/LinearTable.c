#include<stdio.h>
#include<malloc.h>
#include<stdlib.h>
#define MAX 100
typedef int datatype;
typedef struct
{
    datatype a[MAX];
    int size;
}sequence_list;
/*顺序表的初始化*/
void init(sequence_list *slt)
{
    slt->size = 0;
}
/*顺序表的插入操作*/
void append(sequence_list *slt,datatype x)
{
    if(slt->size == MAX)
    {
        printf("顺序表是满的!");
        exit(1);
    }
    slt->a[slt->size] = x;
    slt->size = slt->size+1;
}
/*打印顺序表各个结点的值*/
void display(sequence_list slt)
{
    int i;
    if(!slt.size)
    {
        printf("顺序表是空的!");
    }
    else
    {
        for(i = 0;i<slt.size;i++)
        {
            printf("%5d\t\n",slt.a[i]);
        }
    }
}
/*判断顺序表是否为空*/
int empty(sequence_list slt)
{
    return (slt.size == 0 ? 1:0);
}
/*查找順序表中值为x的结点的位置*/
int find(sequence_list slt,datatype x)
{
	int i = 0;
	while(i<slt.size&&slt.a[i] != x)
	{
		i++;
	}
	return(i<slt.size ? i:-1);
}
/*取得顺序表中第i个结点的值*/
datatype get(sequence_list slt,int i)
{
	if(i<0||i>=slt.size)
	{
		printf("\n指定位置的结点不存在!");
		exit(1);
	}
	else
	{
		return slt.a[i];
	}
}
/*在順序表的position的位置插入值为x的结点*/
void insert(sequence_list *slt,datatype x,int position)
{
	int i;
	if(slt->size==MAX)
	{
		printf("\n顺序表是满的!没法插入!");
		exit(1);
	}
	if(position<0||position>slt->size)
	{
		printf("\n指定插入的位置不存在!");
		exit(1);
	}
	for(i=slt->size;i>position;i--)
	{
		slt->a[i]=slt->a[i-1];
	}
	slt->a[position] = x;
	slt->size++;
}
/*删除顺序表中第position位置的结点*/
void dele(sequence_list *slt,int position)
{
	int i;
	if(slt->size == 0)
	{
		printf("\n顺序表是空的!");
		exit(1); 
	}
	if(position<0||position>=slt->size)
	{
		printf("\n指定删除的位置不存在!");
		exit(1);
	}
	for(i=position ; i<slt->size-1;i++)
	{
		slt->a[i] = slt->a[i+1];
	}	
	slt->size--;
}
int main()
{
	int i,j;
	int length;
	datatype temp;
    sequence_list list1;
    init(&list1);
    printf("请输入线性表的长度:\n");
    scanf("%d",&length);
    printf("请输入线性表中的元素:\n");
    for(i = 0;i < length; i++)
    {
    	scanf("%d",&temp);
    	append(&list1,temp);
	}
	printf("创建好了线性表\n");
	display(list1);
	printf("请输入想要插入的数字,和在哪里插入:\n");
	scanf("%d,%d",&temp,&j);
	insert(&list1,temp,--j);
	printf("插入后的结果为:\n");
	display(list1);
	printf("请输入需要查找的数字:\n");
	scanf("%d",&temp);
	temp = find(list1,temp)+1;
	printf("你所查找的数字的位置在第%d的位置\n",temp);
	printf("请输入你想删除的结点的位置:\n");
	scanf("%d",&j);
	dele(&list1,--j);
	printf("删除之后的结果为:\n");
	display(list1); 
	return 0;
}
