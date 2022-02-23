# "Transformers in Medical Imaging：A Survey" Notes

## Introduction

+ The workhorse in CNNS is the convolution poerator.

`Drawback with CNNs: ` 

+ the local receptive field in convolution operation limits capturing long-range pixel relationships. 

注释：不能看整体，只能看到局部之间的关系。

+  the convolutional filters have stationary weights that are not adapted for the given input image content at inference time.

无法在中途插入输入图像内容。

`Vision Transformers`

将Transformers模块取代CNN中的标准卷积。

`文章结构顺序：`

+ Section 3 ：Segmentation
+ Section 4 ：Classification
+ Section 5 ：Detection
+ Section 6 ：Reconstruction
+ Section 7 ：Synthesis
+ Section 8 ：Registration
+ Section 9 ：Clinical Report Generation
+ Section 10 ：Other Tasks 

## Background

### Hand-Crafted Approaches

成功的模型有：

+ total-variation

+ non-local self-similarity

+ sparsity/structured sparsity

+ Markov-tree models on wavelet coefficients 

+ untrained neural networks

上面的模型不需要大量有label的数据集进行训练，但是辨别能力差。

为了规避可辨别性和通用性差的问题，人们提出了学习手工制作的模型来更好地利用数据，如：

+ optimal directions

+ K-SVD

+ data-driven tight frame

+ low-rank models

+ piece-wise smooth image model

### CNN-based methods

大多数模型是将CNN和Hand-Crafted Approaches进行结合，使得先验信息引导CNN模型，有以下这些方法：

+ unrolled optimization

+ generative models

+ learned denoiser-based approaches

### Transformers