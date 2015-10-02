<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>
<frameset  name="menuFrame" name="menuFrame" rows="20%,*" frameborder="no" >
    <frame name="menu"
           src="<%=request.getContextPath()%>/menuItem.do?method=getPerfectMenu"
           frameborder="1"
           >
    </frame>
   
    <frame name="display" 
           src=""
           scrolling="auto" 
           frameborder="0" 
           >
    </frame>
    
    
</frameset>