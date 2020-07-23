# 基于双目摄像头的障碍物检测

>前言：关于双目摄像头的障碍物检测以及基于OpenCV的障碍物检测在CSDN以及博客园上都有几篇相关的文章。然而，相当一部分的关于障碍物检测的文章多偏向于理论，而有实践的文章却少之又少。在这里，我将按照我从网上学习到的例子进行整合并加入了我自己的理解。希望能为大家在障碍物检测方面起到一定的参考作用。

>**特别鸣谢：<br/>[亦轩Dhc的博客](http://www.cnblogs.com/daihengchen/p/5686272.html)<br/>[琪其齐奇旗棋的CSDN](https://blog.csdn.net/zhouqianq/article/details/78580173)<br/>[_寒潭雁影的CSDN](https://blog.csdn.net/weixinhum/article/details/78161567)**

## 下面开始进入正题啦！
### 前期准备：
+ 这里我使用的是一个双目摄像头进行的障碍物检测。该双目摄像头如图所示：
![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/camera.png)

### 前期知识点：
+ 双目摄像头的标定
+ 使用OpenCV获取图片
+ 双目校正
+ 立体匹配

#### 双目摄像头的标定的前期知识点：
>在这里，我使用的标定方法是**张正友标定法**。
+ 首先你需要明确，对于一个摄像头来说，分为*内参*和*外参*
+ + 内参分别有五个：
+ + 摄像头拍摄到的物体和实际物体在x,y轴上的映射关系（两个参数）。
+ + 摄像头中心和图像中心的偏移关系（两个参数）。
+ + 摄像头和镜头安装非完全垂直，存在一个角度的偏差。（一个参数）。
+ + 外参有六个：
+ + 分别是x，y，z方向上的平移和旋转。
>只要有了上面的两种参数，我们基本上就知道了摄像头拍摄到的图像和现实事物的对应关系了。然而， 摄像头拍摄图像与现实事物中还是会有“**畸变**”上的误差。这是由于镜头质量等原因导致的2D点的偏移。

***因此，我们使用张正友标定法对摄像头进行内外参数以及畸变参数的标定。***

## 张正友标定法
>前言：张正友标定又称“张氏标定”。是张正友教授在1998年提出的单平面棋盘格的摄像机标定法。
+ 前期准备：
+ + 标定板
+ + >需要自定准备一个标定板，这标定板的长相大致如下：

![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/Calibration_board.jpg)
+ + 实景拍摄的图像如下：

![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/True_Calibration_board.jpg)
+ 这种标定板有两种方式可以得到：
+ + 第一种：直接从opencv官网上下载：[官网标定板下载](https://docs.opencv.org/2.4/_downloads/pattern.png)
+ + 第二种：使用Python+OpenCV生成棋盘格图片：
>
>```python
>import cv2 
>import numpy as np
>
>width = 350
>height = 500
>length = 50
>
>image = np.zeros((width,height),dtype = np.uint8)
>print(image.shape[0],image.shape[1])
>
>for j in range(height):
>    for i in range(width):
>        if((int)(i/length) + (int)(j/length))%2:
>            image[i,j] = 255;
>cv2.imwrite("pic/chess.jpg",image)
>cv2.imshow("chess",image)
>cv2.waitKey(0)
>```
**所生成的图片和在OpenCV官网下载得到的图片是一样的规格的。**

### OpenCV下的张正友标定法
+ 角点提取

**使用的函数1：** ***bool findChessboardCorners(InputArray image,Size atternSIze,OutputArray corners,int flags=CALIB_CB_ADAPTIVE_THRESH+CALIB_CB_NORMALIZE_IMAGE);***
>*作用：用于提取标定板的内角点，也就是提取示例图中中每四个黑白格中间的那些角点。*
> + 参数解析：
> + + image：拍摄到的棋盘图像;
> + + patternSize：每个棋盘图上的内角点数。（如果是上图的话，内角点数Size(9,6),即：每行9个角点，每列9个角点）;
> + + corners：用于存储检测到的内角点的图像坐标位置。（一般用Point2f的向量来表示）;
> + + flags：用于定义棋盘图上的内角点查找的不同处理方式。
> + + **返回值类型为bool，用以返回是否从图中找到角点。**

**使用函数2：** ***bool find4QuadCornerSubpix(InputArray img,InputOutputArray corners,Size region_size);***
>*作用：用于在初步提取的角点信息上进一步提取亚像素信息，降低相机标定偏差，该方法专门用来获取棋盘图上内角点的精确位置。（**有时候也会使用cornerSubPix函数**）*
> + 参数解析：
> + + img：输入的Mat矩阵，最好是8位灰度图像;
> + + corners：初始的角点坐标向量，同时作为亚像素坐标位置的输出，所以需要的是浮点型数据，一般用Pointf2f/Point2d的向量来表示。*即输入上面findChessboardCorners函数的第三个参数*;
> + + region_size：角点搜索窗口的尺寸;
> + *在一般情况下，其实我们用得较多的是cornerSubPix，但是我们这里用的是棋盘格，而 
find4QuadCornerSubpix是专门用来获取棋盘图上内角点的精确位置的。*

**使用函数3：** ***drawChessboardCorners( InputOutputArray image, Size patternSize, InputArray corners, bool patternWasFound);***
>*作用：在棋盘上绘制找到的内角点。*
> + 参数解析：
> + + image：8位灰度或者彩色图像。
> + + patternSize：每张标定棋盘上内角点的行列数,即findChessboardCorners的第二个参数;
> + + corners：角点坐标向量，可用find4QuadCornerSubpix函数的第二个参数输出做输入;
> + + patternWasFound：标志位，用来指示定义的棋盘内角点是否被完整的探测到，true表示被完整的探测到，函数会用直线依次连接所有的内角点，作为一个整体，false表示有未被探测到的内角点，这时候函数会以（红色）圆圈标记处检测到的内角点;
+ + 总查找角点的实力代码大致如下：
```C++
Mat imageInput = imread("chess.jpg");
Size board_size = Size(9, 6);//标定板上每行、列的角点数
vector<Point2f> image_points_buf;//缓存每幅图像上检测到的角点
/*提取角点*/
if (!findChessboardCorners(imageInput, board_size, image_points_buf))
{
    cout << "can not find chessboard corners!\n"; //找不到角点  
    return;
}
else
{
    Mat view_gray;
    cvtColor(imageInput, view_gray, CV_RGB2GRAY);
    /*亚像素精确化*/
    find4QuadCornerSubpix(view_gray, image_points_buf, Size(5, 5)); //对粗提取的角点进行精确化  
    drawChessboardCorners(view_gray, board_size, image_points_buf, true); //用于在图片中标记角点  
    imshow("Camera Calibration", view_gray);//显示图片  
    waitKey(0);     
}
```

+ 相机标定
>利用上面获取到的图像角点（理论上需要三张图像，即三组数据，事实上以10~20张为宜，因为这样误差会比较小），便可以用calibrateCamera函数做摄像头标定，计算出摄像头的内参、外参和畸变参数了。

**使用函数1：** ***double calibrateCamera(InputArrays objectPoints,InputAttaysOfArrays imagePoints,Size imageSize,CV_OUT InputOutputArray cameraMatrix,CV_OUT InputOutputArray distCoeffs,OutputArrayOfArrays rvecs,OutputArrayOfArrays tvecs,int flags=0,TermCriteria criteria = TermCriteria(TermCriteria::COUNT+TermCriteria::EPS,30,DBL_EPSILON));***
> + 参数解析：
> + + objectPoints：为世界坐标系中的三维点。在使用时，应该输入一个三维坐标点的向量集合。一般我们假定标定板放在z=0的平面上，然后依据棋盘上单个黑白方块的大小（也可以直接都取10，如果不需要很准确的映射到现实事物的话）可以计算出每个内角点的世界坐标。
> + + imagePoints：为每一个内角点对应的图像坐标点。也即是上面求得的各张图像的角点集合。
> + + imageSize：为图像的像素尺寸大小，在计算相机的内参和畸变矩阵的时候需要用到的该参数。
> + + cameraMatrix：为相机的内参矩阵。输入一个Mat cameraMatrix即可，如Mat cameraMatrix=Mat(3,3,CV_32FC1,Scalar::all(0))。
> + + distCoeffs：为畸变矩阵。输入一个Mat distCoffs=Mat(1,5,CV_32FC1,Scalar::all(0));即可。
> + + rvecs：旋转向量。应该输入一个Mat类型的vector，即vector<Mat>rvecs;
> + + tvecs：位移向量。和rvecs一样，应该为vector<Mat> tvecs;
> + + flags：标定时所采用的算法。flags有如下几个参数（直接不写则依据下面参数描述中没设参数的情况进行）：
> + + + CV_CALIB_USE_INTRINSIC_GUESS：使用该参数时，在cameraMatrix矩阵中应该有fx,fy,u0,v0的估计值。否则的话，将初始化(u0,v0）图像的中心点，使用最小二乘估算出fx，fy。
> + + + CV_CALIB_FIX_PRINCIPAL_POINT：在进行优化时会固定光轴点。当CV_CALIB_USE_INTRINSIC_GUESS参数被设置，光轴点将保持在中心或者某个输入的值。
> + + + CV_CALIB_FIX_ASPECT_RATIO：固定fx/fy的比值，只将fy作为可变量，进行优化计算。当CV_CALIB_USE_INTRINSIC_GUESS没有被设置，fx和fy将会被忽略。只有fx/fy的比值在计算中会被用到。
> + + + CV_CALIB_ZERO_TANGENT_DIST：设定切向畸变参数（p1,p2）为零。
> + + + CV_CALIB_FIX_K1,…,CV_CALIB_FIX_K6：对应的径向畸变在优化中保持不变。
> + + + CV_CALIB_RATIONAL_MODEL：计算k4，k5，k6三个畸变参数。如果没有设置，则只计算其它5个畸变参数。
> + + criteria：最优迭代终止条件设定。
>
>*在使用该函数进行标定运算之前，需要对棋盘上每个角点的空间坐标系位置坐标进行初始化（就是对其进行赋值），算出相机内参矩阵、相机畸变、另外每张图片会生成属于自己的平移向量和旋转向量。*
>
>`具体实现代码大致如下：`
```C++
Size image_size;//图像的尺寸
Size board_size = Size(9, 6);     //标定板上每行、列的角点数
vector<Point2f> image_points_buf;  //缓存每幅图像上检测到的角点
vector<vector<Point2f>> image_points_seq; //保存检测到的所有角点
/*提取角点*/
char filename[10];
for (size_t image_num = 1; image_num <= IMGCOUNT; image_num++)
{
    sprintf_s(filename, "%d.jpg", image_num);
    Mat imageInput = imread(filename);
    if (!findChessboardCorners(imageInput, board_size, image_points_buf))
    {
        cout << "can not find chessboard corners!\n";//找不到角点  
        return;
    }
    else
    {
        Mat view_gray;
        cvtColor(imageInput, view_gray, CV_RGB2GRAY);
        /*亚像素精确化*/
        find4QuadCornerSubpix(view_gray, image_points_buf, Size(5, 5));//对粗提取的角点进行精确化  
        drawChessboardCorners(view_gray, board_size, image_points_buf, true);//用于在图片中标记角点  
        image_points_seq.push_back(image_points_buf);//保存亚像素角点  
        imshow("Camera Calibration", view_gray);//显示图片  
        //waitKey(500);//停半秒
    }
    image_size.width = imageInput.cols;
    image_size.height = imageInput.rows;
    imageInput.release();
}
/*相机标定*/
vector<vector<Point3f>> object_points; //保存标定板上角点的三维坐标,为标定函数的第一个参数
Size square_size = Size(10, 10);//实际测量得到的标定板上每个棋盘格的大小，这里其实没测，就假定了一个值，感觉影响不是太大，后面再研究下
for (int t = 0; t<IMGCOUNT; t++)
{
    vector<Point3f> tempPointSet;
    for (int i = 0; i<board_size.height; i++)
    {
        for (int j = 0; j<board_size.width; j++)
        {
            Point3f realPoint;
            //假设标定板放在世界坐标系中z=0的平面上
            realPoint.x = i*square_size.width;
            realPoint.y = j*square_size.height;
            realPoint.z = 0;
            tempPointSet.push_back(realPoint);
        }
    }
    object_points.push_back(tempPointSet);
}
//内外参数对象
Mat cameraMatrix = Mat(3, 3, CV_32FC1, Scalar::all(0));//摄像机内参数矩阵
vector<int> point_counts;// 每幅图像中角点的数量  
Mat distCoeffs = Mat(1, 5, CV_32FC1, Scalar::all(0));//摄像机的5个畸变系数：k1,k2,p1,p2,k3
vector<Mat> tvecsMat;//每幅图像的旋转向量
vector<Mat> rvecsMat;//每幅图像的平移向量
calibrateCamera(object_points, image_points_seq, image_size, cameraMatrix, distCoeffs, rvecsMat, tvecsMat, 0);//摄像头标定
```

+ 图像矫正：
> 现在已经通过标定，然后得到摄像头的各个参数，后面就可以用这些得到的参数来做摄像头的矫正了。

**使用函数：** ***void undistort(InputArray src, OutputArray dst,InputArray cameraMatrix,InputArray distCoeffs,InputArray newCameraMatrix=noArray());***
> + 参数解析：
> + + src：输入参数，代表畸变的原始图像;
> + + dst：矫正后的输出图像，跟输入图像具有相同的类型和大小;
> + + cameraMatrix：之前求得的相机内参矩阵;
> + + distCoeffs：之前求得的相机畸变矩阵;
> + + newCameraMatrix：默认跟cameraMatrix保持一致;
>
>`具体使用代码如下：`
```C++
/*用标定的结果矫正图像*/
for (size_t image_num = 1; image_num <= IMGCOUNT; image_num++)
{
    sprintf_s(filename, "%d.jpg", image_num);
    Mat imageSource = imread(filename);
    Mat newimage = imageSource.clone();
    undistort(imageSource, newimage, cameraMatrix, distCoeffs);
    imshow("source", imageSource);//显示图片 
    imshow("drc", newimage);//显示图片  
    waitKey(500);//停半秒
    imageSource.release();
    newimage.release();
}
```

### matlab下的张正友标定法
> 前期准备：[工具箱下载](http://www.vision.caltech.edu/bouguetj/calib_doc/download/index.html)
>
> 安装：
> + 将下载的工具箱文件toolbox_calib.zip解压缩，将目录toolbox_calib拷贝到Matlab的目录下，也可以放在其他目录。
> + 运行Matlab并添加文件夹TOOLBOX_calib的位置到matlab路径path中。
> + 具体操作为：File->SetPath->Add Folder To Path，然后找到刚刚存放的文件夹
>   TOOLBOX_calib，save一下就OK了。
> + 采集图像：采集的图像统一命名后，拷贝到toolbox_calib目录中。命名规则为基本名和编号，基本名在前，后面直接跟着数字编号。编号最多为3位十进制数字。
#### 单目标定
> + 准备工作
>
>将双目摄像机拍摄的左右图像的文件夹作为matlab的当前文件夹：我的图像名称类似L1，L2……，R1，R2……（注：图像的命名格式：字母+数字，即字母在前，数字在后。）
>
> + matlab中命令窗口输入calib_gui，回车后弹出如下窗口：
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/ToolBox.jpg)
>
> + 选择第一个选项![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/StandardVersion.jpg)，弹出下面的主窗口：
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/ToolBoxStandard.jpg)
>
> + `备注：`
>
> + + “Image names”键：指定图像的基本名(Basename)和图像格式，并将相应的图像读入内存。
> + + “Read names”键：将指定基本名和格式的图像读入内存。
>
> + + “Extract grid corners”键：提取网格角点。
>
> + + “Calibration”键：内参数标定。
>
> + + “Show Extrinsic”键：以图形方式显示摄像机与标定靶标之间的关系。
>
> + + “Project on images”键：按照摄像机的内参数以及摄像机的外参数(即靶标坐标系相对于摄像机坐标系的变换关系)，根据网格点的笛卡尔空间坐标，将网格角点反投影到图像空间。 
>
> + + “Analyse error”键：图像空间的误差分析
>
> + + “Recomp. corners”键：重新提取网格角点。
>
> + + “Add/Suppress images”键：增加/删除图像。
>
> + + “Save”键：保存标定结果。将内参数标定结果以及摄像机与靶标之间的外参数保存为m文件Calib_results.m，存放于toolbox_calib目录中。
>
> + + “Load”键：读入标定结果。从存放于toolbox_calib目录中的标定结果文件Calib_results.mat读入。
>
> + + “Exit”键：退出标定。
>
> + + “Comp. Extrinsic”键：计算外参数。 
>
> + + “Undistort image”键：生成消除畸变后的图像并保存。 
>
> + + “Export calib data”键：输出标定数据。分别以靶标坐标系中的平面坐标和图像中的图像坐标，将每一幅靶标图像的角点保存为两个tex文件。
>
> + + “Show calib results”键：显示标定结果。
>
> + 选择第一个按钮![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/ImageNames.jpg)，在命令窗口中会出现当前文件夹中的所有信息：
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/Calib_Results.jpg)
>
>并提示你输入“Basename camera calibration images (without number norsuffix):”，对于我的图像名称做左图的标定时输入：L。图像格式的选择 j。回车后，显示load的所有图像。
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/LoadImage.jpg)
>
>共读入20幅图像
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/20Images.jpg)
>
> + 回到主窗口，选择第三个选项![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/ExtractGridCorners.jpg),命令窗口有如下提示：选择默认，敲击回车即可:
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/Wintx_Winty.png)
>
> + `备注：`
> + + a:“wintx ([] = 5) =”和“winty ([] = 5) =”输入行中输入角点提取区域的窗口半宽m和半高n。m和n为正整数，单位为像素，缺省值为5个像素。选定m和n后，命令窗口显示角点提取区域的窗口尺寸(2n+1)x(2m+1)。例如，选择缺省时角点提取区域的窗口尺寸为11x11像素。
> + + b:”Do you want to use the automatic square counting mechanism (0=[]=default) or do you always want to enter the number of squares manually (1,other)? “时，选择缺省值0表示自动计算棋盘格靶标选定区域内的方格行数和列数，选择值1表示人工计算并输入棋盘格靶标选定区域内的方格行数和列数。
>
> + 回车敲完后，显示第一幅棋盘格，进行角点的提取工作：用鼠标单击棋盘格外围4个角点，点击的第一个点是原点O，顺序（逆序）点击其他点，过程如图：
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/ClickOnTheFourExtemeCorners.jpg) ![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/ExtremeCorners.jpg)
>
> + `注意：`
>
> + + 1)、 这里有的要求标定内角点，如图所示。查阅多方资料并未见明确要求，但通过比较，我个人认为是因标定板而异。
>
> + + ![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/WrongPicture.jpg)
>
> + + 2)、所形成的四边形的边应与棋盘格靶标的网格线基本平行。否则，影响角点提取精度，甚至导致角点提取错误。
>
> + 回到命令窗口，输入棋盘信息：
>
>“Number of squares along the X direction ([]=10) =”输入X方向方格数目：7
>
>“Number of squares along the Y direction ([]=10) =”输入X方向方格数目：9
>
>“Size dX of each square along the X direction ([]=100mm) =”输入X方向方格长度（mm）：29；我的棋盘格X、Y方向长度相同均是29mm
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/CornerExtraction.jpg)
>
>回车后，显示角点提取结果：（我的棋盘是打印后贴在纸盒上的，棋盘并不是绝对的平面，所以有些角点的位置与真实位置有些出入）
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/Feature2.jpg)
>
> + `备注：`
>
>在Matlab命令窗口出现“Need of an initial guess for distortion? ([]=no, other=yes) ”时，如果选择no则不输入畸变初始值，如果选择yes则输入畸变初始值。输入的畸变初始值，将同时赋值给需要估计的5个畸变系数，即径向畸变系数kc(1)、kc(2)、kc(5)和切向畸变系数kc(3)、kc(4)。如果不估计6阶径向畸变系数kc(5)，则kc(5)被赋值为0
>
> + 依次循环标定后面的图像（eg.我的是20幅）
>
>注意：当第一幅图像的信息填好后，第二幅会以第一幅的信息为缺省值，只需回车即可。但是，要注意XY两个方向只与你点击的第一个角点有关，举例说明：
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/Feature1.jpg)![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/feature3.jpg)
>
>两图棋盘方向相同（横向），但是，我点击的第一个角点不同，原点O不同，XY的方向也不同。所以，为了不用每次都重新填写棋盘信息，第一个角点选择同一个位置的点（如果所拍摄的棋盘方向不同<横向、纵向都有>，则第一个点是相对于棋盘位置相同的点，说多了又是泪）
>
> + 角点提取完成以后进行标定处理，文件夹中出现.mat文件。（此处最好将其更名为 calib_data_left.mat,以免后面对右图像进行标定时将此结果覆盖。）
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/calib_data.png)
>
>回归主窗口，选择第四个选项![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/calibration.jpg)：
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/calibrationResult.jpg)
>
> + 显示摄像机与标定板间的关系：
>
>主窗口点击![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/ShowExtrinsic.jpg)，即可在新的图形窗口显示摄像机与标定靶标之间的关系:
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/IO.jpg)
>
> + 误差分析：
>
>点击![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/AnalyseError.jpg)，即可在新的图形窗口显示出标定使用的所有角点反投影到图像空间的图像坐标误差，如图所示：
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/Error.jpg)
>
>在图所示的图形窗口，利用鼠标移动十字标尺可以选择角点，即可在命令窗口显示出该角点的信息，包括该角点所属图像、索引号、以方格为单位的坐标、图像坐标、反投影后的图像坐标误差、角点提取区域的窗口半宽m和半高n。
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/WindowSize.jpg)
>
> + 点击![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/save.jpg)，文件夹中出现如下文件：
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/CalibResults.jpg)
>
> + `注：` Result.mat件在双目标定中能够用到。将"Calib_Results.mat"改成"Calib_Results_left.mat "
>
> + 点击“Undistort  image”对图像进行去畸变处理，选择对某一张图像还是所有图像进行处理，默认是all。随后，保存所有畸变处理后的图像。
>
> ![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/UndistortImage.jpg)
>
>
>
#### 双目测定
>
> + 用同样的办法处理右相机拍摄的图像。
>
>标定结果：
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/RightResults.jpg)
>
> + 单独得到摄像头标定完成以后就可以进行立体标定了。在matlab命令窗口中输入`stereo_gui`，弹出如下窗口：
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/StereoCameraToolbox.jpg)
>
> + 点击![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/LoadLeftRightFiles.jpg)，命令行窗口提示.mat文件的名称，默认的文件名(Calib_Result_left.mat和Calib_Result_right.mat)，直接回车即可，或者输入自己的文件名称。
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/LoadingFiles.png)
>
> + load文件后，命令窗口显示左右摄像机的参数信息，
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/LoadResults.jpg)
>
> + `备注：`
>
> + fc_left是左摄像机的放大系数，即焦距归一化成像平面上的成像点坐标到图像坐标的放大系数。cc_left为左摄像机的主点坐标，单位为像素。alpha_c_left是对应于左摄像机的实际y轴与理想y轴之间的夹角，单位为弧度，默认值为0弧度。kc_left为左摄像机的畸变系数。fc_right是右摄像机的放大系数，即焦距归一化成像平面上的成像点坐标到图像坐标的放大系数。cc_right为右摄像机的主点坐标，单位为像素。alpha_c_right是对应于右摄像机的实际y轴与理想y轴之间的夹角，单位为弧度，默认为0弧度。kc_right为右摄像机的畸变系数。om为左摄像机相对于右摄像机的姿态矩阵的rodrigues旋转向量，利用函数rodrigues可以转换为姿态矩阵。T为左摄像机相对于右摄像机的位移向量，即左摄像机坐标系原点在右摄像机坐标系中的位移向量，单位mm。
>
> + 点击![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/RunStereoCalibration.png)，计算优化后的外参数。命令窗口输出左、右摄像机的内参数和优化后的外参数。输出结果如下所示：
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/StereoCalibrationResults.jpg)
>
> + 点击![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/ShowExtrinsicsOfStereoRig.jpg)，显示标定靶面相对于双目摄像机的位置：
>
>![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/ExtrinsicParameters.jpg)
>
> + 点击![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/SaveStereoCalibResults.png)，将标定结果保存为文件![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/CalibResultsStereo.png)
>
> + `备注：`
>
> + 双目标定各个按钮功能：
>
> + + “Load left and right calibration files”键：读入左、右摄像机的标定结果，并对左摄像机相对于右摄像机的位姿进行初步标定。
>
> + + “Run stereo calibration”键：计算优化后的外参数。 
>
> + + “Show Extrinsics of stereo rig”键：显示靶标相对于摄像机的位姿。
>
> + + “Show Intrinsic parameters”键：在Matlab的命令窗口显示左、右摄像机的内参数和优化后的外参数。
>
> + + “Save stereo calib results”键：将标定结果保存为文件Calib_Results_stereo.mat，存放于toolbox_calib目录中。
>
> + + “Load stereo calib results”键：读入标定结果。从存放于toolbox_calib目录中的标定结果文件Calib_Results_stereo.mat读入。
>
> + + “Rectify the calibration images”键：按照畸变系数对左、右摄像机采集的所有靶标图像进行处理，生成消除畸变后的图像并保存在toolbox_calib目录中。生成的消除畸变后的图像，以原图像的文件名在基本名和编号之间插入_rectified作为其文件名。
>
> + + “Exit”键：退出立体视觉标定。
>
#### 总结：
`Matlab工具箱标定，注意问题：`
+ 内参数标定需要注意的问题 
+ + 制作棋盘格靶标时应特别注意，黑色方格与白色方格尺寸需要相同，而且所有方格的尺寸必须严格一致。靶标的方格数量不宜太小，行数和列数以大于10为宜。方格的尺寸不宜太大或太小，采集的整幅靶标图像中方格的边长尺寸不小于20像素。
+ + 采集靶标图像时应特别注意，需要在不同的角度不同的位置采集靶标的多幅图像。采集到的图像必须清晰，靶标图像尺寸以占整幅图像尺寸的1/3～3/4为宜。靶标图像最好在整幅图像的不同位置都有分布，不宜过于集中于同一区域。靶标放置位置与摄像机之间的距离最好为视觉系统的主要工作距离。靶标相对于摄像机的角度应有较大范围的变化，应包含绕三个轴较大角度的旋转，最好不小于30度。采集的靶标图像数量不应太少，建议以10～20幅靶标图像为宜。 
+ + 采集图像过程中，摄像机的焦距不能调整。因为焦距属于摄像机的内参数，不同焦距下采集的图像隐含了不同的内参数，这些图像放在一起进行标定不能得到正确的结果。 
+ + 采集的靶标图像统一命名，由基本名和编号构成，如Image1~Image15。靶标图像的数据格式必须相同。 
+ + 提取角点时，在图形窗口利用鼠标点击设定棋盘格靶标的选定区域。点击的第一个角点作为靶标坐标系的原点，顺序点击4个角点形成四边形。相邻两次点击的角点应在同一条网格线上，使得所形成的四边形的边应与棋盘格靶标的网格线基本平行。为提高点击的角点的精度，建议将显示靶标图像的图像窗口放大到最大，利用鼠标的十字标线尽可能准确的点击4个角点。
+ + 摄像机的实际y轴与理想y轴之间的夹角ac是否标定，由est_alpha标志位设定。est_alpha=1时对alpha_c进行标定，est_alpha=0时不对alpha_c进行标定。
+ + 数组est_dist(1:5)是畸变系数kc(1:5)是否标定的标志，只对标志取值为1的畸变系数标定，标志取值为0的畸变系数不标定。默认值为est_dist(1:5)=[1 1 1 1 0]，即对畸变系数kc1～kc4进行标定，对kc5不进行标定，kc5=0。
+ + 运行calib_gui指令后，Matlab处于busy状态，Matlab命令窗口不再响应其它命令。只有在点击标定工具箱的“Exit”键退出标定后，Matlab命令窗口才能恢复响应其它命令。
+ 外参数标定需要注意的问题 
+ + 方格尺寸必须输入实际尺寸
+ + 提取角点时，在图形窗口利用鼠标点击的第一个角点作为靶标坐标系的原点，得到的外参数是靶标坐标系在摄像机坐标系中的位姿
+ + rodrigues旋转向量omc_ext与姿态矩阵Rc_ext可以利用rodrigues函数进行转换。omc_ext=rodrigues(Rc_ext)，Rc_ext=rodrigues(omc_ext)
+ 立体视觉标定需要注意的问题
+ + 提取角点时，在图形窗口利用鼠标点击的第一个角点作为靶标坐标系的原点，左右摄像机对应的靶标图像对需要选择相同的第一个角点作为原点。其他的3个角点在左右摄像机的靶标图像中也应相同。
+ + 左右摄像机采集的图像数量必须相同。相同的编号的左右摄像机采集的图像是靶标在同一位姿时左右摄像机采集的图像，构成一组立体视觉的靶标图像对。
+ + 得到的外参数是左摄像机相对于右摄像机的位姿，即左摄像机坐标系在右摄像机坐标系中的位姿。
+ + 运行stereo_gui指令后，Matlab命令窗口可以响应其它命令。

