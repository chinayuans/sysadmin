���¼��������ص��࣬��Ҫ��JavaԴ����ú���⣺
ClassLoader, Process,Runtime,System

Java·���������ս������֮һ[ת]2007-06-22 13:21        ǰ��

����Java��·�����⣬�ǳ��Ѹ㡣����Ĺ����漰�������Ͷ�ȡ�ļ��Ĺ����������Ҿ͸���ҳ��׵ý��Java·�����⡣

�����ұ�д��һ����������ClassLoader.getResource(String ���·��)������������ǿ�������Խ��ܡ�../�������Ĳ������������������·������λclasspath�������Դ�����������ǾͿ���ʹ�������classpath��·������λ����λ�õ���Դ��

����Java·��

����Java��ʹ�õ�·������Ϊ���֣�����·�������·����������ԣ��ַ�Ϊ���֣�

����һ��URI��ʽ�ľ�����Դ·��

�����磺file:/D:/java/eclipse32/workspace/jbpmtest3/bin/aaa.b

����URL��URI��������URL��ǰ׺/Э�飬������Java��ʶ�ġ�URL���Դ���Դ����URI���С�

����URL��URI������Ի���ת����ʹ�ø��Ե�toURI(),toURL()�������ɣ�

������������ϵͳ�ľ���·��

����D:/java/eclipse32/workspace/jbpmtest3/bin/aaa.b

����Java.io���е��࣬��Ҫʹ��������ʽ�Ĳ�����

�������ǣ�����һ��Ҳ�ṩ��URI���͵Ĳ�������URI���͵Ĳ��������ܵ���URI��ʽ��String����ˣ�ͨ��URIת�������ǿ��԰�URI��ʽ�ľ���·������java.io���е����С�

�������������classpath�����·��

�����磺�����

����file:/D:/java/eclipse32/workspace/jbpmtest3/bin/���·�������·�������У�bin�Ǳ���Ŀ��classpath�����е�JavaԴ�ļ�������.class�ļ����Ƶ����Ŀ¼�С�

�����ġ�����ڵ�ǰ�û�Ŀ¼�����·��

�������������System.getProperty("user.dir")���ص�·����

��������һ����Ŀ��������Ŀ�ĸ�·��������JavaEE��������������Ƿ�������ĳ��·���������û��ͳһ�Ĺ淶��

�������ԣ����Բ�Ҫʹ�á�����ڵ�ǰ�û�Ŀ¼�����·������Ȼ����

����Ĭ������£�java.io ���е������Ǹ��ݵ�ǰ�û�Ŀ¼���������·��������Ŀ¼��ϵͳ���� user.dir ָ����ͨ���� Java ������ĵ���Ŀ¼��

���������˵����ʹ��java.io���е���ʱ����ò�Ҫʹ�����·����������Ȼ��J2SEӦ�ó����п��ܻ������������ǵ���J2EE�����У�һ��������⣡�������·�����ڲ�ͬ�ķ������ж��ǲ�ͬ�ģ�

�������·�����ʵ��

�����Ƽ�ʹ������ڵ�ǰclasspath�����·��

������ˣ�������ʹ�����·��ʱ��Ӧ��ʹ������ڵ�ǰclasspath�����·����

����ClassLoader���getResource(String name),getResourceAsStream(String name)�ȷ�����ʹ������ڵ�ǰ��Ŀ��classpath�����·����������Դ��

������ȡ�����ļ����õ���ResourceBundle���getBundle(String path)Ҳ����ˡ�

����ͨ���鿴ClassLoader�༰��������Դ���룬�ҷ��֣���ʵ���ϻ���ʹ����URI��ʽ�ľ���·����ͨ���õ���ǰclasspath��URI��ʽ�ľ���·�������������·����URI��ʽ�ľ���·���������ʵ�����ǲ��룬��ΪJDK�ڲ�������SUN��Դ���룬����Щ���벻����JDK�����ǿ�Դ�ġ���

�������·�������ϻ��Ǿ���·��

������ˣ������ף�Java������ֻ��ʹ�þ���·����Ѱ����Դ�����е����·��Ѱ����Դ�ķ�������������һЩ����������������API�ڵײ�������ǹ����˾���·�����Ӷ��ҵ���Դ�ģ�

�����õ�classpath�͵�ǰ��ľ���·����һЩ����

����������һЩ�õ�classpath�͵�ǰ��ľ���·����һЩ�������������Ҫʹ�����е�һЩ�������õ�����Ҫ����Դ�ľ���·����

����1.FileTest.class.getResource("")

�����õ����ǵ�ǰ��FileTest.class�ļ���URIĿ¼���������Լ���

