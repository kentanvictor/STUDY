# 异常
+ 做运行时异常，java中所有的运行时异常都会直接或间接地继承自RuntimeException
+ Java中凡是继承自Exception的类都是非运行时异常

****

```Java
public static void main(String[] args)
{
  int a =3;
  int b = 0;

  int c = a/b;
  System.out.println("Hello World");

  System.out.println(c);

}
```

****

如上面的代码中的c，会默认为是会抛出异常的
+ Java中关于抛出异常是有几种严格的规定的
  + 第一种：try与catch相结合

 ```Java
 int c = 0;
 try
 {
   int a = 3;
   int b = 0;
    c = a/b;
 }
 catch(ArithemticException e)
 {

 }
 System.out.println(c);
 ```

 上面的代码是可以运行的， 运行结果为0。**在java中，每一个异常就是一个类，当程序执行到某一行，出现了异常的情况，那么Java会在你会产生异常的那一行代码的位置处生成一个关于这个异常的对象，这个对象是由运行的时候动态的生成的**

 所以上面的c=a/b;这一行代码会自动生成一个对象，而这个对象就是ArithemticException

 **而catch块的意思就是，当我捕获到有这个对象生成，那么就由我的catch块中的代码去执行接下来的步骤**

`这个是try与catch的关于异常的固定的处理模式`
