#include<iostream>
using namespace std;
int n,m,i,num[100001],t[200001],l,r;//num:ԭ���飻t����״���� 
int lowbit(int x)
{
    return x&(-x);
}
void change(int x,int p)//����x������p 
{
    while(x<=n)
    {
        t[x]+=p;
        x+=lowbit(x);
    }
    return;
}
int sum(int k)//ǰk�����ĺ� 
{
    int ans=0;
    while(k>0)
    {
        ans+=t[k];
        k-=lowbit(k);
    }
    return ans;
}
int ask(int l,int r)//��l-r����� 
{
    return sum(r)-sum(l-1); 
}
int main()
{
    cin>>n>>m;
    for(i=1;i<=n;i++)
    {
        cin>>num[i];
        change(i,num[i]);
    }
    for(i=1;i<=m;i++)
    {
        cin>>l>>r;
        cout<<ask(l,r)<<endl;
    }
    return 0;
}