## OpenCV获取图片
>使用OpenCV获取图片有两种代码，一种是使用Python，另外一种是使用C++，但是道理其实是一样的。`先读取视频，然后再取帧`。

+ Python代码：
+ `注意：保存路径需要根据自己的电脑进行配置，我这里只是使用我在我的电脑上使用的路径进行的拍照、保存功能`。
```python
import cv2
import time
from PIL import Image


def shot(pos, frame):
    global counter
    path = folder + pos + "_" + str(counter) + ".jpg"
    cv2.imwrite(path, frame)
    print("snapshot saved into: " + path)

if __name__ == '__main__':

    AUTO = True  # 自动拍照，或手动按s键拍照
    INTERVAL = 1  # 自动拍照间隔

    cv2.namedWindow("middle")
    cv2.moveWindow("middle", 400, 0)
    middle_camera = cv2.VideoCapture(1)

    counter = 0
    utc = time.time()
    pattern = (12, 8)  # 棋盘格尺寸
    folder = "F:/PyCharm_code/OpenCVDemo/snapshot/"  # 拍照文件目录(请根据自己的路径进行更改)

    while True:
        # ret, left_frame = left_camera.read()
        ret, middle_frame = middle_camera.read()

        # cv2.imshow("left", left_frame)
        cv2.imshow("middle", middle_frame)

        now = time.time()
        if AUTO and now - utc >= INTERVAL:
            shot("middle", middle_frame)
            img = Image.open(folder + "middle" + "_" + str(counter) + ".jpg")
            width, height = img.size
            w = width * 0.5
            right_box = (0, 0, w, height)
            left_box = (w, 0, width, height)
            right_region = img.crop(right_box)
            left_region = img.crop(left_box)
            right_region.save(folder + "RightTest" + "/" + str(counter) + ".jpg")
            left_region.save(folder + "LeftTest" + "/" + str(counter) + ".jpg")

            counter += 1
            utc = now


        key = cv2.waitKey(1)
        if key == ord("q"):
            break
        elif key == ord("s"):
            shot("middle", middle_frame)
            counter += 1

    middle_camera.release()
    cv2.destroyWindow("middle")
```
> + `备注：`由于我使用的双目摄像头拍照的时候所呈现的是左、右摄像头显示在同一个屏幕上，所以我中间有一个分割图像的过程。分割图像代码如下所示：

