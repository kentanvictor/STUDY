# Object详解
## 相等性的比较（==）
+ 1)对于原生数据类型来说，比较的是左右两边的值是否相等。
+ 2)对于引用类型来说，比较左右两边的引用是否指向同一个对象，或者说左右两边的引用地址是否相同。
## java.lang.Object类
java.lang包在使用的时候无需显式导入，编译的时候由编译器自动帮助我们导入。
## API(Application Programming Interface)
应用编程接口
#### 当打印引用时，实际上会打印出引用所指对象的toString()方法的返回值，因为每个类都直接或间接的继承自Object，而Object类中定义了toString()方法，所以每一个类中都有toString()这个方法
## 关于进制的表示：
16进制（逢16进1），16进制的数字包括0~9，A，B，C，D，E，F