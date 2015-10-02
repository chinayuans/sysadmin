<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>

<%@ include file="/common/header.jsp" %>
<html>
<head><title>初始化数据页面</title></head>
<body >
<sql:setDataSource var="db" 
    url="jdbc:mysql://localhost/sysadmin?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=gb2312"
    driver="com.mysql.jdbc.Driver" user="root" password=""/>

<sql:transaction dataSource="${db}">
    <sql:update var="updateCount">
       delete from t_menu_item
    </sql:update>
    
    <sql:update var="updateCount">
        INSERT INTO t_menu_item
            (id, name, title,toolTip)
        VALUES
            ('1','menu_styles','menu_styles','menu_styles')
    </sql:update>
    <sql:update var="updateCount">
        INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip,target)
        VALUES
            ('2','menu_styles','Simple','Simple','/menuItem.do?method=getMenuRepository&amp;style=Simple','/menuItem.do?method=getMenuRepository&amp;style=Simple','main')
    </sql:update>
    <sql:update var="updateCount">
        INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip,target)
        VALUES
            ('3','menu_styles','DropDown','DropDown','/menuItem.do?method=getMenuRepository&amp;style=DropDown','/menuItem.do?method=getMenuRepository&amp;style=DropDown','main')
    </sql:update>
    <sql:update var="updateCount">
       INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip,target)
        VALUES
            ('4','menu_styles','CoolMenu','CoolMenu','/menuItem.do?method=getMenuRepository&amp;style=CoolMenu','/menuItem.do?method=getMenuRepository&amp;style=CoolMenu','main')
    </sql:update>
    <sql:update var="updateCount">
       INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip,target)
        VALUES
            ('5','menu_styles','CoolMenu4','CoolMenu4','/menuItem.do?method=getMenuRepository&amp;style=CoolMenu4','/menuItem.do?method=getMenuRepository&amp;style=CoolMenu4','main')
    </sql:update>
     <sql:update var="updateCount">
       INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip,target)
        VALUES
            ('6','menu_styles','ListMenu','ListMenu','/menuItem.do?method=getMenuRepository&amp;style=ListMenu','/menuItem.do?method=getMenuRepository&amp;style=ListMenu','main')
    </sql:update>
    <sql:update var="updateCount">
       INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip,target)
        VALUES
            ('7','menu_styles','TabbedMenu','TabbedMenu','/menuItem.do?method=getMenuRepository&amp;style=TabbedMenu','/menuItem.do?method=getMenuRepository&amp;style=TabbedMenu','main')
    </sql:update>
    <sql:update var="updateCount">
       INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip,target)
        VALUES
            ('8','menu_styles','XTree','XTree','/menuItem.do?method=getMenuRepository&amp;style=XTree','/menuItem.do?method=getMenuRepository&amp;style=XTree','main')
    </sql:update>  
    <sql:update var="updateCount">
       INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip,target)
        VALUES
            ('9','menu_styles','PerfectMenu','PerfectMenu','/menuItem.do?method=getPerfectMenu','/menuItem.do?method=getPerfectMenu','main')
    </sql:update>      
    <sql:update var="updateCount">
       INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip)
        VALUES
            ('10','','main_menu','main_menu','','main_menu')
    </sql:update>
    <sql:update var="updateCount">
       INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip,target)
        VALUES
            ('11','main_menu','company','company','/application/sysadmin/company/company_Frame.jsp','/application/sysadmin/company/company_Frame.jsp','main')
    </sql:update>
    <sql:update var="updateCount">
       INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip,target)
        VALUES
            ('12','main_menu','department','department','/application/sysadmin/department/department_Frame.jsp','/application/sysadmin/department/department_Frame.jsp','main')
    </sql:update>
    <sql:update var="updateCount">
       INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip,target)
        VALUES
            ('13','main_menu','menu','menu','/application/sysadmin/menuItem/menuItem_Frame.jsp','/application/sysadmin/menuItem/menuItem_Frame.jsp','main')
    </sql:update>
    <sql:update var="updateCount">
       INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip,target)
        VALUES
            ('14','main_menu','user','user','/application/sysadmin/user/user_Frame.jsp','/application/sysadmin/user/user_Frame.jsp','main')
    </sql:update>
    <sql:update var="updateCount">
       INSERT INTO t_menu_item
            (id, parent_name, name, title, page,toolTip,target)
        VALUES
            ('15','main_menu','initial_data','initial_data','/util/initial_data.jsp','/util/initial_data.jsp','main')
    </sql:update>
    
    <sql:query var="menus">
        SELECT * FROM t_menu_item order by id;
    </sql:query>

</sql:transaction>


<display-el:table name="${menus.rows}" class="list" style="width: 600px">
    <display:column property="id"/>
    <display:column property="name"/>
    <display:column property="parent_name" title="Parent Name"/>
    <display:column property="title"/>
    <display:column property="page"/>
</display-el:table>





</body>
</html>
<%@ include file="/common/footer.jsp" %>