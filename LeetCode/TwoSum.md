# 两数之和

## 题目

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

示例: 

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9

所以返回 [0, 1]

`Related Topics `：**数组** **哈希表**

所提供的函数：
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        
    }
}
```

***
我是一条正经的分割线
***

## 解题思路

通过Related Topics可以知道，在解决这道题时是需要用到哈希表的，那么我们要用到Java中hashmap的性质。

+ hashmap
  + HashMap采用key/value存储结构，每个key对应唯一的value，查询和修改的速度都很快，能达到O(1)的平均时间复杂度。它是非线程安全的，且不保证元素存储的顺序。
  + 在Java中，HashMap的实现采用了（数组 + 链表 + 红黑树）的复杂结构，数组的一个元素又称作桶。

通过上面对hashmap的解析可以看到，hashmap在搜索的过程中时间复杂度为O(1)，因此可以通过hashmap中`containkey方法`进行比较。

使用target对当前遍历的数字进行减法操作，后比较hashmap中的结果。如果存在值，获取key对应的下标。

## 最终结果


```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2){
            return new int[]{-1,-1};
        }

        int[] res = new int[]{-1,-1};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i < nums.length; i++){
            if (map.containsKey(target - nums[i])){
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                break;
            }
            map.put(nums[i],i);
        }

        return res;
    }
}
```