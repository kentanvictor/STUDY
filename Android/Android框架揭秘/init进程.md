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