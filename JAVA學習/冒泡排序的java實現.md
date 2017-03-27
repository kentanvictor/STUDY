# 冒泡排序的JAVA實現
* 冒泡排序
```java
public class BUbbleSortTest
{
  /*
  一共兩層循環
  最外層循環是進行走到哪裡了，進行一個循環。從數組兩頭一直走到中間的元素
  最裡面的for循環是用來判斷相鄰的兩兩個數的大小，進行互換
  */
  public static void BUbbleSort(int [] array)
  {
    for (int i=0;i<array.length-1;i++ )
    {
      for (int j=0;j<array.length-i-1;j++ )
      {
        if (array[j]>array[j+1])
        {
          int temp = array[j];
          array[j]= array[j+1];
          array[j+1]=temp;
        }
      }
      System.out.println("第"+(i+1)+"趟排序");
      for (int k = 0;i<array.length;i++)
      {
        System.out.println(array[i] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
        int [] array = {4,7,8,9,3,2};
        BUbbleSort(array);
  }
}
```
