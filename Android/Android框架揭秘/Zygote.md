# Zygote

![flow chart](https://github.com/kentanvictor/STUDY/blob/Image/Android%E6%A1%86%E6%9E%B6%E6%8F%AD%E7%A7%98/initToAppProgress.png?raw=true)

先后顺序：

init进程 –-> Zygote进程 –> Dalvik VM进程 –> SystemServer进程 –>应用进程

在init.rc文件被init.cpp中SecondStageMain函数中进行解析的时候，位于core\/rootdir文件夹下的init.rc文件中有一行：`import /init.${ro.zygote}.rc`