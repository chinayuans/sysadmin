---------------------------
此目录下为sitemesh组件的使用:
---------------------------
安装sitemesh步骤:
1.copy到WEB-INF/lib中的文件有:sitemesh.jar
2.copy到WEB-INF/taglibs中的文件有:sitemesh-decorator.tld,sitemesh-page.tld文件
3.copy到WEB-INF/文件有:sitemesh.xml (可选)decorators.xml  
4.修改web.xml:
   <!-- Sitemesh过滤器 -->
    <filter>
        <filter-name>sitemesh</filter-name>
        <filter-class>com.opensymphony.module.sitemesh.filter.PageFilter</filter-class>
    </filter>
     <!-- Sitemesh过滤器 -->
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
5.修改common_taglibs.jsp:
  <!-- Sitemesh Tag Library -->
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
    
6.建立目录/decorators:
  此目录下保存sitemesh的模板文件.
  
--------------  
注意事项:  
--------------
1.该目录下的default.jsp为 /util/*.jsp的模板文件,
  也就是请求得到/util/中的任何一个jsp文件都会被 
  default.jsp这个文件修饰,得到修饰后的页面.
2.该目录下的maindecorator.jsp为一个综合使用sitemesh的例子:
  这个页面里面使用两次decorator做法:
  第一个: 装饰器1:decorator1(/decorators/decorator1.jsp)
        被装饰页面1:/decorators/decoratee/decoratee1.jsp
  第二个: 装饰器2:decorator1(/decorators/decorator2.jsp)
        被装饰页面2:/decorators/decoratee/decoratee2.jsp
  其中decorator1,decorator2在/web-inf/sitemes-decorators.xml中定义了.
        
