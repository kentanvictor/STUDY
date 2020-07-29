# Audio System

音频系统主要分为连个部分：`数据流`和`策略`。

+ `数据流：` **AudioTrack**,**AudioFlinger**就是数据流，描述了音频数据从数据源流向目的地的流程。

+ `策略：` **AudioPolicyService**,**AudioService**都是策略的范畴，管理及控制数据流的路径与呈现方式。

|    | Audio管理环节  |  Audio输出   | Audio输入  |
|  ----  | ----  |  ----  | ----  |
| Java层  | android.media.AudioSystem | android.media.AudioTrack  | android.media.AudioRecorder |
| 本地框架层  | AudioSystem | AudioTrack  | AudioRecorder |
| AudioFlinger  | IAudioFlinger | IAudioTrack  | IAudioRecorder |
| 硬件抽象层  | AudioHardwareInterface | AudioSteamOut  | AudioStreamIn |








[音频系统解析](https://blog.csdn.net/renshuguo123723/category_8545211.html)