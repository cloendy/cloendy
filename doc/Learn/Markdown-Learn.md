# Markdown

### 第一节    基础语法

1. 空格空格回车  --> 换行

2. 换行+回车  -->换段

3. 前面加#为h1，加##为h2，......

4. 两个星号：**加粗**，

5. 单个星号： *斜体*或 _下划线_，
   
   下划线不能直接相连，或两侧加空格

6. 三个*号 ：***斜体和加粗***

7. 两个波浪号：~~删除线~~

8. 分割线：三个及以上连续星号/下划线，三个减号，后面不能接内容
   
   ---

9. 标题：是几级标题就加几个井号，最多六级标题
   
   1. 一个等号：一级
   
   2. 一个减号：二级

10. <font face = "逐浪圆体">我是逐浪圆体</font>

11. <font color="Red">我是红色</font>

12. 





### 列表

##### 无序列表

* 星号空一格

* 加号

* 减号
  
  * 不能混用

##### 有序列表

1. 数字+小数点+空格

2. 二级列表
   
   * 加上四个空格或tab

##### 列表换行

* 加两个空格会在第三项内换行

* 两个回车新段

##### 勾选列表

* [ ] 星号之后加入方括号
  
  * 括号里空格为空
  
  * 加入x为选中

### 

### 代码块

1. 加入四个空格
   
    public class Main {
   
        public static void main(String[] args) {
            System.out.println("Hello World")
        }
   
    }

2. 使用三个反引号

```java
public class Main{
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

3. 调用方法：使用一个反引号`equals()`

### 引用

右尖括号

> 来自青空的霞光
> 
> 可以使用多个尖括号
> 
> 内部可以嵌套，也可以使用在其他里面
> 
> * 列表
> 
> > 引用的引用。。等等

### 链接

1. 先使用方括号选中超链接文本，后接小括号写入[网址](https://baidu.com)

2. 提前起[变量名][a]为中括号包围的a

[a]: http://www.baidu.com

3. 标注[^1]

脚注[^2]

[^1]: 我是脚注

### 插入图片

网络或本地链接

感叹号+方括号+小括号

小括号可以换为变量

注：html图片

![](ru)

### 表格

1. 使用|表示框 
2. |---|表示表头，表头自动加粗
3. 居中显示，在分割线加：，
   1. 只加左边：左对齐
   2. 只加右边：右对齐
   3. 两边都加：居中

| 姓名  | 年龄  |
| --- | --- |
| 小明  | 18  |
| 小红  | 17  |

### 嵌入HTML

<img title="" src="file:///C:/Users/wugl/Pictures/Vero/Vero_Wallpaper_05_3840x2400.jpg" alt="" data-align="left">

img 

<hr>

span style="",  ,/

<iframe src="//player.bilibili.com/player.html?isOutside=true&aid=1252624739&bvid=BV1eJ4m157kC&cid=1489709802&p=10" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true" height="500px"></iframe>

网站加载失败

### 数学公式

打开数学公式支持

1. 美元符包围

$y=x+1$

2. 两个美元符，多行
   
   $$
   y=e^x
\int_0^1x
   $$

3. \sqrt是根号

4. \frac是分号

5. \可以转义

6. 不等于：\not=  \\

7. 约等于：\approx \\

8. 两个反斜杠为换行

9. 小于等于：\leq

10. 大于等于：\geq

11. 乘号：\times

12. 除号：\div

13. 正负号：\pm

14. 求和符号：\sum

15. 累乘：\prod

16. 累除：\coprod

17. 平均：

18. 角度：^\circ
    \sin
    \pi
    \cos
    \tan
    无穷：\infty
    积分：\int
    多积分：\iiint
    求导：\prime
    极限：\lim
