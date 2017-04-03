# Git
## What is Git?
* Git is the most advanced distributed version control system in the world.
## What is the different between the SVN and the Git?
* SVN is a centralized version control system which version library  centralized packed in the central server.So if you need to do your work,you must go to the central server to download the latest version.When you finished your work,you have to push on the central server.It's so inconvenient.Actually the centralized version control system must accessible networking.
* Git is a distributed version control system,which advantaged is if it has not a central version,the LapTop is a rounded version library.because of this, you can work without using network.

## there are some useful command lines :
* mkdir: XX (Create an empty directory)/*XX refer to directory name*/
* pwd: (Show the path to the current directory)
* git: init (Change the current directory to a managed  Git repository,create a hidden file)
* git add XX (Add  XX file to working-storage section)
* git commit -m "XX" (Add a comment to a file)/*XX is the comment*/
* git status (View warehouse status)
* git log (View history)
* git diff XX (View XX file what are modified)
* git rm XX (Delete XX file)
* cat XX (View file contents)
* git checkout -b A  (Create a A branch and switch to A branch)
* git checkout master (back to master branch)
* git clone HTTPS (clone from online warehouse)
* git branch name (create a branch)
* git branch -d A (Delete A branch)
* git push origin master (push master branch to online warehouse)
# 如何使用Git Bash
## 新建
當需要上傳一個項目到Github時，這個時候你需要在自己的Github上新建一個項目
New repository
然後在右下角有一個Clone or download
點擊之後有一個Clone with HTTPS
Use Git or checkout with SVN using the web URL.
複製地址之後
在需要上傳的項目文件中
右鍵，點擊Git Bash Here之後
先輸入 git clone + 地址
會出現一個項目名稱的文件夾
裡面有隱藏文件.git與ReadMe.md
將具有項目名稱的文件夾中的全部內容剪切到需要上傳的項目的根目錄下
之後在Git Bash中輸入
git add .
然後在本地執行提交操作
git commit -m "//在這裡面輸入你的內容，比如：First commit."
最後將提交的內容同步到遠程版本庫，也就是GitHub上面：
git push origin master
## 更新
當需要更新已經上傳的項目時
直接在已經寫好的更新過的項目中
右鍵，點擊Git Bash Here
然後在命令行中輸入
git pull + 地址
上傳跟新過之後，需要在命令行中輸入
git add .
之後的步驟又是重複著之前的了
然後在本地執行提交操作
git commit -m "//在這裡面輸入你的內容，比如：First commit."
最後將提交的內容同步到遠程版本庫，也就是Github上面：
git push origin master
