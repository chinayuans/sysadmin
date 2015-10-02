<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>

<%@ include file="/common/header.jsp" %>
<html>
<head><title>初始化数据页面</title></head>
<body >
<sql:setDataSource var="db" 
    url="jdbc:mysql://localhost/equinox?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=gb2312"
    driver="com.mysql.jdbc.Driver" user="root" password=""/>

<sql:transaction dataSource="${db}">
    <sql:update var="updateCount">
       delete from t_menu_item
    </sql:update>
    
    <sql:update var="updateCount">
        INSERT INTO t_menu_item
            (id, name, title)
        VALUES
            (1,'DatabaseMenu','Database Menu')
    </sql:update>
    <sql:update var="updateCount">
        INSERT INTO t_menu_item
            (id, parent_name, name, title, location)
        VALUES
            (2,'DatabaseMenu','Yahoo','Yahoo Mail','http://mail.yahoo.com')
    </sql:update>
    <sql:update var="updateCount">
        INSERT INTO t_menu_item
            (id, parent_name, name, title, location)
        VALUES
            (3,'DatabaseMenu','JavaBlogs','JavaBlogs','http://javablogs.com')
    </sql:update>
    <sql:update var="updateCount">
        INSERT INTO t_menu_item
            (id, name, title, location)
        VALUES
            (4,'StandaloneMenu','Standalone Menu','http://raibledesigns.com')
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
    <display:column property="action"/>
</display-el:table>





</body>
</html>
<%@ include file="/common/footer.jsp" %>