```python
            img = Image.open(folder + "middle" + "_" + str(counter) + ".jpg")
            width, height = img.size
            w = width * 0.5
            right_box = (0, 0, w, height)
            left_box = (w, 0, width, height)
            right_region = img.crop(right_box)
            left_region = img.crop(left_box)
            right_region.save(folder + "RightTest" + "/" + str(counter) + ".jpg")
            left_region.save(folder + "LeftTest" + "/" + str(counter) + ".jpg")
```
> + 读取图片然后再进行分割，之后保存。
+ C++代码：
```C++
    VideoCapture capture(0);
	Mat frame;
	if (!capture.isOpened())
	{
		cout << "摄像头打开失败" << endl;
		return -1;
	}
	char key;
	char filename[200] = "F:\VisualStudioProject\OpencvTest\srcPicture";//此路径需要根据自己电脑进行设置
	int count = 0;
	namedWindow("【视频】", 1);
	namedWindow("【图片】", 1);
	while (1) {
		key = waitKey(30);
		capture >> frame;
		imshow("【视频】", frame);
		/*imwrite(filename, frame);*/
		if (key == 27)
			break;//按ESC键退出程序  
		if (key == 32)//按空格键进行拍照  
		{
			sprintf_s(filename, "%d.jpg", ++count);
			cout << "执行完毕" << endl;
			imwrite(filename, frame);//图片保存到本工程目录中  
			imshow("【图片】", frame);
		}

	}
```
## 对图片进行立体矫正

