---------------------------
��Ŀ¼��Ϊsitemesh�����ʹ��:
---------------------------
��װsitemesh����:
1.copy��WEB-INF/lib�е��ļ���:sitemesh.jar
2.copy��WEB-INF/taglibs�е��ļ���:sitemesh-decorator.tld,sitemesh-page.tld�ļ�
3.copy��WEB-INF/�ļ���:sitemesh.xml (��ѡ)decorators.xml  
4.�޸�web.xml:
   <!-- Sitemesh������ -->
    <filter>
        <filter-name>sitemesh</filter-name>
        <filter-class>com.opensymphony.module.sitemesh.filter.PageFilter</filter-class>
    </filter>
     <!-- Sitemesh������ -->
    <filter-mapping>
        <filter-name>sitemesh</filter-name>
        <url-pattern>/util/*</url-pattern>
    </filter-mapping>
    
    <!--Sitemesh Tag Library -->   
    <taglib>
        <taglib-uri>http://www.opensymphony.com/sitemesh/decorator</taglib-uri>
        <taglib-location>/WEB-INF/taglib/sitemesh-decorator.tld</taglib-location>
    </taglib>
    
    <taglib>
        <taglib-uri>http://www.opensymphony.com/sitemesh/page</taglib-uri>
        <taglib-location>/WEB-INF/taglib/sitemesh-page.tld</taglib-location>
    </taglib>
5.�޸�common_taglibs.jsp:
  <!-- Sitemesh Tag Library -->
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
    
6.����Ŀ¼/decorators:
  ��Ŀ¼�±���sitemesh��ģ���ļ�.
  
--------------  
ע������:  
--------------
1.��Ŀ¼�µ�default.jspΪ /util/*.jsp��ģ���ļ�,
  Ҳ��������õ�/util/�е��κ�һ��jsp�ļ����ᱻ 
  default.jsp����ļ�����,�õ����κ��ҳ��.
2.��Ŀ¼�µ�maindecorator.jspΪһ���ۺ�ʹ��sitemesh������:
  ���ҳ������ʹ������decorator����:
  ��һ��: װ����1:decorator1(/decorators/decorator1.jsp)
        ��װ��ҳ��1:/decorators/decoratee/decoratee1.jsp
  �ڶ���: װ����2:decorator1(/decorators/decorator2.jsp)
        ��װ��ҳ��2:/decorators/decoratee/decoratee2.jsp
  ����decorator1,decorator2��/web-inf/sitemes-decorators.xml�ж�����.
        
