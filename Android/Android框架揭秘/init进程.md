# init进程

init进程是Android启动后，由内核启动的第一个用户级进程。

1. start_kernel()
2. init_post()
3. run_init_process()

顺序执行完以上三个步骤就会运行init进程

```c
static int moinline init_post(void)
{
    if(execute_command){
        run_init_process(execute_command);
    }
    run_init_process("/sbin/init");
    run_init_process("/etc/init");
    run_init_process("/bin/init");
    run_init_process("/bin/sh");
}
```

如果内核在以上的目录以及未指定启动项的情况下找不到init文件，内核就会终止init进程，从而触发`Kernel Panic`。

## 四大功能

Android init进程主要提供了四大功能：

+ 分析及运行init.rc文件
  + 分析init.rc和init.{hardware}.rc文件
  + 运行early-init动作列表
  + 执行init动作列表

+ 生成设备驱动节点
  + 生成并装载目录
  + 生成设备静态节点
  + 注册POLL事件

+ 处理子进程终止
  + 注册SIGCHLD信号Handler
  + 生成UDS套接字
  + 注册POLL事件

+ 属性服务
  + 属性初始化
  + 属性初始化设置
  + 运行属性服务
  + 注册POLL事件
