# javaIO

标签： javaIO

---

**Contents**
- [什么是IO流](#流的概念和作用)
  - [IO流的分类](#IO流的分类)
  - [字符流和字节流](#字符流和字节流)
- [文件字节、字符IO](#文件字节、字符IO)
  - [文件字节IO流](#文件字节IO流)
  - [文件字符IO流](#文件字符IO流)

---

# Java流图结构

![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/JavaIO.jpg)

# 流的概念和作用

>流是一组有顺序的，有起点和终点的字节集合，是对数据传输的总称或抽象。即数据在两设备间的传输称为流，流的本质是数据传输，根据数据传输特性将流抽象为各种类，方便更直观的进行数据操作。

## IO流的分类

 + 根据处理数据类型的不同可以分为：字符流和字节流
 + 根据数据流向不同分为：输入流和输出流

## 字符流和字节流

> 字符流的由来： 因为数据编码的不同，而有了对字符进行高效操作的流对象。本质其实就是基于字节流读取时，去查了指定的码表。 字节流和字符流的区别：
>
> + 读写单位不同：字节流以字节（8bit）为单位，字符流以字符为单位，根据码表映射字符，一次可能读多个字节。
>
> + 处理对象不同：字节流能处理所有类型的数据（如图片、avi等），而字符流只能处理字符类型的数据。
>
> + 字节流：一次读入或读出是8位二进制。
>
> + 字符流：一次读入或读出是16位二进制。
>
> **设备上的数据无论是图片或者视频，文字，它们都以二进制存储的。二进制的最终都是以一个8位为数据单元进行体现，所以计算机中的最小数据单元就是字节。意味着，字节流可以处理设备上的所有数据，所以字节流一样可以处理字符数据。**
>
> **`结论：只要是处理纯文本数据，就优先考虑使用字符流。 除此之外都使用字节流。`**
>
>
>


# 文件字节、字符IO
> Java程序可能经常需要获取磁盘上文件的有关信息或在磁盘上创建新的文件等，这就需要用到`File类`。
>
> ### 创建一个File对象的构造方法有3个：
> + File(String filename);
> + File(String directoryPath,String filename);
> + File(File dir,String filename);
>
> *filename--文件名字；directoryPath--文件路径；dir--一个目录；`使用File(String filename)创建文件时，该文件被认为与当前应用程序在同一个目录中。`*
>
### **文件的属性：**
<pre>
public String getName();            //获取文件的名字
public boolean canRead();           //判断文件是否是可读的
public boolean canWrite();          //判断文件是否可被写入
public boolean exists();            //判断文件是否存在
public long length();               //获取文件的长度（单位是字节）
public String getAbsolutePath();    //获取文件的绝对路径
public String getParent();          //获取文件的父目录
public boolean isFile();            //判断文件是否是一个普通文件，而不是目录
public boolean isDirectory();       //判断文件是否是一个目录
public boolean isHidden();          //判断文件是否是隐藏文件
public long lastModified();         //获取文件最后修改的时间（时间是从1970年午夜至文件最后修改时刻的毫秒数）
</pre>
**例子：使用上述方法获取某些文件的信息：**

```java
public class FileTest {
    public static void main(String[] args) {
        File f = new File("F:\\IDEA_code\\IOTest","newIO.txt");
        System.out.println(f.getName() + "是可读的吗：" + f.canRead());
        System.out.println(f.getName() + "的长度：" + f.length());
        System.out.println(f.getName() + "的绝对路径：" + f.getAbsolutePath());

        File newFile = new File("new.txt");
        System.out.println("在当前目录下创建新文件：" + newFile.getName());
        if (!newFile.exists()){
            try {
                newFile.createNewFile();    //如果没有new.txt则创建一个
                System.out.println("创建成功！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### **目录：**
>
+ 创建目录
>File对象调用方法public boolean mkdir()创建一个目录，如果创建成功返回true，否则返回false（如果该目录已经存在将返回false）。
+ 列出目录中的文件
>如果File对象是一个目录，那么该对象调用下述方法列出目录下的文件和目录。
<pre>
public String[] list();             //用字符串形式返回目录下的全部文件
public File[] listFiles();          //用File对象形式返回目录下的全部文件
</pre>
>有时候需要列出目录下指定类型的文件，比如.java、.txt等扩展名的文件。可以使用File类的下述两个方法，列出指定类型的文件：
<pre>
public String[] list(FilenameFilter obj);           //该方法用字符串形式返回目录下的指定类型的所有文件
public File[] listFiles(FilenameFilter obj);        //该方法用File对象形式返回目录下的指定类型的所有文件
</pre>
>上述两个方法的参数FIlenameFilter是一个接口，该接口有个方法：
<pre>
public boolean accept(File dir,String name);
</pre>
>File对象dirFile调用list方法时，需向该方法传递一个实现FilenameFilter接口的对象，list方法执行时，参数obj不断回调接口方法accept(File dir,String name)，该方法中的参数dir为调用list的当前目录dirFile，参数name被实例化为dirFile，参数name被实例化为dirFile目录中的一个文件名，当接口方法返回true时，list方法就将名字为name的文件存放到返回的数组中。
>
**例子：列出当前目录下（应用程序所在的目录）下全部.java文件的名字：**
```java
public class DirFileTest {
    public static void main(String[] args) {
        File dirFile = new File("F:\\IDEA_code\\Singleton_pattern\\src\\com\\dell\\example\\IODemo\\FileTest");
        FileAccept fileAccept = new FileAccept();
        fileAccept.setExtendName("java");
        String fileName[] = dirFile.list(fileAccept);
        for (String name : fileName){
            System.out.println(name);
        }
    }
}
```

```java
public class FileAccept implements FilenameFilter {
    private String extendName;
    public void setExtendName(String s){
        extendName = "." + s;
    }
    @Override
    public boolean accept(File dir, String name) {  //重写接口中的方法
        return name.endsWith(extendName);
    }
}
```

## **文件字节IO流**

### 文件字节输入流
`使用输入流通常包括4个基本步骤：`
+ 设定输入流的源
+ 创建指向源的输入流
+ 让输入流读取源中的数据
+ 关闭输入流

如果对文件读取需求比较简单，那么可以使用`FileInputStream类（文件字节输入流）`，该类是InputStream类的子类（以字节为单位读取文件），该类的实例方法都是从InputStream继承来的。

+ 构造方法：

    可以使用FileInputStream类的下列构造方法创建指向文件的输入流。
    ```java
    FileInputStream(String name);
    FileInputStream(File file);
    ```
    第一个构造方法使用给定的文件名name创建FileInputStream流，第二个构造方法使用File对象创建FileInputStream流。参数name和file指定的文件称为输入流的源。

    **注意：** FileInputStream输入流打开一个到达文件的通道。当创建输入流的时候，可能会出现异常。
    ```java
    try{
        FileInputStream in = new FileInputStream("hello.txt");//创建指向文件hello.txt的输入流
    }
    catch(IOException e){
        System.out.println("File read error:" + e);
    }
    ```
    或
    ```java
    File f = new File("hello.txt"); //指定输入流的源
    try{
        FileInputStream in = new FileInputStream(f);    //创建指向源的输入流
    }
    catch(IOEception e){
        System.out.println("File read error:" + e);
    }
    ```
+ 使用输入流读取字节

    文件字节流可以调用从父类继承的`read方法`顺序地读取文件，只要不关闭流，每次调用read方法就顺序地读取文件中的其余内容，直到文件的末尾或文件字节输入流被关闭。
    
    字节输入流的read方法以字节为单位读取源中的数据。
    <pre>
    int read()                              //输入流调用该方法从源中读取单个字节的数据，该方法返回字节值（0~255之间的一个整数），如果未读出字节就返回-1
    int read(byte b[])                      //输入流调用该方法从源中试图读取b.length个字节到字节数组中，返回实际读取的字节数目，如果到达文件末尾则返回-1
    int read(byte b[],int off,int len)      //输入流调用该方法从源中试图读取b.length个字节到字节数组中，返回实际读取的字节数目，如果到达文件末尾则返回-1，参数off指定从字节数组的某个位置开始存放读取的数据
    </pre>
+ 关闭流
    输入流都提供了关闭方法`close()`，尽管程序结束时会自动关闭所打开的流，但是当程序使用完流后，显式地关闭任何打开的流仍是一个好习惯。

**例子：使用文件字节流读文件内容：**
```java
public class InputTest {
    public static void main(String[] args) {
        int n = -1;
        byte[] a = new byte[100];
        try {
            File f = new File("F:\\IDEA_code\\Singleton_pattern\\src\\com\\dell\\example\\IODemo\\InputStreamTest\\InputTest.java");
            InputStream in = new FileInputStream(f);
            while ((n = in.read(a,0,100)) != -1){
                String s = new String(a,0,n);
                System.out.println(s);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
### 文件字节输出流
`使用输出流通常包括4个基本步骤：`
+ 给出输出流的目的地
+ 创建指向目的地的输出流
+ 让输出流把数据写入到目的地
+ 关闭输出流

如果对文件写入需求比较简单，那么可以使用`FileOutputStream类（文件字节输出流）`，该类是OutputStream类的子类（以字节为单位写入文件），该类的实例方法都是从OutputStream继承来的。

+ 构造方法：

    可以使用FileOutputStream类的下列具有刷新功能的构造方法创建指向文件的输出流。
    ```java
    FileOutputStream(String name);
    FileOutputStream(File file);
    ```
    第一个构造方法使用给定的文件名name创建FileOutputStream流，第二个构造方法使用File对象创建FileOutputStream流。参数name和file指定的文件称为输出流的目的地。

    **注意：** FileOutputStream输出流指向的文件不存在，java就会创建该文件，如果指向的文件是已存在的文件，输入流会刷新该文件（使文件长度为0）。创建输出流可能会出现异常。
    ```java
    try{
        FileOutputStream out = new FileOutputStream("androidlab.txt");//创建指向文件androidlab.txt的输出流
    }
    catch(IOException e){
        System.out.println("File read error:" + e);
    }
    ```
    或
    ```java
    File f = new File("androidlab.txt");                //指定输出流的目的地
    try{
        FileOutputStream in = new FileOutputStream(f);    //创建指向目的地的输出流
    }
    catch(IOEception e){
        System.out.println("File read error:" + e);
    }
    ```
+ 使用输出流写字节

    文件字节流可以调用从父类继承的`write方法`顺序地写文件，只要不关闭流，每次调用write方法就顺序地写文件，直到流被关闭。
    
    字节输出流的write方法以字节为单位向目的地写入数据。
    <pre>
    void write(int n)                       //输出流调用该方法向目的地写入单个字节
    void write(byte b[])                     //输出流调用该方法向目的地写入一个字节数组
    void write(byte b[],int off,int len)     //给定字节数组中起始于偏移量off处取len个字节写入目的地
    void close()                             //关闭输出流
    </pre>
+ 关闭流
    需要注意的是，在操作系统把程序所写到输出流上的那些字节保存到磁盘上之前，有时被存放在缓冲区中，通过调用`close()`方法，可以保证操作系统把缓冲区的内容写到它的目的地。

**例子：使用文件字节流写入文件：** 使用具有刷新功能的构造方法指向文件android.txt的输出流，并向里面写入“Android”，然后再选择使用不刷新文件的构造方法指向android.txt，并向文件中追加“Lab”。
```java
public class OutputTest {
    public static void main(String[] args) {
        byte[] a = "Android".getBytes();
        byte[] b = "Lab".getBytes();
        File newFile = new File("android.txt");     //输出目的地
        try {
            OutputStream out = new FileOutputStream(newFile);   //指向目的地的输出流
            System.out.println(newFile.getName() + "的大小:" + newFile.length() + "字节");
            out.write(a);   //向目的地写入数据
            out.close();
            out = new FileOutputStream(newFile,true);   //准备向文件尾追加内容
            System.out.println(newFile.getName() + "的大小:" + newFile.length() + "字节");
            out.write(b,0,b.length);
            System.out.println(newFile.getName() + "的大小:" + newFile.length() + "字节");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## **文件字符IO流**
文件字节输入、输出流的read和write方法使用字节数组读写数据，即以字节为单位处理数据。因此字节流不能很好地操作Unicode字符。**一个汉字在文件中占两个字节，如果使用字节流读取不当会出现“乱码”现象。**
<pre>
    字节            ------          字符
FileInputStream     ------      FileReader
FileOutputStream    ------      FileWriter
</pre>
FileReader和FileWriter分别是Reader和Writer的子类，其构造方法分别是：
<pre>
FileReader(String filename);    FileReader(File filename);
FileWriter(String filename);    FileWriter(File filename);
FileWriter(String filename,boolean append);
FileWriter(File filename,boolean append);
</pre>
**字符输入流和输出流的`read`和`write`方法使用字符数组读写数据，即以字符为基本单位处理数据。**

+ **例子：用文件字符输入、输出流将文件“NotHelloWorld.txt”中的内容尾加到“AndroidLab.txt”中**
```java
public class RWTest {
    public static void main(String[] args) {
        File sourceFile = new File("F:\\IDEA_code\\IOTest\\NotHelloWorld.txt");//读取的文件
        File targetFile = new File("F:\\IDEA_code\\IOTest\\AndroidLab.txt");//写入的文件
        char c[] = new char[100];
        try{
            Writer out = new FileWriter(targetFile,true);   //指向目的地的输出流
            Reader in = new FileReader(sourceFile);                 //指向源的输入流
            int n = -1;
            while ((n = in.read(c)) != -1){
                out.write(c,0,n);
            }
            out.flush();
            out.close();
        }catch (IOException e){
            System.out.println("Error " + e);
        }
    }
}
```
