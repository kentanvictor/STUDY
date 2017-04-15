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
 
