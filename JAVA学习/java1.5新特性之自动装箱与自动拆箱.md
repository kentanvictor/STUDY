## 自动装箱
>基本类型自动转为包装类 `int >> Integer`
>
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