```C++
/*立体校正*/
Rodrigues(rec, R); //Rodrigues变换
stereoRectify(cameraMatrixL, distCoeffL, cameraMatrixR, distCoeffR,imageSize, R, T, Rl, Rr, Pl, Pr, Q, CALIB_ZERO_DISPARITY,0, imageSize, &validROIL, &validROIR);
initUndistortRectifyMap(cameraMatrixL, distCoeffL,Rl,Pr,imageSize,CV_32FC1, mapLx, mapLy);
initUndistortRectifyMap(cameraMatrixR, distCoeffR,Rr,Pr,imageSize,CV_32FC1, mapRx, mapRy);
```

## 立体匹配
>采用Block Matching算法进行立体匹配，Block Matching用的是SAD方法，速度比较快，但效果一般。

`参数设置：`
+ MinDisparity设置为0，因为两个摄像头是前向平行放置，相同的物体在左图中一定比在右图中偏右。如果为了追求更大的双目重合区域而将两个摄像头向内偏转的话，这个参数是需要考虑的。 
+ UniquenessRatio主要可以防止误匹配，此参数对于最后的匹配结果是有很大的影响。立体匹配中，宁愿区域无法匹配，也不要误匹配。如果有误匹配的话，碰到障碍检测这种应用，就会很麻烦。该参数不能为负值，一般5-15左右的值比较合适，int型。
+ BlockSize：SAD窗口大小，容许范围是[5,255]，一般应该在 5x5..21x21 之间，参数必须为奇数值, int型。
+ NumDisparities：视差窗口，即最大视差值与最小视差值之差,窗口大小必须是 16的整数倍，int型。 
+ 在BM算法的参数中，对视差生成效果影响较大的主要参数是BlockSize、NumDisparities和UniquenessRatio三个，一般只需对这三个参数进行调整，其余参数按默认设置即可。 

