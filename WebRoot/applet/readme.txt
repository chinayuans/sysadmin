��Ŀ¼Ϊapplet����ʹ�õ�ר��Ŀ¼
class ����applet��class�ļ�����ط�
jsp   Ϊjspҳ��ı���ط�


===============================
Classpath��Codebase:
===============================


1.��Ҫ
Classpath��Codebase��Java�зǳ���Ҫ�����������ѧ�����û���������������
����������ClassNotFoundException������ص��쳣ʱ��֪���롣���⣬
�ܶ���������Ĵ�������Ҳ��Classpath�йء����Ľ���ϸ�������������
���������������з�ʽ�ͼ��ɿ�����������JBuilderΪ�����е����÷�ʽ�� 

2.ʲô��Classpath
Classpath��Java�е���Ҫ�����������Java�����������һ��Classʱ����Щ·����
����Ҫ���е����Լ����е���Ҫ�õ����ࡣ�򵥵�˵�����������ϵͳ��path��ֻ�������
classpath����Java���������ʹ�ò�����Ҫ���ص��࣬������ϵͳ��path���ɲ���ϵͳ
���������û�����Ŀ�ִ�г���ͬpathһ����classpathҲ��һ����������������ͨ��set���������á� 

3.Classpath��Java���Ĺ�ϵ
Java�İ���Package����classpath��ϵ���С������ԡ�.���ָ�ģ�SUN����ʹ����������
�����������ֲ�ͬ�İ����Ա����ͻ����com.company.util����һ����������ڴ洢��ʱ��
��Ҫ�洢�ںͰ�����ͬ��Ŀ¼�������com.company.util���е�Sample.class��Ҫ��
����com\company\utilĿ¼�С� 
Classpath�����ֱ�﷽ʽ��һ����ָ��Ŀ¼��classpath����C:\work\classes����ʾ
C:\work\classesĿ¼��һ��classpath��Ŀ����һ�ַ�ʽ��ָ��ѹ���ļ���classpath��
��C:\work\util.jar����ʾC:\work\util.jar�ļ���һ��classpath��Ŀ���κ�һ��
����Java���zip��ʽ��ѹ���ļ���������Ϊclasspath����Ŀ�� 
��ôclasspath�Ͱ�������ʲô��ϵ�أ��򵥵�˵������Java������ڼ������ʱ��������һ
�ַ�ʽ���Ҿ�������ļ���classpath�����洢��Ŀ¼����������ļ�����classpath����һ��
c:\work\classes��Ŀ����Ҫ���ص�����com.company.util.Sample.class����ô�ڼ���
������ʱ������������c:\work\classes\com\company\utilĿ¼�����Sample.class
�����Ŀ¼�У�������Ϳ����ҵ����������಻�����Ŀ¼�У�ͬʱҲ�����κ�һ������classpath�У�
��ô��������׳�һ��ClassNotFoundException�� 

4.Classpath��˳�����汾��ͻ
Java������ڼ������ʱ�����classpath����˳��ģ������classpath���ж����Ŀ����
ͬһ�����Ƶ��࣬��ô�ڽ�ǰλ�õ���ᱻ���أ�����Ļᱻ���ԡ����ְ���˳�������ؿ��ܻ�
������İ汾��ͻ������classpath��c:\servlet2.2\servlet.jar��c:\servlet2.3\servlet.jar��
��ô��ʵ��Ӧ�õĹ����У���ʹ�õ���servlet2.2��������servlet2.3���ܶ�ʱ�������ע����һ�㣬���ܻᵼ����ֵ��쳣�� 

5.������״̬�µ�classpath����
������״̬�µ�classpath����ͨ�����ַ�ʽ���á� 
һ����ֱ�����û���������������windows�����£�����ʹ��set��� 
set classpath��c:\work\classes��c:\work\util.jar 
��һ�ַ�ʽ����ִ��javac��java��������Java����ʱֱ��ָ��classpath�� 
java -classpath c:\work\classes;c:\work\util.jar com.company.util.Sample 

6.���ɿ��������µ�classpath����

���ɿ�������������classpathһ��ͨ�����û�������У����ּ��ɿ���������classpath���ø�����ͬ��
������JBuilderΪ����˵�����ɿ��������µ�classpath�� 
(1).����Jbuilder��Library 
JBuilder�е�classpathҪͨ����������ã�����ѡ��Tools->Configure Library��Ȼ����New��ť��
���Add...������ѡ��Ҫ���ӵ���⣬�����������Ŀ¼��Ҳ������zip��ʽ��ѹ���ļ�����.jar����.zip�� 
(2).������Ŀ��Ҫ�õ���Library 
�����������֮����JBuilder������һ��classʱ���������ϲ�����������⣬����Ҫ��
Project->Project Properties->Path->Required Library�����ã�ѡ��Add...��ť����Ϳ�������
�Լ���classpath��Ŀ�ˡ� 

7.ʲô��Codebase
ʹ��Java���Ա�д�ĳ��򣬲��������ڱ��ص�classpath�м����࣬Ҳ���Ը�����Ҫ�������������ࡣ
Ϊ��ʹJava������Դ������������࣬������Ҫʹ��codebase��codebaseָ����Java�����������Ϻδ������ҵ���Ҫ���ࡣ 

8.��Java Applet��ʹ��codebase
������֪��������Java Applet��ʹ��codebaseָ��Applet����������Ҫ���������λ�á����磺 
���룺
--------------------------------------------------------------------------------

<applet codebase="." code=Animator.class width=460 height=160>

--------------------------------------------------------------------------------
���Appletָ���������õ�������ڷ�������Applet���ڵ�Ŀ¼���ҵ��� 

9.��Java Application��ʹ��codebase
������Applet�п���ʹ��codebase����Application��Ҳ����ʹ��codebase������Application���˿���ʹ��
classpath�е��࣬������ʹ�������ϵ��ࡣ���磺 
java ��classpath c:\work\classes ��codebase http://www.company.com/classes Sample 
Ӧ�ó���Sample��������ʹ��c:\work\classes�е��࣬������ʹ��http://www.company.com/classes�е��ࡣ 

10.Classpath��codebase�Ĺ�ϵ
��ȻJava�������������classpath�м����࣬�ֿ�����codebase�м����࣬��ôclasspath��codebase��ʲô��ϵ�أ�
ʵ���ϣ�Classpath��codebase������ϵͳ���������ClassLoader��ʹ�õġ���������ڼ���һ�����ʱ��
������classpath�в�����Ҫ���࣬Ȼ����codebase�в��ң���һ�������ҵ�����ᱻ���ء����磬�����ڵ�JDK�汾�У�
ȱʡcodebase�ǿ�ֵ�����û����classpath��ָ����.������ǰĿ¼�������е�ǰĿ¼�µ�javaclassʱ�����
ClassNotFoundException��������Щ�汾�У�ȱʡcodebase�ǡ�.�������Լ�ʹ����classpath�м��롰.������ǰĿ¼�µ�
java class��Ȼ�����������С� 

11.�ܽ�
��ѧJava������һ��Ҫ����classpath��codebase�ĸ��������ں���������ֱ��롢���г���Ĺ����г���ʲô���⣬
����Ӧ�ÿ����Ƿ���classpath�����⡣ʵ���ϣ�����һЩ�ǳ����ӵ�Ӧ���У������������Ӧ�ã�������Ϊclasspath��
���ö�����Ī����������⡣���������Ķ����ĵĹ�������ʲô���⣬����������һ�����ۡ� 
 
 
