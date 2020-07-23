# 数组的搜索与查找查询
+ 二分查找（Binary Search）:
  
`注：`
+ 需要被查找的数据必须是有序的。
+ 按照中间的元素来进行比较：
  + 若中间的元素大于的话，就直接将中间数后面的元素全部排除掉
  + 然后中间元素前面的元素再取中间的值进行比较

  * ……

  * 不断的取中间的值进行比较


+ 代码如下:

```java

public class ArraySearchText
{
    public static int BinarySearch(int [] Array,int value)//这里直接认为传入方法中的数组就已经是排序好了的
    {
      int low = 0;
      int high = array.length - 1;
      int middle;

      while(low <= high)
      {
        middle = (low + high) / 2;

        for(int i = 0 ; i< array.length; i++)
        {
          System.out.print(array[i]);
          if (i == middle) {
            System.out.print("#");
          }
          System.out.print(" ");
        }
        if (array[middle] == value ) {
          return middle;
        }
        if(value < array[middle] ){
          high = middle - 1;
        }
        if(value > array[middle]){
          low = middle + 1;
        }
      }
      return -1;
    }
    public static void main(String[] args) {
        int [] b = new int []{1,2,3,4,5,6,7,8,9};
        int index2 = BinarySearch(b,10);

        System.out.print(index2);
    }
}


```
