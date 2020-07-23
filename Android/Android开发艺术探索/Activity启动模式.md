# Activity 启动模式

默认情况下，多次启动同一个activity时，系统会创建多个实例并放进任务栈中，当点击back键后，任务栈通过“后进先出”的栈结构出栈，最终清空栈。

## 四种启动模式

### standard：标准模式

### singleTop：栈顶复用模式

### singleTask：栈内复用模式

### singleInstance：单实例模式