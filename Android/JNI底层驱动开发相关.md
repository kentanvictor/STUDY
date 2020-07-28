# 底层驱动开发相关

## JNI 开发流程

+ 在Java中声明native方法

+ 编译Java源文件得到class文件，然后通过javah命令导出JNI头文件

+ 实现JNI方法

+ 编译so库并在Java中调用

## NDK 开发流程

+ 下载并配置NDK

+ 创建一个android项目，并声明所需的native方法

+ 实现android项目中所声明的native方法

+ 切换到JNI目录的父目录，然后通过ndk-build命令编译产生so库
