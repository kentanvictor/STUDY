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
![android通信方式](https://github.com/kentanvictor/STUDY/blob/Image/JNI%E5%88%B0native%E7%9A%84AudioSystem%E8%B4%AF%E9%80%9A/android%E9%80%9A%E4%BF%A1%E6%96%B9%E5%BC%8F.png?raw=true)

Android音频系统的`核心`是Audio系统

![Audio系统框架结构](https://github.com/kentanvictor/STUDY/blob/Image/JNI%E5%88%B0native%E7%9A%84AudioSystem%E8%B4%AF%E9%80%9A/Audio%E7%B3%BB%E7%BB%9F%E6%A1%86%E6%9E%B6%E7%BB%93%E6%9E%84.png?raw=true)

在Java层中，AudioSystem的最上层是AudioService、AudioSystem进行着Audio相关的管理，下面是AudioService.java所在的路径。

![AudioService路径](https://github.com/kentanvictor/STUDY/blob/Image/JNI%E5%88%B0native%E7%9A%84AudioSystem%E8%B4%AF%E9%80%9A/AudioService%E8%B7%AF%E5%BE%84.png?raw=true)

以其中一个为例子，isAudioServerRunning方法用来判断音频系统是否工作，可以看到，中间的checkMonitorAudioServerStatePermission()方法是用来判断是否给予权限，而实际上的判断语句是return后面`AudioSystem.checkAudioFlinger`进行比较的。

![AudioService中方法](https://github.com/kentanvictor/STUDY/blob/Image/JNI%E5%88%B0native%E7%9A%84AudioSystem%E8%B4%AF%E9%80%9A/AudioService%E4%B8%AD%E6%96%B9%E6%B3%95.png?raw=true)

使用OpenGrok，我们可以追踪到AudioSystem.java文件所在的路径。

![AudioSystem路径](https://github.com/kentanvictor/STUDY/blob/Image/JNI%E5%88%B0native%E7%9A%84AudioSystem%E8%B4%AF%E9%80%9A/AudioSystem%E8%B7%AF%E5%BE%84.png?raw=true)

在该类中，我们可以发现，其通过JNI的方式，声明了checkAudioFlinger()的方法。我们就可以通过该方法去JNI层去寻找C++中实现的类。

![AudioSystem中checkAudioFlinger](https://github.com/kentanvictor/STUDY/blob/Image/JNI%E5%88%B0native%E7%9A%84AudioSystem%E8%B4%AF%E9%80%9A/AudioSystem%E4%B8%ADcheckAudioFlinger.png?raw=true)

可以看到，在`core/jni/`的包下，有一个android_media_AudioSystem.cpp的对应的文件进行实现JNI层提供的checkAudioFlinger()的接口。

`问：`Java中如何通过方法名就能够找到native层中的函数呢？

`答：`native层的函数名遵循如下规则：**Java_包名_类名_方法名**

![android_media_AudioSystem路径](https://github.com/kentanvictor/STUDY/blob/Image/JNI%E5%88%B0native%E7%9A%84AudioSystem%E8%B4%AF%E9%80%9A/android_media_AudioSystem%E8%B7%AF%E5%BE%84.png?raw=true)

在android_media_AudioSystem.cpp中具体的实现方法如下：

![android_media_AudioSystem实现方式](https://github.com/kentanvictor/STUDY/blob/Image/JNI%E5%88%B0native%E7%9A%84AudioSystem%E8%B4%AF%E9%80%9A/android_media_AudioSystem%E5%AE%9E%E7%8E%B0%E6%96%B9%E5%BC%8F.png?raw=true)

可以看到其中的具体实现是`check_AudioSystem_Command()`函数中实现的， 然后传入了AudioSystem中的CheckAudioFlinger()函数的返回值。下图显示的是AudioSystem类中的checkAudioFlinger()函数的实现。
+ NO_ERROR = 0

![checkAudioFlinger()方法](https://github.com/kentanvictor/STUDY/blob/Image/JNI%E5%88%B0native%E7%9A%84AudioSystem%E8%B4%AF%E9%80%9A/checkAudioFlinger()%E6%96%B9%E6%B3%95.png?raw=true)

从`check_AudioSystem_Command()`函数中可以看到最终也是返回一个状态值。

![check_AudioSystem_Command()](https://github.com/kentanvictor/STUDY/blob/Image/JNI%E5%88%B0native%E7%9A%84AudioSystem%E8%B4%AF%E9%80%9A/check_AudioSystem_Command().png?raw=true)

defaultServiceManager()是一个位于binder下IServiceManager.cpp中的一个函数

![defaultServiceManager智能指针](https://github.com/kentanvictor/STUDY/blob/Image/JNI%E5%88%B0native%E7%9A%84AudioSystem%E8%B4%AF%E9%80%9A/defaultServiceManager%E6%99%BA%E8%83%BD%E6%8C%87%E9%92%88.png?raw=true)

`注：` sp指的是shared_ptr，C++11后推出的智能指针的概念。shared_ptr是实现共享式拥有概念。多个智能指针可以指向相同对象，该对象和其相关资源会在“最后一个引用被销毁”时候释放。

### 总结

以上只是一种从上层Java层一路追溯到native层中C++的实现方式。而这也侧面的证明了Java层与native层的数据通信的方式是通过Binder实现的。

在native层中的AudioSystem.cpp中就属于一个中间件的作用，进行了承上启下，在AudioSystem.cpp中，有AudioFlinger相关的智能指针，如下图所示：

![AudioSystem中与AudioFlinger相关指针](https://github.com/kentanvictor/STUDY/blob/Image/JNI%E5%88%B0native%E7%9A%84AudioSystem%E8%B4%AF%E9%80%9A/AudioSystem%E4%B8%AD%E4%B8%8EAudioFlinger%E7%9B%B8%E5%85%B3%E6%8C%87%E9%92%88.png?raw=true)

+ gAudioFlinger作为AudioFlinger的代理端句柄可以输入输出设备进行相关控制。

![get_audio_policy_service函数](https://github.com/kentanvictor/STUDY/blob/Image/JNI%E5%88%B0native%E7%9A%84AudioSystem%E8%B4%AF%E9%80%9A/get_audio_policy_service%E5%87%BD%E6%95%B0.png?raw=true)

+ gAudioPolicyService作为AudioPolicyService服务代理端的句柄提可以对策略相关业务流程进行调度。