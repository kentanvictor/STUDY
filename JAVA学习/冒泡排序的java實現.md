## 冒泡排序的JAVA实现
* 冒泡排序
```java
public class BUbbleSortTest
{
  /*
  一共两层循环
  最外层循环是进行走到哪里了，进行一个循环。从数组两头一直走到中间的元素
  最里面的for循环是用來判断相邻的两个数的大小，进行互换
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
## 快速排序算法java实现
* 快速排序
> **整体的实现逻辑是基于冒泡排序,直接可以将关键字较大的记录从前面直接移动到后面,关键字较小的记录从后面直接移动到前面,从而减少了总的比较次数与移动次数.**
>
>![](../image/quick.png)
* 代碼如下:
```java
public class QuickSort {
    public static void sort(int a[], int low, int hight) {
        int i, j, index;
        if (low > hight) {
            return;
        }
        i = low;
        j = hight;
        index = a[i]; // 用子表的第一个记录做基准
        while (i < j) { // 从表的两端交替向中间扫描
            while (i < j && a[j] >= index)
                j--;
            if (i < j)
                a[i++] = a[j];// 用比基准小的记录替换低位记录
            while (i < j && a[i] < index)
                i++;
            if (i < j) // 用比基准大的记录替换高位记录
                a[j--] = a[i];
        }
        a[i] = index;// 将基准数值替换回 a[i]
        sort(a, low, i - 1); // 对低子表进行递归排序
        sort(a, i + 1, hight); // 对高子表进行递归排序

    }

    public static void quickSort(int a[]) {
        sort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {

        int a[] = { 49, 38, 65, 97, 76, 13, 27, 34 };
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }
}
```
