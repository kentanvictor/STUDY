# java多线程

标签： java多线程

---

**Contents**
- [什么是线程](#进程与线程)
- [传统线程技术](#传统线程技术)
  - [Java中的线程](#Java中的线程)
  - [传统创建线程方式](#传统创建线程方式)
  - [互斥](#互斥)
  - [同步](#同步)

---

# 进程与线程

![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/SystemProcess.jpg)

+ `进程（process）是指运行中的应用程序，每一个进程都有自己独立的内存空间。`一个应用程序可以同时启动多个进程。例如对于IE浏览器程序，每打开一个IE浏览器窗口，就启动了一个新的进程。同样，每次执行JDK的java.exe程序，就启动了一个独立的Java虚拟机进程，该进程的任务是解析并执行Java程序代码。

![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/ProcessAndThread.jpg)

+ `线程（thread）是操作系统能够进行运算的最小单位。` **线程被包含在进程之中，是行程中的实际运行单位。一条线程是指进程中的一个单一顺序的控制流，一个进程中可以并行多个现成，每条线程并行执行不同的任务。** 当进程内的多个线程同时运行时，这种运行方式称为并发运行。许多服务器程序，如数据库服务器和Web服务器，都支持并发运行，这些服务器能同时响应来自不同客户的请求。

# 传统线程技术

## Java中的线程

>Java语言的一大特性点就是内置对多线程的支持。多线程是指一个应用程序中同时存在几个执行体，按几条不同的执行线索共同工作的情况。（如下图所示：）
![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/JVMThread.png)
>
>让我们提出这样一个问题：
>
>  `能否在一个Java程序中出现两个以上的无限循环呢？`
>
> 如果不使用多线程技术，是无法解决上述问题的，比如下面的代码：
>
```java
public class Hello{
    public static void main(String args[]){
        while(true){
            System.out.println("Hello");
        }
        while(true){
            System.out.println("您好");
        }
    }
}
```
>
>上述代码是有问题的，因为第2个while语句是永远没有机会会被执行到的。如果能在主线程中创建两个线程，每个线程分别执行一个while循环，那么两个循环就都有机会执行。`JVM负责管理这些线程，这些线程会被轮流执行，使得每个线程都有机会使用CPU资源。`
>


## 传统创建线程方式

![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/Thread.png)

1.继承Thread类，覆盖run方法

```java
Thread t = new Thread();
t.start();
```

2.实现Runnable接口

Runnable不是线程，是线程要运行的代码的宿主。

1.看看Thread类源码，捋清Runnable，target,run,start关系

- `Runnable`是一个接口
- `target`是`Thread`类中类型为`Runnable`，名为`target`的属性
- `run`是`Thread`类实现了`Runnable`的接口，重写的方法。
- `start`是启动线程的方法
- **在`Thread`类中，调用关系为：`start`->`start0`->`run`->`target.run`**

`Thread`类的`run`方法源码

```java
public void run() {
    if (target != null) {
        target.run();
    }
}
```

`Thread`类的`target`属性

```java
/* What will be run. */
private Runnable target;
```

`target`属性由`private void init(ThreadGroup g, Runnable target, String name,long stackSize, AccessControlContext acc)`方法初始化。`init`方法在`Thread`类的构造方法里被调用




2.匿名内部类对象的构造方法如何调用父类的非默认构造方法



## 互斥

关键字:`synchronized`，检查锁对象

- `synchronized(this)`
- `synchronized void function(){}`
- `synchronized(A.class)`

例子：实现登录

+ A登录类：

```java
public class ALogin extends Thread{
    @Override
    public void run() {
        LoginServlet.doPost("a","aa");
    }
}
```
+ B登录类：

```java
public class BLogin extends Thread{
    @Override
    public void run() {
        LoginServlet.doPost("b","bb");
    }
}
```
+ 登录方法：
```java
public class LoginServlet {
    private static String usernameRef;
    private static String passwordRef;
    public static void doPost(String username,String password){
        try {
            usernameRef = username;
            if (username.equals("a")){
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("userName=" + usernameRef + "\t" + "password=" + password);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
```
+ 实现类：

```java
public class Run {
    public static void main(String[] args) {
        ALogin a = new ALogin();
        a.start();
        BLogin b = new BLogin();
        b.start();
    }
}
```
`如果不使用互斥锁，则会出现下面的情况：`

![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/ThreadResultWithNotSy.png)

>为了避免由于线程的随机运行带来的线程的不安全性，我们需要在`doPost方法前加上互斥锁（synchronized）`。

## 同步

经验：

>* 要用到共同数据(包括同步锁)或共同算法的若干个方法应该归在同一个类身上，这种设计体现了高聚类和程序的健壮。性。
>* 同步互斥不是在线程上实现，而是在线程访问的资源上实现，线程调用资源。

例子: 子线程循环5次，主线程循环10次，如此交替50次

设计：

使用一个Business类来包含子线程和主线程要运行的代码，从而，该类的对象成为加锁的对象。同步互斥在该类实现，由线程调用该类的方法，即调用了资源。

代码：

```java

public class TraditionalThreadCommunication {
    public static void main(String[] args) {
        Business business = new Business();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for(int i=1;i<=50;i++){
                            business.sub(i);
                        }
                    }
                }
        ).start();

        for(int i=1;i<=50;i++){
            business.main(i);
        }

    }
}

class Business{
    private boolean bShouldSub = true;

    public synchronized void sub(int i){
        while(!bShouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j=1;j<=5;j++){
            System.out.println("sub thread count "+j+","+i+"/50");
        }
        bShouldSub = false;
        this.notify();
    }
    public synchronized void main(int i){
        while(bShouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j=1;j<=10;j++){
            System.out.println("main thread count "+j+","+i+"/50");
        }
        bShouldSub = true;
        this.notify();
    }
}
```

判断条件时，while与if的区别:while防止伪唤醒。
