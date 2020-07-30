音频系统主要分为连个部分：`数据流`和`策略`。

+ `数据流：` **AudioTrack**，**AudioFlinger**就是数据流，描述了音频数据从数据源流向目的地的流程。

+ `策略：` **AudioPolicyService**，**AudioService**都是策略的范畴，管理及控制数据流的路径与呈现方式。

|    | Audio管理环节  |  Audio输出   | Audio输入  |
|  ----  | ----  |  ----  | ----  |
| Java层  | android.media.AudioSystem | android.media.AudioTrack  | android.media.AudioRecorder |
| 本地框架层  | AudioSystem | AudioTrack  | AudioRecorder |
| AudioFlinger  | IAudioFlinger | IAudioTrack  | IAudioRecorder |
| 硬件抽象层  | AudioHardwareInterface | AudioSteamOut  | AudioStreamIn |

总通信方式大致如下：
![android通信方式](https://wx1.sbimg.cn/2020/07/30/PY0mj.png)

Android音频系统的`核心`是Audio系统

![Audio系统框架结构](https://wx1.sbimg.cn/2020/07/30/PYpxN.png)

在Java层中，AudioSystem的最上层是AudioService、AudioSystem进行着Audio相关的管理，下面是AudioService.java所在的路径。

![AudioService路径](https://wx1.sbimg.cn/2020/07/30/PYdao.png)

以其中一个为例子，isAudioServerRunning方法用来判断音频系统是否工作，可以看到，中间的checkMonitorAudioServerStatePermission()方法是用来判断是否给予权限，而实际上的判断语句是return后面`AudioSystem.checkAudioFlinger`进行比较的。

![AudioService中方法](https://wx2.sbimg.cn/2020/07/30/PYwh4.png)

使用OpenGrok，我们可以追踪到AudioSystem.java文件所在的路径。

![PYUED.png](https://wx1.sbimg.cn/2020/07/30/PYUED.png)

在该类中，我们可以发现，其通过JNI的方式，声明了checkAudioFlinger()的方法。我们就可以通过该方法去JNI层去寻找C++中实现的类。

![AudioSystem中checkAudioFlinger](https://wx1.sbimg.cn/2020/07/30/PYcgw.png)

可以看到，在`core/jin/`的包下，有一个android_media_AudioSystem.cpp的对应的文件进行实现JNI层提供的checkAudioFlinger()的接口。

`问：`Java中如何通过方法名就能够找到native层中的函数呢？

`答：`native层的函数名遵循如下规则：**Java_包名_类名_方法名**

![android_media_AudioSystem路径](https://wx1.sbimg.cn/2020/07/30/PYv1e.png)

在android_media_AudioSystem.cpp中具体的实现方法如下：

![android_media_AudioSystem实现方式](https://wx1.sbimg.cn/2020/07/30/PYfvw.png)

可以看到其中的具体实现是`check_AudioSystem_Command()`函数中实现的， 然后传入了AudioSystem中的CheckAudioFlinger()函数的返回值。下图显示的是AudioSystem类中的checkAudioFlinger()函数的实现。
+ NO_ERROR = 0

![checkAudioFlinger()方法](https://wx1.sbimg.cn/2020/07/30/PYxhj.png)

从`check_AudioSystem_Command()`函数中可以看到最终也是返回一个状态值。

![check_AudioSystem_Command()](https://wx1.sbimg.cn/2020/07/30/PYnpN.png)

defaultServiceManager()是一个位于binder下IServiceManager.cpp中的一个函数

![defaultServiceManager智能指针](https://wx2.sbimg.cn/2020/07/30/PZ54o.png)

`注：` sp指的是shared_ptr，C++11后推出的智能指针的概念。shared_ptr是实现共享式拥有概念。多个智能指针可以指向相同对象，该对象和其相关资源会在“最后一个引用被销毁”时候释放。

### 总结

以上只是一种从上层Java层一路追溯到native层中C++的实现方式。而这也侧面的证明了Java层与native层的数据通信的方式是通过Binder实现的。

在native层中的AudioSystem.cpp中就属于一个中间件的作用，进行了承上启下，在AudioSystem.cpp中，有AudioFlinger相关的智能指针，如下图所示：

![AudioSystem中与AudioFlinger相关指针](https://wx1.sbimg.cn/2020/07/30/PZMAl.png)

+ gAudioFlinger作为AudioFlinger的代理端句柄可以输入输出设备进行相关控制。

![get_audio_policy_service函数](https://wx2.sbimg.cn/2020/07/30/PZXH1.png)

+ gAudioPolicyService作为AudioPolicyService服务代理端的句柄提可以对策略相关业务流程进行调度。