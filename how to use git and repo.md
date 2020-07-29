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

+ git reset

git reset会把指定的提交的所有修改回滚，并同时生成一个新的提交。

git reset 会修改HEAD到指定的状态，用法为：

```
git reset [options] <commit>
```

这条命令会使HEAD提向指定的Commit，一般会用到3个参数，这3个参数会影响到工作区与暂存区中的修改：

+ --soft: 只改变HEAD的State，不更改工作区与暂存区的内容
+ --mixed(默认): 撤销暂存区的修改，暂存区的修改会转移到工作区
+ --hard: 撤销工作区与暂存区的修改

### git rebase and git merge

+ git rebase

`rebase` 会把你当前分支的 commit 放到公共分支的最后面

![rebase示意图](https://wx1.sbimg.cn/2020/07/28/Pmgyn.png)

+ git merge

`merge` 会把公共分支和你当前的commit 合并在一起，形成一个新的 commit 提交

![merge示意图](https://wx2.sbimg.cn/2020/07/28/Pms6h.png)

### 与人协作

#### cherry-pick

当与别人和作开发时，会向别人贡献代码或者接收别人贡献的代码，有时候可能不想完全merge别人贡献的代码，只想要其中的某一个提交，这时就可以使用cherry-pick了。

```
git cherry-pick <commit-id>
```

#### 新建分支

```
git branch <branch-name>
```

有时需要在新建分支后直接切换到新建的分支，可以直接用checkout的-b选项

```
git checkout -b <branch-name>
```

#### 删除分支

```
git branch -d <branch-name>
```

如果在指定的分支有一些unmerged的提交，删除分支会失败，这里可以使用-D参数强制删除分支。

```
git branch -D <branch-name>
```

#### 检出分支或提交

检出某一分支或某一提交是同一个命令

```
git checkout <branch-name> | <commit>
```