�����磺file:/D:/java/eclipse32/workspace/jbpmtest3/bin/com/test/

����2.FileTest.class.getResource("/")

�����õ����ǵ�ǰ��classpath�ľ���URI·����

�����磺file:/D:/java/eclipse32/workspace/jbpmtest3/bin/

����3.Thread.currentThread().getContextClassLoader().getResource("")

�����õ���Ҳ�ǵ�ǰClassPath�ľ���URI·����

�����磺file:/D:/java/eclipse32/workspace/jbpmtest3/bin/

����4.FileTest.class.getClassLoader().getResource("")

�����õ���Ҳ�ǵ�ǰClassPath�ľ���URI·����

�����磺file:/D:/java/eclipse32/workspace/jbpmtest3/bin/

����5.ClassLoader.getSystemResource("")

�����õ���Ҳ�ǵ�ǰClassPath�ľ���URI·����

�����磺file:/D:/java/eclipse32/workspace/jbpmtest3/bin/

�������Ƽ�ʹ��Thread.currentThread().getContextClassLoader().getResource("")���õ���ǰ��classpath�ľ���·����URI��ʾ����

����WebӦ�ó�������Դ��Ѱַ

����������˵������ǰ�û�Ŀ¼���������System.getProperty("user.dir")���ص�·����

��������JavaEE��������������Ƿ�������ĳ��·���������û��ͳһ�Ĺ淶��

�������������Ƿ�����WebӦ�ó���ĸ�Ŀ¼��

������������WebӦ�ó����У����Ǿ��Բ���ʹ������ڵ�ǰ�û�Ŀ¼�����·����

������WebӦ�ó����У�����һ��ͨ��ServletContext.getRealPath("/")�����õ�WebӦ�ó���ĸ�Ŀ¼�ľ���·����

��������������ֻ��Ҫ�ṩ�����WebӦ�ó����Ŀ¼��·�����Ϳ��Թ�������λ��Դ�ľ���·����

�����������ǿ���WebӦ�ó���ʱһ������ȡ�Ĳ��ԡ� 

����ͨ�õ����·������취

����Java�и������·���ǳ��࣬������ʹ�ã��ǳ����׳�����ˣ��ұ�д��һ���������������������׵Ľ�����·�����⡣

����WebӦ�ó�����ʹ��JavaSE���е���ԴѰַ����

������JavaSE�����У�����һ��ʹ��classpath����Ϊ�����Դ��Ŀ�ĵء����ǣ���WebӦ�ó����У�����һ��ʹ��classpath�����WEB-INF������Ŀ¼��Ϊ��Դ�ļ��Ĵ�ŵء�

������WebӦ�ó����У�����һ��ͨ��ServletContext.getRealPath("/")�����õ�WebӦ�ó���ĸ�Ŀ¼�ľ���·��������������ֻ��Ҫ�ṩ�����WebӦ�ó����Ŀ¼��·�����Ϳ��Թ�������λ��Դ�ľ���·����

����WebӦ�ó��򣬿�����ΪWebӦ�ó�����з��������С����ǣ�����Ҳ��������JavaSE�ķ�ʽ������WebӦ�ó����ĳ�����main���������ߣ�ʹ��JUnit���ԡ��ⶼ��Ҫʹ��JavaSE�ķ�ʽ�����С�

�������������Ǿ��޷�ʹ��ServletContext.getRealPath("/")�����õ�WebӦ�ó���ĸ�Ŀ¼�ľ���·����

������JDK�ṩ��ClassLoader�࣬����getResource(String name),getResourceAsStream(String name)�ȷ�����ʹ������ڵ�ǰ��Ŀ��classpath�����·����������Դ��

������ȡ�����ļ����õ���ResourceBundle���getBundle(String path)Ҳ����ˡ�

�������Ƕ�ֻ��ʹ�����·������ȡclasspath�µ���Դ���޷���λ��classpath�������Դ��

����Classpath�������ļ���ȡ����

�����磬����ʹ�ò������������ķ���������Spring��Hibernate��iBatis��ʹ�������ļ���WebӦ�ó��򣬾ͻ��������⡣

��������Spring�Լ��ṩ��FileSystem��Ҳ���������user,dirĿ¼������ȡWeb�����ļ��ķ����������վ����Ǻܷ��㡣������Web�����еĴ���ʹ�÷�ʽ��һ�£�

��������Hibernate��iBatis�͸��鷳�ˣ�ֻ�а������ļ��Ƶ�classpath�£��������������ʹ�ò�������������

��������ô�죿

����ͨ�õ����·������취

