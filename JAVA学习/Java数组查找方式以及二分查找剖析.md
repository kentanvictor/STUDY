# 數組的搜索與查找查詢
* 二分查找（Binary Search）:
#### 注：
 * 需要被查找的數據必須是有序的。
 * 按照中間的元素來進行比較：
  * 若中間的元素大於的話，就直接將中間數後面的元素全部排除掉
  * 然後中間元素前面的元素再取中間的值進行比較

  ……

  * 不斷的取中間的值進行比較


* 代碼如下:

```java

public class ArraySearchText
{
    public static int BinarySearch(int [] Array,int value)//這裡直接認為傳入方法中的數組就已經是排序好了的
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
