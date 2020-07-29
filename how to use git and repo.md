# how to use git and repo

## git中常见的指令

### 新建一个仓库

+ git init

+ git clone \<url>

### 对仓库内内容的保存与判断

git中使用的判断条件是：`SHA-1校验和`

### 仓库中文件转换
![File Status Lifecycle](https://wx1.sbimg.cn/2020/07/27/DfbZn.png)

+ git add \<file>

![Untracked](https://wx1.sbimg.cn/2020/07/27/DfFuK.png)

使用add命令能够将untracked文件track，而通过reset命令能够将tracked转换成untracked

add之后再查看文件状态

![tracked](https://wx2.sbimg.cn/2020/07/27/DfI8G.png)

### git fetch and pull

![指令示意图](https://wx2.sbimg.cn/2020/07/28/PMDXd.png)

+ git fetch：将远程主机的最新内容拉取到本地，用户在检查了以后决定是否合并到工作本机分支当中

+ git pull：将远程主机的最新内容拉取下来直接合并。`即：git pull = git fetch + git merge`

### git rebase and git merge

+ git rebase

`rebase` 会把你当前分支的 commit 放到公共分支的最后面

![rebase示意图](https://wx1.sbimg.cn/2020/07/28/Pmgyn.png)

+ git merge

`merge` 会把公共分支和你当前的commit 合并在一起，形成一个新的 commit 提交

![merge示意图](https://wx2.sbimg.cn/2020/07/28/Pms6h.png)