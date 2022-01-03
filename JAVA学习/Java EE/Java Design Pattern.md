# Java Design Pattern

`Ambition：`

+ 1、反射技术

+ 2、动态代理、责任链模式、拦截器

+ 3、观察者模式

+ 4、工厂和抽象工厂模式

+ 5、Builder构建模式

## Java 反射技术

正向的方式是：

```Java
Student john = new Student();
john.setAge(23);
```

反射的方式进行方法的调用是：

```Java
// 获取Class对象实例
Class clz = Class.forName("com.example.Student"); //类的全路径名
// 根据 Class 对象实例获取 Constructor 对象
Constructor appleConstructor = clz.getConstructor();
//使用 Constructor 对象的 newInstance 方法获取反射类对象
Object johnObj = appleConstructor.newInstance();
// 获取方法的 Method 对象
Method setAgeMethod = clz.getMethod("setAge", int.class);
// 利用 invoke 方法调用方法
setPriceMethod.invoke(johnObj, 23);

/** 
    如果有多个参数，可以写成
    Method.invoke(target, obj1, obj2, obj3, ……)
**/

```

## 动态代理、责任链模式、拦截器

### 动态代理

代理必须分为两步：

+ 代理对象和真实对象之间建立代理关系

+ 实现代理对象的代理逻辑方法

动态代理主要分为两种实现方式：

+ JDK动态代理 ————> JDK自带功能

+ CGLIB ————> 第三方

#### JDK动态代理

`注：需要接口`

+ 通常的写法：

定义接口：

```Java
public interface Hello {
    void morning(String name);
}
```

编写实现类：

```Java
public class HelloWorld implements Hello {
    public void morning(String name) {
        System.out.println("Good morning, " + name);
    }
}
```

创建实例，转型为接口并调用：

```Java
Hello hello = new HelloWorld();
hello.morning("Bob");
```

+ JDK动态代理写法：

`Proxy.newProxyInstance()`创建了一个`Hello`接口对象

```Java
public class Main {
    public static void main(String[] args) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };
        Hello hello = (Hello) Proxy.newProxyInstance(
            Hello.class.getClassLoader(), // 传入ClassLoader
            new Class[] { Hello.class }, // 传入要实现的接口
            handler); // 传入处理调用方法的InvocationHandler
        hello.morning("Bob");
    }
}

interface Hello {
    void morning(String name);
}

```