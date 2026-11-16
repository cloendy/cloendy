# JVM学习  
架构内容：
虚拟机栈 VM Stacks
|-> 栈帧 Frame
本地方法栈 Native Method Stack
PC 寄存器 Program Counter Register
方法区 Method Area （Non-Heap）
堆 Heap


JVM内存管理  
1、物理内存与虚拟内存
  物理内存即 RAM（随机存储器）  
  寄存器，用于存储计算单元执行指令（如浮点，整数等运算）  
  地址总线，连接处理器和RAM  
  虚拟内存使得多个进程在同时运行时可以共享物理内存  

2、内核空间与用户空间  
  内核空间：操作系统运行时所使用的用于程序调度，虚拟内存的使用或连接硬件资源等的程序逻辑  
  用户空间：用户运行程序所占用的空间

3、Java中需要使用内存的组件  
  java堆  
    用于存储Java对象的内存区域  
    -Xmx 表示堆的最大大小  
    -Xms 表示堆的最小大小  
    一旦分配完成，就不能在内存不够时再向操作系统重新申请  
  线程  
    每个线程创建时JVM会为线程创建一个堆栈，通常在256KB~756KB之间  
  类和加载器  
    在Sun SDK中被存储在堆中，这个区域叫永久代（PermGen区）  
      只有HotSpot才有PermGen space  
      JRockit（Oracle）、J9（IBM）并没有PermGen space  
      JDK1.8中PermSize和MaxPermGen已经无效，使用元空间替代永久代，元空间不在虚拟机中，而是使用本地内存  
    默认的3个类加载器  
      Bootstrap ClassLoader 启动类加载器
      ExtClassLoader 扩展类加载器
      AppClassLoader 系统类加载器/应用类加载器  
  NIO  
    JDK1.4版本后引入了一种基于通道和缓冲区来执行I/O的新方式
    使用java.nio.ByteBuffer.allocateDirect()方法分配内存，分配的是本机内存而不是Java堆内存
    每次分配内存时会调用操作系统的os::malloc()函数
  JNI  
    JNI使得本机代码（如C语言程序）可以调用Java方法，也就是native memory  

4、JVM内存结构
  PC寄存器：记录线程当前执行到哪条指令  
  栈  
  堆  
  方法区：用于存储类结构信息，可以被所有线程共享，不会频繁GC  
  运行时常量池：代表运行时每个class文件中的常量表（1.数字常量；2.方法；3.字段的引用）  
  本地方法栈：为JVM运行Native方法准备的空间  

5、JVM内存回收策略  


6、JVM参数  
  -Xmx3550m：设置堆最大值
  -Xms3550m：设置初始堆大小（最小值）  
  -Xss128k：设置线程栈大小
  -Xmn2g：设置年轻代大小，在整个堆内存大小确定的情况下，增大年轻代将会减小老年代，反之亦然  
  -XX:NewSize=1024m：设置年轻代初始值
  -XX:MaxNewSize=1024m：设置年轻代最大值
  -XX:PermSize=256m：设置永久代初始值
  -XX:MaxPermSize=256m：设置永久代最大值
  -XX:NewRatio=4：设置年轻代（包括一个Eden区和两个Survivor区）和老年代的比值
  -XX:SurvivorRatio=4：设置Survivor区和Eden区的比值 （Eden:S0:S1=4:1:1）
  -XX:MaxTenuringThreshold=7：表示一个对象如果在Survivor区（救助空间）移动了7次还没有被垃圾回收就进入老年代  


|术语|	英文|	是什么	|
|---|---|---|
|永久代/持久代|	Permanent Generation, PermGen|	HotSpot 在 JDK 7 及以前对方法区的实现，主要存类元数据、常量池、静态变量等|  
|老年代/年老代|	Old Generation / Tenured Generation|	Java 堆内存分代的一部分，存经过多次 GC 仍存活的长生命周期对象|  
|年轻代/新生代|Young Gen|	新创建的对象，生命周期短|






