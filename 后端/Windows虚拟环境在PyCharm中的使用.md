# Anaconda

> 网上对于Anaconda的安装是教程有很多，这里会为各位提供的是一个我当时安装时候所参考的链接地址。[Anaconda的介绍、安装及使用教程](https://zhuanlan.zhihu.com/p/32925500)

## Anaconda中创建虚拟环境（命令行模式）

> 在Anaconda中创建虚拟环境的方式：
>
> 下面是创建python=3.6版本的环境，取名叫py36
>
> conda create -n py36 python=3.6
>
>删除环境
>
>conda remove -n py36 --all
>
>激活环境
>
>source activate py36
>
>退出环境
>
>source deactivate

+ 在创建虚拟环境的过程中，我遇到了一个问题，这个问题就是：`如果创建了多个python版本的环境，那么我应该如何去管理当中所安装的不同的包呢？并且与本机原本安装的python环境不起到冲突？`

+ 我解决Anaconda与本机办法是这样的：
>+ 解决办法一：
>
>其实python 在安装的时候就为我们提供了两个版本的解决方案
>
>在C盘的windows 目录下有个 py.exe
>
>我们用py.exe 这个工具启动就能分辨出python的版本
>
>启动python2.7的pip
>
>直接在cmd 窗口里输入：py -2 -m pip install XXXX
>
>启动python3.6的pip
>
>直接在cmd 窗口里输入：py -3 -m pip install XXXX
>
>+ 解决办法二：
>
>找到自己本机安装的Python路径（在Path中可以直接找到），然后找到自己的Python.exe文件，然后重命名即可。
>
>如：我对我本机的Python重命名为Python3，然后对Anaconda中安装的Python不重命名。
>
>之后需要对Anaconda中的python进行pip安装的时候，就输入：
>
>python -m pip install XXX
>
>然后对本机的python进行安装的时候，就输入：
>
>python3 -m pip install XXX

+ 我解决Anaconda进入虚拟环境之后与Anaconda自身的Python冲突的问题：
>
>根据上面启动虚拟环境的方法，我们能够启动已经创建的虚拟环境
>
>进入虚拟环境之后，输入：
>
>python -V
>
>就能够看到当前的虚拟环境中的python的版本号
>
>这个时候，输入：
>
>pip list
>
>你所看到的就是你当前环境下的pip所安装的包。
>
>`如果你发现你的list还是Anaconda自带的list的话，就输入：`
>
>`python -m pip list`

## Anaconda中创建虚拟环境（GUI模式）

`当你安装好Anaconda之后，会出现一个Anaconda Mavigator，点击之后在里面也可以创建虚拟环境。`

![AnacondaMavigator](https://images2018.cnblogs.com/blog/1410558/201807/1410558-20180715214011743-297214195.png)

# PyCharm

+ 在PyCharm中的`Settings`中的`Project Interpreter`进行python版本的选择。

+ 找到你的`Anaconda`的`安装路径`。

+ 然后在里面找到一个文件夹叫`envs`。

+ 在这个文件夹里面有你创建的所有的虚拟环境。

+ 选择你需要用到的虚拟环境，然后找到`python.exe`即可。