`双目摄像头的原理：`

![](https://raw.githubusercontent.com/kentanvictor/STUDY/Image/StereoPhoto.png)

>BM算法计算出的视差disp是CV_16S格式，通过disp.convertTo(disp8, CV_8U, 255/(numberOfDisparities*16.))变换才能得到真实的视差值。 
然后通过reprojectImageTo3D这个函数将视差矩阵转换成实际的物理坐标矩阵。在实际求距离时，reprojectImageTo3D出来的X / W, Y / W, Z / W都要乘以16(也就是W除以16)，才能得到正确的三维坐标信息。 

+ 立体匹配代码：
```C++
/*****立体匹配*****/
void stereo_match(int, void*)
{
	bm->setBlockSize(2 * blockSize + 5);     //SAD窗口大小，5~21之间为宜
	bm->setROI1(validROIL);
	bm->setROI2(validROIR);
	bm->setPreFilterCap(31);
	bm->setMinDisparity(0);  //最小视差，默认值为0, 可以是负值，int型
	bm->setNumDisparities(numDisparities * 16 + 16);//视差窗口，即最大视差值与最小视差值之差,窗口大小必须是16的整数倍，int型
	bm->setTextureThreshold(10);
	bm->setUniquenessRatio(uniquenessRatio);//uniquenessRatio主要可以防止误匹配
	bm->setSpeckleWindowSize(100);
	bm->setSpeckleRange(32);
	bm->setDisp12MaxDiff(-1);
	Mat disp, disp8,copyImage;
	bm->compute(rectifyImageL, rectifyImageR, disp);//输入图像必须为灰度图
	disp.convertTo(disp8, CV_8U, 255 / ((numDisparities * 16 + 16)*16.));//计算出的视差是CV_16S格式
	reprojectImageTo3D(disp, xyz, Q, true); //在实际求距离时，ReprojectTo3D出来的X / W, Y / W, Z / W都要乘以16(也就是W除以16)，才能得到正确的三维坐标信息。
	xyz = xyz * 16;
	imshow("disparity", disp8);
	copyImage = disp8.clone();
	imshow("contour", copyImage);
	//根据现有的视差图进行凸包的绘制
	Mat threshold_output;
	vector<vector<Point> > contours;
	vector<Vec4i> hierarchy;
	RNG rng(12345);
	threshold(copyImage, threshold_output, 20, 255, CV_THRESH_BINARY);//二值化
	findContours(threshold_output, contours, hierarchy, CV_RETR_TREE, CV_CHAIN_APPROX_SIMPLE, Point(0, 0));//寻找轮廓
	/// 对每个轮廓计算其凸包
	vector<vector<Point> >hull(contours.size());
	vector<vector<Point> > result;
	for (int i = 0; i < contours.size(); i++)
	{
		convexHull(Mat(contours[i]), hull[i], false);

	}

	/// 绘出轮廓及其凸包
	Mat drawing = Mat::zeros(threshold_output.size(), CV_8UC3);
	for (int i = 0; i< contours.size(); i++)
	{
		if (contourArea(contours[i]) < 500)//面积小于area的凸包，可忽略
			continue;
		result.push_back(hull[i]);
		Scalar color = Scalar(rng.uniform(0, 255), rng.uniform(0, 255), rng.uniform(0, 255));
		drawContours(drawing, contours, i, color, 1, 8, vector<Vec4i>(), 0, Point());
		drawContours(drawing, hull, i, color, 1, 8, vector<Vec4i>(), 0, Point());
	}
	imshow("contours", drawing);//凸包大小
}
```

## 总结：
>使用MatLab或者OpenCV进行内参以及外参的设定，然后将测出的数据填入到代码中，再使用立体矫正以及立体匹配，这样就可以得到深度图，使用深度图再进行视差运算，得到视差图。
+ 完整代码如下：
```C++
#include <opencv2\opencv.hpp>
#include <string>
#include <iostream>
#include "opencv2/imgproc/imgproc.hpp"
#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>

using namespace std;
using namespace cv;

const int imageWidth = 320;                             //摄像头的分辨率  
const int imageHeight = 240;
Size imageSize = Size(imageWidth, imageHeight);

Mat rgbImageL, grayImageL;
Mat rgbImageR, grayImageR;
Mat rectifyImageL, rectifyImageR;

Rect validROIL;//图像校正之后，会对图像进行裁剪，这里的validROI就是指裁剪之后的区域  
Rect validROIR;

Mat mapLx, mapLy, mapRx, mapRy;     //映射表  
Mat Rl, Rr, Pl, Pr, Q;              //校正旋转矩阵R，投影矩阵P 重投影矩阵Q
Mat xyz;							//三维坐标


Point origin;					//鼠标按下的起始点
Rect selection;					//定义矩形选框
bool selectObject = false;		//是否选择对象

int blockSize = 7, uniquenessRatio = 20, numDisparities = 0;
Ptr<StereoBM> bm = StereoBM::create(16, 9);

/*
事先标定好的相机的参数
fx 0 cx
0 fy cy
0 0  1
*/
Mat cameraMatrixL = (Mat_<double>(3, 3) << 248.32797, 0, 248.24842,
	0, 150.87402, 114.30813,
	0, 0, 1);
Mat distCoeffL = (Mat_<double>(5, 1) << 0.04477, -0.10081, 0.01026, 0.00132, 0.00000);

Mat cameraMatrixR = (Mat_<double>(3, 3) << 248.74867, 0, 248.84978,
	0, 152.62972, 98.07575,
	0, 0, 1);
Mat distCoeffR = (Mat_<double>(5, 1) << -0.04158, 0.08338, -0.00584, 0.00611, 0.00000);

Mat T = (Mat_<double>(3, 1) << 182.44004, 0.20804, 0.41865);//T平移向量
Mat rec = (Mat_<double>(3, 1) << -0.05347, -0.00229, -0.00203);//rec旋转向量
Mat R;//R 旋转矩阵


/*****立体匹配*****/
void stereo_match(int, void*)
{
	bm->setBlockSize(2 * blockSize + 5);     //SAD窗口大小，5~21之间为宜
	bm->setROI1(validROIL);
	bm->setROI2(validROIR);
	bm->setPreFilterCap(31);
	bm->setMinDisparity(0);  //最小视差，默认值为0, 可以是负值，int型
	bm->setNumDisparities(numDisparities * 16 + 16);//视差窗口，即最大视差值与最小视差值之差,窗口大小必须是16的整数倍，int型
	bm->setTextureThreshold(10);
	bm->setUniquenessRatio(uniquenessRatio);//uniquenessRatio主要可以防止误匹配
	bm->setSpeckleWindowSize(100);
	bm->setSpeckleRange(32);
	bm->setDisp12MaxDiff(-1);
	Mat disp, disp8,copyImage;
	bm->compute(rectifyImageL, rectifyImageR, disp);//输入图像必须为灰度图
	disp.convertTo(disp8, CV_8U, 255 / ((numDisparities * 16 + 16)*16.));//计算出的视差是CV_16S格式
	reprojectImageTo3D(disp, xyz, Q, true); //在实际求距离时，ReprojectTo3D出来的X / W, Y / W, Z / W都要乘以16(也就是W除以16)，才能得到正确的三维坐标信息。
	xyz = xyz * 16;
	imshow("disparity", disp8);
	copyImage = disp8.clone();
	imshow("contour", copyImage);
	//根据现有的视差图进行凸包的绘制
	Mat threshold_output;
	vector<vector<Point> > contours;
	vector<Vec4i> hierarchy;
	RNG rng(12345);
	threshold(copyImage, threshold_output, 20, 255, CV_THRESH_BINARY);//二值化
	findContours(threshold_output, contours, hierarchy, CV_RETR_TREE, CV_CHAIN_APPROX_SIMPLE, Point(0, 0));//寻找轮廓
	/// 对每个轮廓计算其凸包
	vector<vector<Point> >hull(contours.size());
	vector<vector<Point> > result;
	for (int i = 0; i < contours.size(); i++)
	{
		convexHull(Mat(contours[i]), hull[i], false);

	}

	/// 绘出轮廓及其凸包
	Mat drawing = Mat::zeros(threshold_output.size(), CV_8UC3);
	for (int i = 0; i< contours.size(); i++)
	{
		if (contourArea(contours[i]) < 500)//面积小于area的凸包，可忽略
			continue;
		result.push_back(hull[i]);
		Scalar color = Scalar(rng.uniform(0, 255), rng.uniform(0, 255), rng.uniform(0, 255));
		drawContours(drawing, contours, i, color, 1, 8, vector<Vec4i>(), 0, Point());
		drawContours(drawing, hull, i, color, 1, 8, vector<Vec4i>(), 0, Point());
	}
	imshow("contours", drawing);//凸包大小
}



/*****描述：鼠标操作回调*****/
static void onMouse(int event, int x, int y, int, void*)
{
	if (selectObject)
	{
		selection.x = MIN(x, origin.x);
		selection.y = MIN(y, origin.y);
		selection.width = std::abs(x - origin.x);
		selection.height = std::abs(y - origin.y);
	}

	switch (event)
	{
	case EVENT_LBUTTONDOWN:   //鼠标左按钮按下的事件
		origin = Point(x, y);
		selection = Rect(x, y, 0, 0);
		selectObject = true;
		cout << origin << "in world coordinate is: " << xyz.at<Vec3f>(origin) << endl;
		/*cout << origin << "深度信息为：" << xyz.at<Vec3f>(origin) << endl;*/
		break;
	case EVENT_LBUTTONUP:    //鼠标左按钮释放的事件
		selectObject = false;
		if (selection.width > 0 && selection.height > 0)
			break;
	}
}




int main()
{
	VideoCapture capture(0);
	Mat frame;
	if (!capture.isOpened())
	{
		cout << "摄像头打开失败" << endl;
		return -1;
	}
	char key;
	char filename[200] = "F:\VisualStudioProject\OpencvTest\srcPicture";
	int count = 0;
	namedWindow("【视频】", 1);
	namedWindow("【图片】", 1);
	while (1) {
		key = waitKey(30);
		capture >> frame;
		imshow("【视频】", frame);
		/*imwrite(filename, frame);*/
		if (key == 27)
			break;//按ESC键退出程序  
		if (key == 32)//按空格键进行拍照  
		{
			sprintf_s(filename, "%d.jpg", ++count);
			cout << "执行完毕" << endl;
			imwrite(filename, frame);//图片保存到本工程目录中  
			imshow("【图片】", frame);
		}

	}
	
	/*
	立体校正
	*/
	Rodrigues(rec, R); //Rodrigues变换
	stereoRectify(cameraMatrixL, distCoeffL, cameraMatrixR, distCoeffR, imageSize, R, T, Rl, Rr, Pl, Pr, Q, CALIB_ZERO_DISPARITY,
		0, imageSize, &validROIL, &validROIR);
	initUndistortRectifyMap(cameraMatrixL, distCoeffL, Rl, Pr, imageSize, CV_32FC1, mapLx, mapLy);
	initUndistortRectifyMap(cameraMatrixR, distCoeffR, Rr, Pr, imageSize, CV_32FC1, mapRx, mapRy);

	/*
	读取图片
	*/
	string left_test = "F:\\VisualStudioProject\\OpencvTest\\LeftPicture\\LDemo.jpg";
	string right_test = "F:\\VisualStudioProject\\OpencvTest\\RightPicture\\RDemo.jpg";
	rgbImageL = imread(left_test, CV_LOAD_IMAGE_COLOR);
	cvtColor(rgbImageL, grayImageL, CV_BGR2GRAY);
	rgbImageR = imread(right_test, CV_LOAD_IMAGE_COLOR);
	cvtColor(rgbImageR, grayImageR, CV_BGR2GRAY);

	imshow("ImageL Before Rectify", grayImageL);
	imshow("ImageR Before Rectify", grayImageR);
	/*
	经过remap之后，左右相机的图像已经共面并且行对准了
	*/
	remap(grayImageL, rectifyImageL, mapLx, mapLy, INTER_LINEAR);
	remap(grayImageR, rectifyImageR, mapRx, mapRy, INTER_LINEAR);


	/*
	把校正结果显示出来
	*/
	Mat rgbRectifyImageL, rgbRectifyImageR;
	cvtColor(rectifyImageL, rgbRectifyImageL, CV_GRAY2BGR);  //伪彩色图
	cvtColor(rectifyImageR, rgbRectifyImageR, CV_GRAY2BGR);

	//单独显示
	//rectangle(rgbRectifyImageL, validROIL, Scalar(0, 0, 255), 3, 8);
	//rectangle(rgbRectifyImageR, validROIR, Scalar(0, 0, 255), 3, 8);
	imshow("ImageL After Rectify", rgbRectifyImageL);
	imshow("ImageR After Rectify", rgbRectifyImageR);

	//显示在同一张图上
	Mat canvas;
	double sf;
	int w, h;
	sf = 600. / MAX(imageSize.width, imageSize.height);
	w = cvRound(imageSize.width * sf);
	h = cvRound(imageSize.height * sf);
	canvas.create(h, w * 2, CV_8UC3);   //注意通道

										//左图像画到画布上
	Mat canvasPart = canvas(Rect(w * 0, 0, w, h));                                //得到画布的一部分  
	resize(rgbRectifyImageL, canvasPart, canvasPart.size(), 0, 0, INTER_AREA);     //把图像缩放到跟canvasPart一样大小  
	Rect vroiL(cvRound(validROIL.x*sf), cvRound(validROIL.y*sf),                //获得被截取的区域    
		cvRound(validROIL.width*sf), cvRound(validROIL.height*sf));
	//rectangle(canvasPart, vroiL, Scalar(0, 0, 255), 3, 8);                      //画上一个矩形  
	cout << "Painted ImageL" << endl;

	//右图像画到画布上
	canvasPart = canvas(Rect(w, 0, w, h));                                      //获得画布的另一部分  
	resize(rgbRectifyImageR, canvasPart, canvasPart.size(), 0, 0, INTER_LINEAR);
	Rect vroiR(cvRound(validROIR.x * sf), cvRound(validROIR.y*sf),
		cvRound(validROIR.width * sf), cvRound(validROIR.height * sf));
	//rectangle(canvasPart, vroiR, Scalar(0, 0, 255), 3, 8);
	cout << "Painted ImageR" << endl;

	//画上对应的线条
	for (int i = 0; i < canvas.rows; i += 16)
		line(canvas, Point(0, i), Point(canvas.cols, i), Scalar(0, 255, 0), 1, 8);
	imshow("rectified", canvas);
	//find_obstacle(grayImageL, 20, 255, 500);

	/*
	立体匹配
	*/
	namedWindow("disparity", CV_WINDOW_AUTOSIZE);
	// 创建SAD窗口 Trackbar
	createTrackbar("BlockSize:\n", "disparity", &blockSize, 8, stereo_match);
	// 创建视差唯一性百分比窗口 Trackbar
	createTrackbar("UniquenessRatio:\n", "disparity", &uniquenessRatio, 50, stereo_match);
	// 创建视差窗口 Trackbar
	createTrackbar("NumDisparities:\n", "disparity", &numDisparities, 16, stereo_match);
	//鼠标响应函数setMouseCallback(窗口名称, 鼠标回调函数, 传给回调函数的参数，一般取0)
	setMouseCallback("disparity", onMouse, 0);
	stereo_match(0, 0);

	waitKey(0);
	return 0;
}
```

`备注：`
+ 上面的代码中，需要自己填入的数据有：
+ + 测定的摄像头的内参以及外参
+ + 保存以及读取图片的路径