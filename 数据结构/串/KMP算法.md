# KMP算法
> **前言：**KMP算法是一种模式匹配算法，由D.E.Knuth、J.H.Morris和V.R.Pratt三人发表的一个模式匹配算法【当我看懂之后，对这三位前辈的敬畏之情油然而生】。`最重要的是，可以大大的避免重复遍历的情况的发生！`

>话不多说，让我们看一看KMP算法的神奇之处。

### 朴素的模式匹配算法
+ 首先，我们得先知道最简单，最容易想到的朴素的模式匹配算法是如何实现的。

```
private static int MatchingTest(String S,String T) {
       for(int i = 0; i < S.length(); i++)
       {
           int k = i;
           for(int j  = 0; j < T.length();j++)
           {
               if(S.charAt(k) != T.charAt(j))
               {
                   break;
               }
               else
               {
                   k++;
                   if(j == T.length()-1)
                   {
                       return i;
                   }
               }
           }
       }
       return -1;
    }
```
