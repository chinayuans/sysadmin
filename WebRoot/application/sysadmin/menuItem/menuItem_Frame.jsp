<!-- css files  -->
<link rel="stylesheet" type="text/css" media="screen, print" href="<%=request.getContextPath()%>/css/screen.css'/>" /> 


<script type="text/javascript">
<!--
function move(){
  //alert("xx");
  //document.all["center"].height="10";
}
//-->
</script>
<frameset  id="mainFrame" name="mainFrame" rows="0,*" frameborder="no" >
    <frame id="data" 
           name="data" 
           src="<%=request.getContextPath()%>/common/wait_next.html"
           >
    </frame>
   
    <frame id="listing" 
           name="listing" 
           src="<%=request.getContextPath()%>/menuItem.do?method=queryAll"
           scrolling="auto" 
           onmove="move();"
           frameborder="0" 
           bordercolor="green"
           >
    </frame>
    
    
</frameset>

