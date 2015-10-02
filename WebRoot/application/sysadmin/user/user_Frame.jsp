
<frameset  id="mainFrame" name="mainFrame" rows="0,*" frameborder="no" >
    <frame id="data" 
           name="data" 
           src="<%=request.getContextPath()%>/common/wait_next.html"
           >
    </frame>
   
    <frame id="listing" 
           name="listing" 
           src="<%=request.getContextPath()%>/user.do?method=queryAll"
           scrolling="auto" 
           frameborder="0" 
           >
    </frame>
    
    
</frameset>

