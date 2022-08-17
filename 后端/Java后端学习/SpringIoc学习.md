# SpringIOC 底层实现原理

## 传统方式开发

```java
UserService us = new UserService();
```

+ 面向接口编程

```java
UserService us = new UserServiceImpl();
```

+ ocp原则：Open-Close远测，对程序扩展是Open的，对修改程序代 码是close的。**尽量做到不修改程序代码，实现对程序的扩展。**

`工厂模式`

![示意图](https://img2020.cnblogs.com/blog/1410558/202103/1410558-20210306175200448-286289027.png)

大体实现过程如下：

```java
class FactoryBean{
    public static UserService getUs(){
        return new UserServiceImpl();
    }

    ……
}


UserService us = FactoryBean();
``` 

`工厂 + 反射 + 配置文件`

```xml
<bean id = "us" class = "com.kentanvictor.UserServiceImpl">
```

```java
class FactoryBean{
    public static Object getBean(String id){
        ……
        //反射的方式获取到实例对象
    }
}
```