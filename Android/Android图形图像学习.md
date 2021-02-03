# Android 图形图像学习

> 自我学习笔记，从上层layout的加载到HAL的绘制展示。

## 大致过程

整个显示过程由App、system_server和SurfaceFlinger三个进程共同配合完成：

+ App：负责画图

+ system_server：负责控制图层

+ SurfaceFlinger：负责混合图层并显示

这个涉及到画图与合图的过程，App负责画图，SurfaceFlinger负责合成所有图层并展示。

### 可以画图的 **"画笔"** ：

+ Skia：CPU绘制2D图形

+ OpenGL：GPU绘制3D图形

### 可以合图的 **"工具"** ：

+ GPU：使用OpenGL来合成图层

+ HWC：使用显示控制器来合成图层


## 详细过程

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

