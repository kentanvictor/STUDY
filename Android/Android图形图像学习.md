# Android 图形图像学习

> 自我学习笔记，从上层layout的加载到HAL的绘制展示。

+ setContentView(@LayoutResint layoutResID)

从代码中可以看到，继承的Activity中的setContentView方法以及AppCompatActivity中实现的，代码如下：

```java
@Override
    public void setContentView(View view) {
        getDelegate().setContentView(view);
    }
```

在这上面可以看到一个 **getDelegate()** 这么一个方法，这个方法最终return的是一个AppCompatDelegate对象。AppCompatDelegate类是用来进行AppCompatActivity代理的，AppCompatActivity将大部分生命周期都委托给AppCompatDelegate。

通过代码跳转我们可以看到：AppCompatDelegate是一个抽象类，这个抽象类中的抽象方法最终实现的地方是：AppCompatDelegateImpl中。

