 ****注：構造方法與普通方法之間的區別在于：構造方法的名稱與類名相同、不需要定義返回值類型和不可以寫renturn語句。其作用只在於給對象起到初始化的效果****
 ### 构造方法与一般方法在写法上的不同：
 构造方法：
 ```java
 public(修饰符) class（定义类的关键字） Test（类名）()
 {
 //无参
  Test（类名）（）
  {  


  }
      //带一个参数
      Test（类名）（String(变量类型) name（变量名））
      {


      }

}
 ```
 ### 一般方法：
 定义类：
 ```java
 public(修饰符) class（定义类的关键字） Test（类名）()
 {
       private(修饰符) static(修饰符) String(变量类型) s（变量名） = "hello world";
   // 下面是方法的定义
       public(修饰符)  static(修饰符)  void(返回值类型)  main（方法名）（String[] args（参数）)
       {
              System.out.println(s);（代码语句）
       }
}
 ```
>#### 构造方法与一般方法在运行上的不同：
 * 构造方法：
 构造方法在创建的时候就给对象初始化；
 一个对象建立构造方法只能运行一次；
 * 一般方法：
 一般方法是对象调用才执行，给对象添加对象具备的功能；
 一般方法可以被对象多次调用；

# Interface Set < E >
* 實現Set接口的類有：
 * AbstractSet、ConcurrentSkipListSet、CopyOnWriteArraySet、EnumSet、HashSet、JobStateReasons
 * LinkedHashSet、TreeSet

## 在Interfere Set< E >接口中，使用的最多的繼承的類就是HashSet
 * HashSet的構造方法一共有四個：
  * HashSet()
  * HashSet(Collection<? extends E> c)
  * HashSet(int initialCapacity)
  * HashSet(int initialCapacity,float loadFactor)


 * Method Summary
  * boolean add(E e)
   >Add the specified element to this set if it is not already present.(如果不存在，则将指定的元素添加到该集合中)
   >If this set already contains the element, the call leaves the set unchanged and renturns false.(如果集合中包含这个元素，则集合不会被修改，返回值为假。)

  * void clear()
   >Removes all of the elements from this set.(移除此集合中的所有元素。)

  * Object clone()
   >Returns a shallow copy of this HashSet instance: the elements themselves are not cloned.(返回此HashSet实例浅拷贝：元素本身不是克隆。)

  * boolean contains(Object o)
   >Returns true if this set contains the specified element.(如果此集合包含指定元素，则返回true。)

  * boolean isEmpty()
   >Returns true if this set contains no element.(如果此集合不包含元素，返回true。)

  * Interator< E > interator()
   >Returns an iterator over the elements in this set.(返回此集合中元素的迭代器。)

  * boolean remove(Object o)
   >Removes the specified element from this set if it's present.(如果此集合存在，则从该集合中移除指定元素。)

  * int size()
   >Returns the number of elements in this set(its cardinality)(返回此集合中的元素数（它的基数）

### 關於Object類的equals方法的特點
* 自反性：x.equals(x)應該返回true
* 對稱性：x.equals(y)為true，那麼y.equals(x)返回值也為true
* 可傳遞性：x.equals(y)為true並且y.equals(z)為true，那麼x.equals(z)也應該為true。
* 一致性：x.equals(y)的第一次迪奧喲經為true，那麼x.equals(y)的第二次、第三次、第n次調用也應該為true，前提條件是在比較之間沒有修改x也沒有修改y。
* 對於任何的非空引用x，x。equals(null)返回false。
**當我們需要重寫hashcode()方法的時候也需要重寫equals()方法，同樣的道理，當重寫了equals()方法也需要重寫equals()方法**

## 關於Object類中的hashcCode()方法的特點：
* 在Java應用的一次執行過程當中，對於同一個對象的hashcCode方法的多次調用，他們應該返回同樣的值。
