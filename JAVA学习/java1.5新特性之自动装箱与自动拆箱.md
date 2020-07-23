## 自动装箱
>基本类型自动转为包装类 `int >> Integer`

>自动装箱是编译器通过自动调用`valueOf()`实现的。

>***下面的就是将3自动装箱转换成Integer类型并放到集合中***

```java
public class BoxTest {
    public static void main(String[] args) {
        int a = 3;
        Collection<Integer> c = new ArrayList<Integer>();

        c.add(3);           //这里就是自动的装箱，将3转换成Integer类型并放到集合中
    }
}
```

## 自动拆箱
> 包装类自动转为基本类型 `Integer >> int`


## 面试常见题目：
> 自动拆箱与自动装箱的问题。

>Iteger的范围为`[-128，127]`。

```java
public class App {  
        public static void main(String[] args) {  
              Integer i1 = 127;  
              Integer i2 = 127;  
              Integer i3 = 128;  
              Integer i4 = 128;  

              System. out.println(i1 == i2);  
              System. out.println(i3 == i4);  
       }  

}  
```
