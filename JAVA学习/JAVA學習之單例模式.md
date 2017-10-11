1、接口中聲明的方法都是抽象方法。並且接口中的方法都是public.
2、接口中也可以定義成員變量，但是需要賦值
接口中的成員變量都是static public and final的。就因為是final所以需要賦值
3、一個類不能既是final，又是abstract的，因為abstract的主要目的是定義一種約定，讓子類去實現這種約定，而final表示該類不能被繼承，這樣abstract希望該類可以被繼承而final又明確說明該類不能被繼承，兩者矛盾。因此，一個類不能既是final又是abstract的。
# 單例模式（Singleton）詳解
單例模式表示一個類只會生成唯一的一個對象。 （如果生成一個類，那麼構造方法一定會被調用，不管類有幾個構造方法，肯定有一個會被調用）--> 所以單例模式是從構造方法的方向入手，去建立唯一的一個類。
### 第一種的單例模式生成唯一實例的方式
```java
public class SingletonTest
{
   public static void main(String[] args)
  {
    Singleton singleton = Singleton.getInstance();
    Singleton singleton2 = Singleton.getInstance();
    System.out.println(singleton == singleton2);//用來判斷是否只生成了一個實例，如果返回true，那麼證明生成的兩個對象所指向的是同一個實例。
  }
}
//為保證能夠讓Main不能夠通過Singleton singleton = new Singleton();的方式來生成對象，那麼久需要在構造方法前面加上private，而構造出一個靜態方法，在類中去生成唯一的一個實例。
class Singleton
{
  private static Singleton singleton = new Singleton();//只有靜態方法才能夠調用靜態方法
  private Singleton()
  {

  }
  public static Singleton getInstance()
  {
      return singleton;
  }
}
```
### 第二種的單例模式生成唯一實例的方式
```java
public class SingletonTest
{
   public static void main(String[] args)
  {
    Singleton singleton = Singleton.getInstance();
    Singleton singleton2 = Singleton.getInstance();
    System.out.println(singleton == singleton2);//用來判斷是否只生成了一個實例，如果返回true，那麼證明生成的兩個對象所指向的是同一個實例。
  }
}

class Singleton
{
  private static Singleton singleton;//只有靜態方法才能夠調用靜態方法,並且這是一個引用類型的變量，默認初始為null
  private Singleton()
  {

  }
  public static Singleton getInstance()
  {
      if(singleton == null)
      {
            singleton = new Singleton();
      }
      return singleton;
  }
}
```
## 注
如果在多線程的環境下，更加推薦使用第一種的方式來寫單例模式。而第二種方式在多線程的情況下有可能就不是單例了。
