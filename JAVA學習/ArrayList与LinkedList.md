* 其中Object类型的元素element就是向LinkedList中所添加的元素，然后Entry又构造好了向前与向后的引用previous、next，最后生成的这个Entry对象加入到了链表中。换句话说，**LinkedList维护的是一个个的Entry对象。**
* 而entry中是
```java
entry
{
  Entry previous;
  Object element;
  Entry next;
}
```
* 如上面的代码所示,这就直接构成了一个数据元素,然后放到链表中.
