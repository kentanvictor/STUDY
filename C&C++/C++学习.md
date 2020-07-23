# 输入输出

+ 标准库（standard library）提供IO机制
+ iostream库：
  + istream（输入流）
  + ostream（输出流）
  
## 读取数量不定的输入数据

```c++

#include<iostream>
int main()
{
  int sum = 0, value = 0;
  //读取数据直到遇到文件尾，计算所有读入的值的和
	while(std::cin >> value)
	{
		sum += value;//等价于sum = sum + value
	}
	std::cout << "Sum is: " << sum << std::endl;
	return 0;
}

```

+ 对于Windows系统来说，输入文件结束符的方法是敲Ctrl+Z（按住Ctrl键的同时按Z键）
