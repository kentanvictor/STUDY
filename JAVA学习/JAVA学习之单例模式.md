# 单例模式（Singleton）详解

+ 1、接口中声明的方法都是抽象方法。并且接口中的方法都是public.
+ 2、接口中也可以定义成员变量，但是需要赋值接口中的成员变量都是static public and final的。就因为是final所以需要赋值
+ 3、一个类不能既是final，又是abstract的，因为abstract的主要目的是定义一种约定，让子类去实现这种约定，而final表示该类不能被继承，这样abstract希望该类可以被继承而final又明确说明该类不能被继承，两者矛盾。因此，一个类不能既是final又是abstract的。

单例模式表示一个类只会生成唯一的一个对象。 （如果生成一个类，那么构造方法一定会被调用，不管类有几个构造方法，肯定有一个会被调用）--> 所以单例模式是从构造方法的方向入手，去建立唯一的一个类。

## 第一种的单例模式生成唯一实例的方式

```java
public class SingletonTest
{
   public static void main(String[] args)
  {
    Singleton singleton = Singleton.getInstance();
    Singleton singleton2 = Singleton.getInstance();
    System.out.println(singleton == singleton2);//用来判断是否只生成了一个实例，如果返回true，那么证明生成的两个对象所指向的是同一个实例。
  }
}
//为保证能够让Main不能够通过Singleton singleton = new Singleton();的方式来生成对象，那么久需要在构造方法前面加上private，而构造出一个静态方法，在类中去生成唯一的一个实例。
class Singleton
{
  private static Singleton singleton = new Singleton();//只有静态方法才能够调用静态方法
  private Singleton()
  {

  }
  public static Singleton getInstance()
  {
      return singleton;
  }
}
```

## 第二种的单例模式生成唯一实例的方式

```java
public class SingletonTest
{
   public static void main(String[] args)
  {
    Singleton singleton = Singleton.getInstance();
    Singleton singleton2 = Singleton.getInstance();
    System.out.println(singleton == singleton2);//用来判断是否只生成了一个实例，如果返回true，那么证明生成的两个对象所指向的是同一个实例。
  }
}

class Singleton
{
  private static Singleton singleton;//只有静态方法才能够调用静态方法,并且这是一个引用类型的变量，默认初始为null
  private Singleton()
  {

  }
  public static Singleton getInstance()
  {
      if(singleton == null)
      {
            singleton = new Singleton();
      }
      return singleton;
  }
}
```

`注：`
如果在多线程的环境下，更加推荐使用第一种的方式来写单例模式。而第二种方式在多线程的情况下有可能就不是单例了。
