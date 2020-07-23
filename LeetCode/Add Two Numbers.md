# 两数相加

## 题目

给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)

输出：7 -> 0 -> 8

原因：342 + 465 = 807

`Related Topics `：**链表** **数学**

链表的结构：

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
```

所提供的函数：

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
    }
}
```
***
我是一条正经的分割线
***

## 解题思路

+ 这里面最核心的内容是：**除法、取模**这两者的正确使用。因为需要返回一个ListNode，那肯定是需要创建一个新的ListNode的对象作为总和的存储。

+ 为了不改变形参传入的链表的内容以及排列方式，可以创建两个新的链表对象进行数据的存储。

+ 其实就是从链表最开头进行相加，如果有进位那就对后一个链表进行相加。因此，在这其中，除法操作以及取余操作十分重要。

+ 当链表1与链表2第一个元素进行相加，存储在sum变量中。将sum对10进行取模操作，如果没有进位操作，那么`sum%10`的结果就为0，反之为1。同样的进行`sum/10`的操作，获取个位数的数值，存储在链表中。

+ 当进行到最后一位的加法操作时，如果还有进位就进行单独的判断。当sum等于1时，在返回的链表最后追加一个1就可以了。




## 最终结果

```java

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);   //作为output
        int sum = 0;
        ListNode cur = dummy;
        ListNode p1 = l1, p2 = l2;
        while (p1 != null || p2 != null){
            if (p1 != null){
                sum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null){
                sum += p2.val;
                p2 = p2.next;
            }
            cur.next = new ListNode( sum % 10);
            sum /= 10;
            cur = cur.next;
        }
        if (sum == 1){
            cur.next = new ListNode(1);
        }
        return dummy.next;
    }
}

```