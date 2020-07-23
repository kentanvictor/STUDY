# String类陷阱深度解析
`注：`
+ 对于String对象的相等性判断，使用equals()方法，而不要用“==”
+ String 是常量，其对象一旦创建完毕就无法改变，当使用+凭借字符串的时候会生成新的String对象，而不是向原有的String对象追加内容
## String 创建对比  
`注：`String pool(字符串池)
### 第一种创建方式

```java
 string s = "aaa";//（采用字面值方式赋值）
```

+ 1、查找**String Pool**中是否有“aaa”这个对象，如果不存在则在**String Pool**中创建一个“aaa”对象并将“aaa”对象地址返回，赋给引用变量s，这样s会指向**String Pool**中的“aaa”对象。
+ 2、如果**String Pool**中存在，则不创建对象，直接将**String Pool**中的“aaa”对象地址返回，赋给s引用。

### 第二种创建方式

```java
String s = new String("aaa");
```

+ 1、首先在**String Pool**中查找有没有“aaa”字符串对象，如果有，则不在**String Pool**中再去创建“aaa”对象，直接在 **堆(heap)** 中创建一个“aaa”字符串对象，然后将堆中的地址返回赋给s引用，导致s指向了堆中的对象。
+ 2、如果**String Pool**中没有“aaa”对象，那么会在**String Pool**中创建一个“aaa”对象， 然后再在 **堆(heap)** 中创建一个“aaa”对象，返回的地址是堆中的地址。
  
`注：`new出来的对象所指向的都是 **堆(heap)** 中所创建的。