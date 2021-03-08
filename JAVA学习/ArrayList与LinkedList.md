# ArrayList and LinkList

## ArrayList

## LinkList

## ArrayList与LinkList底层数据存储的区别

* 当向ArrayList添加一个对象的时候,实际上就是将该对象放置到ArrayList底层所维护的数组当中;当向LinkedList中添加一个对象的时候,实际上LinkedList内部会生成一个Entry对象

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

* 其中Object类型的元素element就是向LinkedList中所添加的元素，然后Entry又构造好了向前与向后的引用previous、next，最后生成的这个Entry对象加入到了链表中。换句话说，**LinkedList维护的是一个个的Entry对象。**


# 队列的实现

+ 队列的实现

```java
public class MyQueue
{
  private LinkedList LinkedList = new LinkedList;
  public void put (Object o){
    list.addLast(o);
  }
  public Object get(){
    return list.removeFirst();
  }
  public boolean isEmpty()
  {
    return list.isEmpty();
  }

  public static void main(String[] args) {
    MyQueue myQueue = new MyQueue();
    myQueue.put("one");
    myQueue.put("two");
    myQueue.put("three");
    System.out.println(myQueue.get());
    System.out.println(myQueue.get());
    System.out.println(myQueue.get());
    System.out.println(myQueue.isEmpty());
  }
}
```