�������������⣬�Ҿ�����дһ��������ClassLoaderUtil���ṩһ����������[public static URL getExtendResource(String relativePath)]����WebӦ�ó����һ��Java�����У���Ҫ��λclasspath�����Դʱ����ʹ�����������ı�������������ʹ��WebӦ�ó������е�ServletContext.getRealPath("/")��������λ��Դ��

��������classpath�ľ���·������λ������Դ

�����������������ʵ��ԭ�����ǡ�����classpath�ľ���·������λ������Դ����

����ClassLoader���getResource("")�����ܹ��õ���ǰclasspath�ľ���·������������Java����ӵ�е�����������������Ӧ�ԣ�
��Ŀǰ��JDK�ṩ��ClassLoader���getResource(String ���·��)������ֻ�ܽ���һ������·����������ʹ��ClassLoader���getResource(String ���·��)������ֻ�ܶ�λ��classpath�µ���Դ��

������������ܹ����ܡ�../�������Ĳ������������������·������λclasspath�������Դ����ô���ǾͿ��Զ�λλ�õ���Դ��

������Ȼ�����޷��޸�ClassLoader���������������ǣ��ұ�д��һ��������ClassLoaderUtil�࣬�ṩ��[public static URL getExtendResource(String relativePath)]������������ܹ����ܴ��С�../�����ŵ����·����ʵ��������Ѱ����Դ�Ĺ��ܡ�

����ͨ�����classpath·��ʵ������Ѱ����Դ���������Դ���룺

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
*@author����shendl_s@hotmail.com
*Nov29,2006 10:34:34AM
*���������࣬classpath �µ���Դ�ļ��������ļ��ȡ�
*getExtendResource(StringrelativePath)����������ʹ��../����������classpath�ⲿ����Դ��
*/
publicclass ClassLoaderUtil {
��privatestatic Log log=LogFactory.getLog(ClassLoaderUtil.class);
��/**
��*Thread.currentThread().getContextClassLoader().getResource("")
��*/

��/**
��*����Java�ࡣ ʹ��ȫ�޶�����
��*@paramclassName
��*@return
��*/
��publicstatic Class loadClass(String className) {
����try {
������return getClassLoader().loadClass(className);
����} catch (ClassNotFoundException e) {
������thrownew RuntimeException("class not found '"+className+"'", e);
����}
��}
��/**
��*�õ��������
��*@return
��*/
��publicstatic ClassLoader getClassLoader() {
����return ClassLoaderUtil.class.getClassLoader();
��}

��/**
��*�ṩ�����classpath����Դ·���������ļ���������
��*@paramrelativePath���봫����Դ�����·�����������classpath��·���������Ҫ����classpath�ⲿ����Դ����Ҫʹ�á�../������
��*@return �ļ�������
��*@throwsIOException
��*@throwsMalformedURLException
��*/
��publicstatic InputStream getStream(String relativePath) throws MalformedURLException, IOException {
����if(!relativePath.contains("../")){
������return getClassLoader().getResourceAsStream(relativePath);
����}else{
������return ClassLoaderUtil.getStreamByExtendResource(relativePath);
����}
��}
��/**
��*
��*@paramurl
��*@return
��*@throwsIOException
��*/
��publicstatic InputStream getStream(URL url) throws IOException{
����if(url!=null){
������return url.openStream();
����}else{
������returnnull;
����}
��}
��/**
��*
��*@paramrelativePath���봫����Դ�����·�����������classpath��·���������Ҫ����classpath�ⲿ����Դ����Ҫʹ�á�../������
��*@return
��*@throwsMalformedURLException
��*@throwsIOException
��*/
��publicstatic InputStream getStreamByExtendResource(String relativePath) throws MalformedURLException, IOException{
����return ClassLoaderUtil.getStream(ClassLoaderUtil.getExtendResource(relativePath));
��}

��/**
��*�ṩ�����classpath����Դ·�����������Զ�������һ��ɢ�б�
��*@paramresource
��*@return
��*/
��publicstatic Properties getProperties(String resource) {
����Properties properties = new Properties();
����try {
������properties.load(getStream(resource));
����} catch (IOException e) {
������thrownew RuntimeException("couldn't load properties file '"+resource+"'", e);
����}
����return properties;
��}
��/**
��*�õ���Class���ڵ�ClassLoader��Classpat�ľ���·����
��*URL��ʽ��
��*@return
��*/
��publicstatic String getAbsolutePathOfClassLoaderClassPath(){
����ClassLoaderUtil.log.info(ClassLoaderUtil.getClassLoader().getResource("").toString());
����return ClassLoaderUtil.getClassLoader().getResource("").toString();
��}
��/**
��*
��*@paramrelativePath ���봫����Դ�����·�����������classpath��·���������Ҫ����classpath�ⲿ����Դ����Ҫʹ����../������
��*@return��Դ�ľ���URL
��*@throwsMalformedURLException
��*/
��publicstatic URL getExtendResource(String relativePath) throws MalformedURLException{
����ClassLoaderUtil.log.info("��������·����"+relativePath) ;
����//ClassLoaderUtil.log.info(Integer.valueOf(relativePath.indexOf("../"))) ;
����if(!relativePath.contains("../")){
������return ClassLoaderUtil.getResource(relativePath);
����}
����String classPathAbsolutePath=ClassLoaderUtil.getAbsolutePathOfClassLoaderClassPath();
����if(relativePath.substring(0, 1).equals("/")){
������relativePath=relativePath.substring(1);
����}
����ClassLoaderUtil.log.info(Integer.valueOf(relativePath.lastIndexOf("../"))) ;
����String wildcardString=relativePath.substring(0,relativePath.lastIndexOf("../")+3);
����relativePath=relativePath.substring(relativePath.lastIndexOf("../")+3);
����int containSum=ClassLoaderUtil.containSum(wildcardString, "../");
����classPathAbsolutePath= ClassLoaderUtil.cutLastString(classPathAbsolutePath, "/", containSum);
����String resourceAbsolutePath=classPathAbsolutePath+relativePath;
����ClassLoaderUtil.log.info("����·����"+resourceAbsolutePath) ;
����URL resourceAbsoluteURL=new URL(resourceAbsolutePath);
����return resourceAbsoluteURL;
��}
��/**
��*
��*@paramsource
��*@paramdest
��*@return
��*/
��privatestaticint containSum(String source,String dest){
����int containSum=0;
����int destLength=dest.length();
����while(source.contains(dest)){
������containSum=containSum+1;
������source=source.substring(destLength);
����}
����return containSum;
��}
��/**
��*
��*@paramsource
��*@paramdest
��*@paramnum
��*@return
��*/
��privatestatic String cutLastString(String source,String dest,int num){
����// String cutSource=null;
����for(int i=0;i<num;i++){
������source=source.substring(0, source.lastIndexOf(dest, source.length()-2)+1);
����}
����return source;
��}
��/**
��*
��*@paramresource
��*@return
��*/
��publicstatic URL getResource(String resource){
����ClassLoaderUtil.log.info("����������classpath��·����"+resource) ;
����return ClassLoaderUtil.getClassLoader().getResource(resource);
��}
��/**
��*@paramargs
��*@throwsMalformedURLException
��*/
��publicstaticvoid main(String[] args) throws MalformedURLException {
����//ClassLoaderUtil.getExtendResource("../spring/dao.xml");
����//ClassLoaderUtil.getExtendResource("../../../src/log4j.properties");
����ClassLoaderUtil.getExtendResource("log4j.properties");
����System.out.println(ClassLoaderUtil.getClassLoader().getResource("log4j.properties").toString());
��}
} 

