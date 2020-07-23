# JavaWeb初尝

+ 前言
> 第一次使用JavaWeb去实现后端的数据库的建立，非常兴奋！(主要原因在于老师同意我一个人写数据库课程设计【斜眼笑】)。话不多说，一起来看看JavaWeb的使用吧！

## 环境配置

### 准备工作

+ 安装JDK
+ web服务器：Tomcat
+ IDE集成开发工具
+ 数据库：(个人使用的是MySQL)

### 开始配置

#### JDK

+ 安装JDK
> + 在ORACLE官网可以进行JavaJDK的下载，网址为：[JavaJDK下载](http://www.oracle.com/technetwork/java/javase/downloads/index.htmll)
> + 下载后JDK的安装中会自带JRE的安装，一并安装即可。
> + 安装过程中用户可以自定义安装目录等信息。
+ 配置环境变量（Mac用户无需配置环境变量）
> + 安装完成后，右击"我的电脑"，点击"属性"
> + 选择"高级"选项卡，点击"环境变量"
> + 在"系统变量"中设置3项属性JAVA_HOME | PATH | CLASSPATH(大小写无所谓),若已存在则点击"编辑"，不存在则点击"新建"。
> + `变量设置`：
> + 变量名：JAVA_HOME
> + 变量值：C:\Program Files\Java\jdk1.7.0（这里是你JDK的安装路径，可以更换）
> + 变量名：CLASSPATH
> + 变量值：.;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;
> + 变量名：Path
> + 变量值：%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;

#### Tomcat

+ 下载和安装Tomcat服务器
> + 下载Tomcat安装程序包：[Tomcat安装包](http://tomcat.apache.org/)
> + Tomcat官方站点：[Tomcat官方站点](http://jakarta.apache.org)
> + 如图所示：

> ![](https://github.com/kentanvictor/STUDY/blob/Image/old_image/TomcatDownload.png?raw=true)

> + 点击【Download】跳转到如下图所示的下载页面

> ![](https://github.com/kentanvictor/STUDY/blob/Image/old_image/TomcatDownloadNext.png?raw=true)

> + tar.gz文件是Linux操作系统下的安装版本
> + exe文件是Windows系统下的安装版本
> + zip文件是Windows系统下的压缩版本
> + 压缩包解压
+ 启动和测试Tomcat服务器