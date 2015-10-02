<!-- css files  -->
<link rel="stylesheet" type="text/css" media="screen, print" href="<%=request.getContextPath()%>/css/screen.css'/>" /> 

<frameset  id="mainFrame" name="mainFrame" rows="0,*" frameborder="no" >
    <frame id="data" 
           name="data" 
           src="<%=request.getContextPath()%>/common/wait_next.html"
           >
    </frame>
   
    <frame id="listing" 
           name="listing" 
           src="<%=request.getContextPath()%>/department.do?method=queryAll"
           scrolling="auto"
           frameborder="0" 
           >
    </frame>
    
    
</frameset>

