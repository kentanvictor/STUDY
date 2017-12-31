# 控件的使用
**在这里所要讲到的，就是快速开发中的控件的使用。**

+ QDateEdit
 + 控件效果为：

 ![](./image/dateEdit.png)

 + 例如，使用其`获取系统时间`（代码如下）：

 ```
 //获取系统日期
 QDateTime sysTime = QDateTime::currentDateTime();
 QStringList list = sysTime.toString("yyyy-MM-dd").split('-');
 ui.dateEdit->setDate(QDate(list[0].toInt(),list[1].toInt(), list[2].toInt()));
 ```
