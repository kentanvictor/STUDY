#include<stdio.h>
#include<malloc.h>
#include<stdlib.h>
typedef struct LNote
{
    int data;
    struct LNote *next;
}LNote;//����һ���ṹ�����ͣ�û�и�ֵ��û�����κ�ֵ
//���촴��������
LNote *creat(int n)
{
    int i;
    LNote *head,*p1,*p2;//����ͷ����p1ָ���·�����ڴ�ռ䣬p2ָ��β���
    int a;//
    head=NULL;
    for(i=1;i<=n;i++)
    {
        p1=(struct LNote *)malloc(sizeof(struct LNote));//
    }
}

