# CSS

## 选择器

+ 选择器的样式：

> **选择器{ 属性:值; 属性:值 }**

+ 选择器：**选择标签的过程**

+ 选择器的种类：

>+ 标签选择器
>+ 类选择器
>+ ID选择器
>+ 通配符选择器
>+ 交集选择器（标签指定式）
>+ 子代选择器
>+ 并集选择器
>> + 补充选择器（C3）：
>> + 伪类选择器
>> + 属性选择器
>> + 伪元素选择器

### 标签选择器

+ 标签选择器的样式：
> **Html 标签名 { 属性:值;}**

+ 常用的属性：

> + color --> 前景色（文字颜色）
> + background-color --> 背景色
> + font-size --> 字体大小
> + width --> 设置宽度
> + height --> 设置高度
> + 代码形式如下：

>```html
>/*标签选择器*/
>P{
>    color:red;/*改变文字颜色*/
>    font-size:50px;/*改变文字大小*/
>}
>```
>

+ 属性单位问题：
> + px --> 像素
> + em --> 文字大小：**e.g: 1em=一个文字大小**

+ 颜色的表示方式：
> + 直接设置成颜色的名称： **e.g: red，green，pink。。。**
> + 使用十六进制表示：**e.g: 0-f**
> + 使用三原色表示： **rgb（）**
> + 半透明效果： **rgba() a的取值是0-1范围内**
> + + 半透明效果还可以用*opacity*
> + + 为了兼容IE8等更早的浏览器，则还需要加一句**filter:Alpha(opacity=50);**作为兼容

### 通配符选择器
+ 通配符选择器的语法：
> **{属性:值;.....}**

> + `常用在样式初始化的时候`
> + 因为不同浏览器的默认样式不一样，所以需要使用通配符选择器进行初始化。

### 标签指定式选择器
+ 标签指定式选择器的语法：
> **标签名.类名{属性：值;.....}**
> 或者
> **标签名#id名{属性：值;.....}**
 + 关系：
> + `既。。。又。。。`
>
> 示例代码如下：
>```html
>CSS:
>div.one{
>    color:green;
>}
>dic#two{
>    color:red;
>}
>HTML:
><div class="one"> div中的文字</div> 
><div id="red">id选择中的文字</div>
>```

### 后代选择器
+ 后代选择器的语法：
> **选择器 选择器{属性:值;......}**
+ `注意：`
> + 后代选择器标签之间必须是嵌套关系
> + 选择器与选择器之间必须使用空格隔开
> + 后代选择器只能选择后代元素，当前的元素无法被选中
> + 只要是父代中的子元素，就都会被选中
>
>示例代码如下：
>```html
>CSS:
> p span {
>   color: red;    
>}
>
>HTML:
>
><div class="one">
>   <span>文字1</span>
>   <p>
>       <span>文字2</span>
>   </p>
></div>
>```


## font属性介绍
+ `font-size：` **文字大小**
+ `font-weight：` **文字加粗效果** （值可以为：700（具体值）或 bold）（文字默认正常显示可以设置为400（具体值）或normal）
+ `font-style：` **设置文字是否斜体显示**  （值可以为：italic） （文字默认正常显示：normal）
+ `font-family：` **设置文字字体**  
+ + `注意：` **font-family一次性可以设置多个字体，字体值与值之间用“,”隔开，设置多个字体是为了适配各种浏览器。**
+ + 文字字体表现方式：
+ + + 直接设置字体对应的名称（如：宋体或黑体）
+ + + 设置字体对应的英文名称（如：SimSun（宋体）、NSimSun（新宋体）、微软雅黑（microsoft yahei）、黑体（SimHei））
+ + + 设置字体对应的unicode编码
+ `line-height：` **设置文字行高**
### font 属性联写
```html
font: font-weight  font-style  font-size/line-height  font-family;
```
+ 示例代码如下：
```html
font: 700 italic 30px/20px 微软雅黑;
```
+ **`注意：`**
+ + 使用font属性联写的过程中，必须要设置`font-size`和`font-family`
+ + 在属性联写中，font-size必须放在font-family
+ + 如果font属性联写中的属性都需要设置，那么就按照示例代码的方式去写。

## CSS中大小、定位、轮廓相关属性

+ 大小相关属性
> + height：用于设置目标对象高度。
> + max-height：用于设置目标对象的最大高度。 **如果此属性值小于min-height的属性值，将会自动转换为min-height属性值。**
> + min-height：用于设置目标对象的最小高度。 **和max-height的道理一样，会有自动转换的过程。**
> + width：用于设置目标对象的宽度。
> + max-width：用于设置目标对象的最大宽度。**如果此属性值小于min-width的属性值，将会自动转换为min-width属性值。**
> + 用于设置目标对象的最小宽度。 **和max-width的道理一样，会有自动转换的过程。**
>
>代码如下：
>```html
><div style="width=200px;height=40px;background-color=#ddd">CSS学习中</div>
>```

+ box-sizing属性
> + width和height只是指定该元素的内容区的宽度、高度。然而对与元素区域内的内补丁区、边框区、外补丁区所占的空间不产生任何效果。
> + border：设置内补丁区。*属性与width和height具有相同的属性值*
> + padding：设置边框区。*属性与width和height具有相同的属性值*
> + background-clip：设置元素的背景（背景图片或颜色）是否延伸到边框下面。
> + 备注：background-clip这个属性如果元素没有设置背景颜色或者图片，那么这个属性只有在边框（border）设置为透明或者半透明时才能看到视觉效果，不然这个属性造成的样式变化会被边框覆盖住。
> + background-clip属性的值有：
> + + border-box：背景延伸到边框外沿（但是在边框之下）。
> + + padding-box：边框下面没有背景，即背景延伸到内边距外沿。
> + + content-box：背景裁剪到内容区（content-box）外沿。
>
>background-clip示例代码如下：
>```html
>
><html lang="zh">
><head>
>    <meta charset="UTF-8">
>    <title>CSS+DIV页面布局</title>
>    <style type="text/css">
>        p {
>            border: 5px navy;
>            border-style: dotted double;
>            margin: 1em;
>            padding: 2em;
>            background: #F8D575;
>        }
>        .border-box { background-clip: border-box; }
>        .padding-box { background-clip: padding-box; }
>        .content-box { background-clip: content-box; }
>    </style>
></head>
><body>
><p class="border-box">The yellow background extends behind the border.</p>
><p class="padding-box">The yellow background extends to the inside edge of the border.</p>
><p class="content-box">The yellow background extends only to the edge of the content box.</p>
></body>
></html>
>```
>
>box-sizing代码如下：
>```html
><div style="width:200px;height=40px;background-color=#ddd;background-clip=content-box;border:30px solid #555;padding:30px">CSS学习引用</div>
>```