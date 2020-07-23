# 正则表达式

正则表达式是一种字符串匹配的一种模式，可以用于检测一个字符串是否包含某串、将匹配的字符串进行替换或者从一个字符串中提取符合某个条件的的子串等等

`注：`在C++中使用正则表达式进行字符串操作的时候，需要添加头文件 **```#include<regex>```** 

## 正则表达式中匹配的字符

### 特殊字符

| 特别字符 | 描述 |
|  ----  | ----  |
| $  | 匹配输入字符串的结尾位置 |
| ( )  | 标记一个子表达式的开始和结束位置 |
| * | 匹配前面的子表达式零次或多次 |
| + | 匹配前面的子表达式一次或多次 |


### 限定符

| 字符 | 描述 |
|  ----  | ----  |
| * | 匹配前面的子表达式零次或多次。例如，zo* 能匹配 "z" 以及 "zoo"。* 等价于{0,} |
| + | 匹配前面的子表达式一次或多次。例如，'zo+' 能匹配 "zo" 以及 "zoo"，但不能匹配 "z"。+ 等价于 {1,} |
| ? | 匹配前面的子表达式零次或一次。例如，"do(es)?" 可以匹配 "do" 、 "does" 中的 "does" 、 "doxy" 中的 "do" 。? 等价于 {0,1} |
| {n} | n 是一个非负整数。匹配确定的 n 次。例如，'o{2}' 不能匹配 "Bob" 中的 'o'，但是能匹配 "food" 中的两个 o |
| {n,} | n 是一个非负整数。至少匹配n 次。例如，'o{2,}' 不能匹配 "Bob" 中的 'o'，但能匹配 "foooood" 中的所有 o。'o{1,}' 等价于 'o+'。'o{0,}' 则等价于 'o*' |
| {n,m} | m 和 n 均为非负整数，其中n <= m。最少匹配 n 次且最多匹配 m 次。例如，"o{1,3}" 将匹配 "fooooood" 中的前三个 o。'o{0,1}' 等价于 'o?'。请注意在逗号和两个数之间不能有空格 |

## regex_match
+ match为全文匹配，要求整个字符串符合匹配规则。
+ 返回值为bool值，匹配成功返回1。
  
```C++
#include<iosteam>
#include<regrex>
using namespace std;
int main(){
    cout << regex_match("123", regex("\\d")) << endl;		//结果为0
    cout << regex_match("123", regex("\\d+")) << endl;		//结果为1
    return 0;
}
``` 

+ `\d`：匹配一个数字字符
+ `+` ：匹配一次或多次

regex_match函数还有一种重载方式：regex_match(str, result, pattern)。示例如下所示：
```C++
#include<iosteam>
#include<regrex>
using namespace std;
int main(){
    string str = "Hello_2018";
    smatch result;
    regex pattern("(.{5})_(\\d{4})");	//匹配5个任意单字符 + 下划线 + 4个数字

    if (regex_match(str, result, pattern))
    {
	    cout << result[0] << endl;		//完整匹配结果，Hello_2018
	    cout << result[1] << endl;		//第一组匹配的数据，Hello
	    cout << result[2] << endl;		//第二组匹配的数据，2018
	    cout<<"结果显示形式2"<<endl;
	    cout<< result.str() << endl;	//完整结果，Hello_2018
	    cout<< result.str(1) << endl;	//第一组匹配的数据，Hello
	    cout << result.str(2) << endl;	//第二组匹配的数据，2018
    }

    //遍历结果
    for (int i = 0; i < result.size(); ++i)
    {
	    cout << result[i] << endl;
    }
    return 0;
}

```

## regex_search

+ search是搜索匹配，即搜索字符串中存在符合规则的子字符串
具体例子如下所示：
```C++
#include<iosteam>
#include<regrex>
using namespace std;
int main(){
    string str = "Hello 2018, Bye 2017";
    smatch result;
    regex pattern("\\d{4}");	//匹配四个数字

    //迭代器声明
    string::const_iterator iterStart = str.begin();
    string::const_iterator iterEnd = str.end();
    string temp;
    while (regex_search(iterStart, iterEnd, result, pattern))
    {
	    temp = result[0];
	    cout << temp << " ";
	    iterStart = result[0].second;	//更新搜索起始位置,搜索剩下的字符串
    }
    return 0;
}


输出结果：2018 2017
```
## regex_replace

+ replace是替换匹配，即可以将符合匹配规则的子字符串替换为其他字符串
```C++
#include<iosteam>
#include<regrex>
using namespace std;
int main(){
    string str = "Hello_2018!";
    regex pattern("Hello");	
    cout << regex_replace(str, pattern, "") << endl;	//输出：_2018，将Hello替换为""
    cout << regex_replace(str, pattern, "Hi") << endl;	//输出：Hi_2018，将Hello替换为Hi
    return 0;
}

```

+ 除了直接替换以外，还有可以用来调整字符串内容（缩短、顺序等）

```C++
#include<iosteam>
#include<regrex>
using namespace std;
int main(){
    string str = "Hello_2018!";	
    regex pattern2("(.{3})(.{2})_(\\d{4})");				//匹配3个任意字符+2个任意字符+下划线+4个数字
    cout << regex_replace(str, pattern2, "$1$3") << endl;	//输出：Hel2018，将字符串替换为第一个和第三个表达式匹配的内容
    cout << regex_replace(str, pattern2, "$1$3$2") << endl;	//输出：Hel2018lo，交换位置顺序
    return 0;
}
```
`注：` $n用于表示第n组匹配数据
