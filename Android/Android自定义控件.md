# Android自定义控件

## Android属性动画的分类

+ View Animation(补间)
+ Drawable Animation(帧动画)
+ Property Animation(Android3.0新引入)

### View Animation

+ 基于view的渐变动画，只改变view的绘制效果，而实际属性值未该改变。
+ 比如：动画移动一个按钮位置，但按钮点击的实际位置仍未改变。
+ 代码中定义动画，可以参考`AnimationSet`类和`Animation`的子类。
+ 而如果使用XML，可以在res/anim/文件夹中定义XML文件。
+ 下面是一个实例

```java
TranslateAnimation animation = new TranslateAnimation(0,iv_animation.getWidth(),0,iv_animation.getHight());
/*
iv_animation是需要移动的图片的id
使用TranslateAnimation的构造方法，将图片从0(原始位置)移动图片长度的长度(iv_animation.getWidth);
高度的移动也是同样的道理
*/
animation.setDuration(3000);//设置延迟效果
animation.setFillAfter(true);//使得view不再复位
iv_animation.startAnimation(animation);//开始移动
```

### Drawable Animation

+ 加载一系列Drawable资源来创建动画，这种传统动画某种程度上就是创建不同图片序列，顺序播放，就像电影胶片。
+ 在代码中定义动画帧，使用`AnimationDrawable`类。
+ XML文件能更简单的组成动画帧，在`res/drawable`文件夹，使用`<animation-list>`采用`<item>`来定义不同的帧。
+ 注：感觉只能设置的属性是动画间隔时间。

### Property Animation(属性动画)

+ 动画的对象除了传统的view对象，还可以是Object对象，动画之后，Object对象的属性值就实实在在的改变了。
+ Property Animation能够通过改变view对象的实际属性来实现view动画。
+ 任何时候view属性的改变，view能够自动调用invalidate()来试试刷新。
+ 下面是一个实例

```java
ObjectAnimator animator = ObjectAnimator.ofFloat(iv_animation,"translationX",0,iv_animation.getWidth());
/*
使用ObjectAnimator的ofFloat进行平移
ofFloat(平移的id,“如何平移，如：(水平->translationX)”,初始位置,平移距离);
*/
ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv_animation,"translationY",0,iv_animation.getHight());
AnimatorSet animatorSet = new AnimatorSet();
AnimatorSet.setDuration(2000);
AnimatorSet.setInterpolator(new BounceInterpolator());
//两个动画一起播放
animatorSet.playTogether(animator,animator2);
//开始播放
animatorSet.start();
```

+ 另一种写法

```java
iv_animation.animate()
            .translationXBy(iv_animation.getWidth())
            .translationYBy(iv_animation.getHight())
            .setDuration(2000)
            .setInterpolator(new BounceInterpolator())
            .start();
```

### ViewPager(广告条效果)

+ ViewPager的使用
+ 广告条的基本功能
+ ViewPager拓展
+ 改进成引导页面

#### ViewPager的使用

+ 第一：使用`android.support.v4.view.ViewPager`在布局文件中定义
+ 第二：在代码中实例化ViewPager
+ 第三：准备数据
+ 第四：设置适配器(PagerAdapter)-item-布局-绑定数据