�������

����ClassLoaderUtil���public static URL getExtendResource(String relativePath)����Ȼ�ܼ򵥣�����ȷʵ���Խ�������⡣

������������������ǱȽϼ�ª�ġ��һ�����δ���п�ʱ����һ����ǿ�������������磬����Ant����ƥ�������**������Ŀ¼��*�������ַ���������һ���ַ����ﵽSpring������������һ�η��ض����Դ��URL����һ�������ҿ�����

�����ܽ᣺

����1.������Ҫʹ�������System.getProperty("user.dir")��ǰ�û�Ŀ¼�����·��������һ�Ŷ�ʱը������ʱ����Ҫ�������

����2.����ʹ��URI��ʽ�ľ���·����Դ�������Ժ����׵�ת��ΪURI,URL��File����

����3.����ʹ�����classpath�����·������Ҫʹ�þ���·����ʹ������ClassLoaderUtil���public static URL getExtendResource(String relativePath)�����Ѿ��ܹ�ʹ�������classpath�����·����λ����λ�õ���Դ��

����4.���Բ�Ҫʹ��Ӳ����ľ���·������Ϊ��������ȫ����ʹ��ClassLoader���getResource("")�����õ���ǰclasspath�ľ���·����
ʹ��Ӳ����ľ���·������ȫû�б�Ҫ�ģ���һ�����������ĺ��ѿ��������޷���ֲ��

���������һ��Ҫָ��һ������·������ôʹ�������ļ���Ҳ��Ӳ����Ҫ�õö࣡ 

������Ȼ���һ����Ƽ���ʹ�ó���õ�classpath�ľ���·����ƴ��Դ�ľ���·���� 
