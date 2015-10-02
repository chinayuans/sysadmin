该目录为applet开发使用的专有目录
class 保存applet的class文件保存地方
jsp   为jsp页面的保存地方


===============================
Classpath和Codebase:
===============================


1.概要
Classpath和Codebase是Java中非常重要的两个概念，初学者如果没有掌握这两个概念，
在遇到诸如ClassNotFoundException或者相关的异常时不知所措。另外，
很多其它方面的错误往往也和Classpath有关。本文将详细解释这两个概念，
并且描述在命令行方式和集成开发环境（以JBuilder为例）中的设置方式。 

2.什么是Classpath
Classpath是Java中的重要概念，它描述了Java虚拟机在运行一个Class时在哪些路径中
加载要运行的类以及运行的类要用到的类。简单的说，就是像操作系统的path，只不过这个
classpath是由Java的虚拟机来使用查找需要加载的类，而操作系统的path是由操作系统
用来查找用户输入的可执行程序。同path一样，classpath也是一个环境变量，可以通过set命令来设置。 

3.Classpath和Java包的关系
Java的包（Package）和classpath关系密切。包是以“.”分割的，SUN建议使用域名的逆
向排列来区分不同的包，以避免冲突，如com.company.util。在一个包里的类在存储的时候
需要存储在和包名相同的目录里，如上述com.company.util包中的Sample.class，要存
储在com\company\util目录中。 
Classpath有两种表达方式，一种是指向目录的classpath，如C:\work\classes，表示
C:\work\classes目录是一个classpath条目；另一种方式是指向压缩文件的classpath，
如C:\work\util.jar，表示C:\work\util.jar文件是一个classpath条目，任何一个
包含Java类的zip格式的压缩文件都可以作为classpath的条目。 
那么classpath和包到底是什么关系呢？简单的说，就是Java虚拟机在加载类的时候以这样一
种方式查找具体的类文件：classpath＋包存储的目录＋具体的类文件。如classpath中有一个
c:\work\classes条目，需要加载的类是com.company.util.Sample.class，那么在加载
这个类的时候，虚拟机会查找c:\work\classes\com\company\util目录，如果Sample.class
在这个目录中，虚拟机就可以找到，如果这个类不在这个目录中，同时也不在任何一个其它classpath中，
那么虚拟机会抛出一个ClassNotFoundException。 

4.Classpath的顺序和类版本冲突
Java虚拟机在加载类的时候查找classpath是有顺序的，如果在classpath中有多个条目都有
同一个名称的类，那么在较前位置的类会被加载，后面的会被忽略。这种按照顺序的类加载可能会
导致类的版本冲突。例如classpath＝c:\servlet2.2\servlet.jar；c:\servlet2.3\servlet.jar，
那么在实际应用的过程中，你使用的是servlet2.2，而不是servlet2.3。很多时候如果不注意这一点，可能会导致奇怪的异常。 

5.命令行状态下的classpath设置
命令行状态下的classpath可以通过两种方式设置。 
一种是直接设置环境变量，例如在windows环境下，我们使用set命令： 
set classpath＝c:\work\classes；c:\work\util.jar 
另一种方式是在执行javac、java或者其它Java命令时直接指定classpath： 
java -classpath c:\work\classes;c:\work\util.jar com.company.util.Sample 

6.集成开发环境下的classpath设置

集成开发环境中设置classpath一般通过其用户界面进行，各种集成开发环境的classpath设置各不相同，
我们以JBuilder为例来说明集成开发环境下的classpath。 
(1).设置Jbuilder的Library 
JBuilder中的classpath要通过类库来设置，首先选择Tools->Configure Library，然后点击New按钮，
点击Add...，可以选择要增加的类库，这个类库可以是目录，也可以是zip格式的压缩文件，如.jar或者.zip。 
(2).设置项目需要用到的Library 
在设置了类库之后，在JBuilder中运行一个class时并不会马上查找你加入的类库，而是要在
Project->Project Properties->Path->Required Library中设置，选择Add...按钮，你就可以增加
自己的classpath条目了。 

7.什么是Codebase
使用Java语言编写的程序，不仅可以在本地的classpath中加载类，也可以根据需要从网络上下载类。
为了使Java程序可以从网络上下载类，我们需要使用codebase，codebase指定了Java程序在网络上何处可以找到需要的类。 

8.在Java Applet中使用codebase
众所周知，可以在Java Applet中使用codebase指定Applet加载其所需要的类的网络位置。例如： 
代码：
--------------------------------------------------------------------------------

<applet codebase="." code=Animator.class width=460 height=160>

--------------------------------------------------------------------------------
这个Applet指定了其所用的类可以在服务器上Applet所在的目录下找到。 

9.在Java Application中使用codebase
不仅在Applet中可以使用codebase，在Application中也可以使用codebase。这样Application除了可以使用
classpath中的类，还可以使用网络上的类。例如： 
java －classpath c:\work\classes －codebase http://www.company.com/classes Sample 
应用程序Sample不仅可以使用c:\work\classes中的类，还可以使用http://www.company.com/classes中的类。 

10.Classpath和codebase的关系
既然Java虚拟机即可以在classpath中加载类，又可以在codebase中加载类，那么classpath和codebase是什么关系呢？
实际上，Classpath和codebase都是由系统类加载器（ClassLoader）使用的。类加载器在加载一个类的时候，
首先在classpath中查找需要的类，然后在codebase中查找，第一个被查找到的类会被加载。例如，在早期的JDK版本中，
缺省codebase是空值，如果没有在classpath中指定“.”（当前目录），运行当前目录下的javaclass时会出现
ClassNotFoundException；而在晚些版本中，缺省codebase是“.”，所以即使不在classpath中加入“.”，当前目录下的
java class仍然可以正常运行。 

11.总结
初学Java的朋友一定要掌握classpath和codebase的概念，理解其内涵，如果出现编译、运行程序的过程中出现什么问题，
首先应该考虑是否是classpath的问题。实际上，甚至一些非常复杂的应用中，比如服务器端应用，都会因为classpath的
设置而出现莫名其妙的问题。如果大家在阅读本文的过程中有什么问题，请提出来大家一起讨论。 
 
 
