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

```