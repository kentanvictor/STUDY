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
/*˳���ĳ�ʼ��*/
void init(sequence_list *slt)
{
    slt->size = 0;
}
/*˳���Ĳ������*/
void append(sequence_list *slt,datatype x)
{
    if(slt->size == MAX)
    {
        printf("˳���������!");
        exit(1);
    }
    slt->a[slt->size] = x;
    slt->size = slt->size+1;
}
/*��ӡ˳����������ֵ*/
void display(sequence_list slt)
{
    int i;
    if(!slt.size)
    {
        printf("˳����ǿյ�!");
    }
    else
    {
        for(i = 0;i<slt.size;i++)
        {
            printf("%5d\t\n",slt.a[i]);
        }
    }
}
/*�ж�˳����Ƿ�Ϊ��*/
int empty(sequence_list slt)
{
    return (slt.size == 0 ? 1:0);
}
/*����������ֵΪx�Ľ���λ��*/
int find(sequence_list slt,datatype x)
{
	int i = 0;
	while(i<slt.size&&slt.a[i] != x)
	{
		i++;
	}
	return(i<slt.size ? i:-1);
}
/*ȡ��˳����е�i������ֵ*/
datatype get(sequence_list slt,int i)
{
	if(i<0||i>=slt.size)
	{
		printf("\nָ��λ�õĽ�㲻����!");
		exit(1);
	}
	else
	{
		return slt.a[i];
	}
}
int main()
{
	int i;
	int length;
	datatype temp;
    sequence_list list1;
    init(&list1);
    printf("���������Ա�ĳ���:\n");
    scanf("%d",&length);
    printf("���������Ա��е�Ԫ��:\n");
    for(i = 0;i < length; i++)
    {
    	scanf("%d",&temp);
    	append(&list1,temp);
	}
	printf("�����������Ա�\n");
	display(list1);
	printf("��������Ҫ���ҵ�����:\n");
	scanf("%d",&temp);
	temp = find(list1,temp)+1;
	printf("�������ҵ����ֵ�λ����%d��λ��",temp);
	return 0;
	
}
