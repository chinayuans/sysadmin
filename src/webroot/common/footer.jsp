<!--�����������-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<!--       ��header.jsp�ļ�һ��ʹ�� 
    ������������ȫ���رյ������,�鿴Դ����Ų�����ʾ
  -->
<c:set var="view_jsp_sourceCode" value="on"/>

<c:if test="${view_jsp_sourceCode=='on' or initParam.view_jsp_sourceCode=='true'}" >
    <ul id="showsource">
            <li><a href="<%=request.getRequestURI()%>.source">�鿴ҳ��Դ����</a></li>
    </ul>
</c:if>



<!--

<table border="0" >
<tr>
  <td align="left">
        <div >
            
            <h3>  
             <table border="0" cellpadding="0" >
               <tr >
                   <td >      
                      <a href="mailto:chinayuans@163.com">������κ�������������ϵ</a>
                   </td>
               </tr>
               <tr> 
                   <td >
                    <a href="#" onclick="toggleDisplay('readme'); return false">�鿴˵���ĵ�</a>
                    </td >
                </tr>
             </table>
            </h3>
        
        </div>
   </td>
   <td align="right">
        <div >
          <img src="images/footer.jpg" 
               height="150"   
               width="250"
               align="right"/>
        </div>
   </td>
</tr>
<tr>
  <td colspan='2'>
        <div id="readme" style="display:none;margin-left: 10px;margin-top: 0;" >
            <%-- Include the README.txt --%>
          
            <pre>
               
                <c:import url="/readme.txt"/>
            </pre>
          
        </div>
  </td>
</tr>
</table>

-->
 <!--jsp:include page="/readme.txt"/-->


