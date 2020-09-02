# Zygote

![flow chart](https://github.com/kentanvictor/STUDY/blob/Image/Android%E6%A1%86%E6%9E%B6%E6%8F%AD%E7%A7%98/initToAppProgress.png?raw=true)

`问题：`

+ zygote是如何启动的
+ zygote主要是负责哪些事情的
+ zygote和AndroidRuntime有什么关系
+ AndroidRuntime和虚拟机实例对象又是什么关系
+ zygote进程是如何从native层进入Java层的（JNI接口）
+ 通过zygote fork出来的进程，跟exec开启的进程有什么区别
+ zygote和（ZygoteServer） zygoteSocket,UASPSocket有什么关联？或者说使用socket做了什么事情
+ zygoteInit（ZygoteInit.java--JNI-->zygoteInit.cpp）中做了什么事情
+ zygote进程是如何完成fork子进程的
+ zygote是如何启动systemServer（AMS，PMS，WMS……）的
+ zygote fork子进程的copy-on-write在源码中的体现

先后顺序：

~~nit进程 –-> Zygote进程 –> Dalvik VM进程 –> SystemServer进程 –>应用进程~~

由于Zygote是由Java编写的，所以如何要运行Zygote必须得先把Dalvik虚拟机跑起来，而在init.rc中执行这一任务的进程是：`app_process`

在init.rc文件被init.cpp中SecondStageMain函数中进行解析的时候，位于core\/rootdir文件夹下的init.rc文件中有一行：`import /init.${ro.zygote}.rc`

从rc文件中可以看到第一行会执行一个app_process的可执行文件，在P中，这个可执行文件所链接到的cpp位置为：`frameworks/base/cmds/app_process/app_process.cpp`

在app_process中的main函数中的while中有对应的制定参数针对rc文件的第一行执行命令所传入的参数

在Linux中，只有0号进程，又被称为`idle`，不是通过fork()方式进行创建的，其他的进程，包括init进程的创建方式都是通过fork()进行的

Zygote进程首先fork出“System server”进程，“System server”进程会有PowerManager Service、Sensor Service、Location Service等服务的开启

`COW:`因为复制内存的开销比较大，因此创建的子进程在引用父进程的内存空间时，不要进行复制，而要直接共享父进程的内存空间。

## AppRuntime对象

AppRuntime类用于初始化并运行Dalvik虚拟机，再在Dalvik上装在ZygoteInit Class。

### app_process

app_process参数形式如下：

`app_process [java-options] cmd-dir start-class-name[options]`

+ [java-options] : 传给虚拟机的选项，必须以“-” 开始

+ cmd-dir : 所要运行的进程所在的目录

+ start-class-name : 要在虚拟机中运行的类的名称