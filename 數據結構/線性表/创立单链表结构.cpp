#include<stdio.h>
#include<malloc.h>
struct LNode
{
	int data;
	struct LNode *next;
};
	/*����ֻ�Ƕ�����һ���ṹ�����ͣ���δʵ�ʷ����ڴ�ռ�
	ֻ�ж����˱����ŷ����ڴ�ռ�*/
struct LNode *creat(int n)
{
	int i;
	struct LNode *head,*p1,*p2;
	/*head�����������p1��������ָ���·�����ڴ�ռ䣬
	p2����ָ��β��㣬��ͨ��p2�������·���Ľ��*/
	int a;
	head=NULL;
for(i=1;i<=n;i++)
{
	p1=(struct LNode *)malloc(sizeof(struct LNode));
	/*��̬�����ڴ�ռ䣬������ת��Ϊ(struct LNode)����*/
	printf("�����������еĵ�%d������",i);
	scanf("%d",&a);
	p1->data=a;
	if(head==NULL)/*ָ�������ͷָ��*/
	{
		head=p1;
		p2=p1;
	}
	else
	{
		p2->next=p1;
		p2=p1;
	}
	p2->next=NULL;/*β���ĺ��ָ��ΪNULL(��)*/
}
	return head;/*���������ͷָ��*/
}
int main()
{
	int n;
	struct LNode *q;
	printf("����������ĳ��ȣ�/n");
	scanf("%d",&n);
	q=creat(n);/*�����ͷָ��(head)�������������*/
	printf("/n�����е����ݣ�/n");
	while(q)/*ֱ�����qΪNULL����ѭ��*/
	{
		printf("%d ",q->data);/*�������е�ֵ*/
		q=q->next;/*ָ����һ�����*/
	}
}
