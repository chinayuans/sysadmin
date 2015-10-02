/*
 * project name : equinox-self
 * package name : org.displaytag.sample
 * file    name : OperatorWrapper.java
 * class   name : OperatorWrapper
 * Created on 2005-7-27 17:39:09
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.displaytag.sample;

import javax.servlet.http.HttpServletRequest;

import org.appfuse.model.base.BaseObject;
import org.displaytag.decorator.TableDecorator;

public class OperatorWrapper extends TableDecorator {
	public OperatorWrapper(){
		super();
	}
	 /*<!-- ����Ϊbutton���Ƚ�hrefҪ�ã���Ϊ��������javascript����
     <c:out value='${pageContext.request.requestURL}'/>��ʹ�ã�
     ����href   user.doд���ˡ�
     -->
     */
     
	/**
	 * displayTag���������м�¼���༭�޸ĵķ���.
	 * �����γ�   �鿴 | �༭ | ɾ��.
	 * ע�������������: 
	 *  /user.do   --->��Ӧ���б���ʾ���м�¼��ҳ���������Ϊ /user_ListAll.jsp
	 *  �����user���Ը����������ַ���.
	 *  
	 * ʹ�÷���:
	 * <display:table name="data" 
	 *           decorator="org.displaytag.sample.OperatorWrapper">
	 *     <display:column property="operator"  title="��������" />
	 * </display:table>

	 * @return
	 * Created on 2005-7-27 20:36:02
	 * @author ---Joson Yuan
	 * author comments:
	 * ���Ǻ�����ʱ���á�
	 * @deprecated
	 */
	
	public String getView_Edit_Delete(){
		BaseObject object = (BaseObject) getCurrentRowObject();
        String id = object.getId();
        HttpServletRequest request=(HttpServletRequest)getPageContext().getRequest();
        String requestURI=request.getRequestURI();
        String ContextPath=request.getContextPath();//�õ� /webapplication
        String URI_Part1=requestURI.substring(requestURI.lastIndexOf("/"));//�õ� /user_ListAll.jsp
        
        String URI_Part2=URI_Part1.substring(0,URI_Part1.lastIndexOf("_ListAll.jsp"));//�õ�/user
        String URI_Last_Part=ContextPath+URI_Part2+".do"; //�õ�/webapplication/user.do
        
        String output="<a href=\""+URI_Last_Part+"?id=" //$NON-NLS-1$
			        + id + "&method=detailOne\">�鿴</a> | " //$NON-NLS-1$
			        + "<a href=\""+URI_Last_Part+"?id=" //$NON-NLS-1$
			        + id + "&method=preUpdateOne\">�༭</a> | " //$NON-NLS-1$
			        + "<a href=\""+URI_Last_Part+"?id=" //$NON-NLS-1$
			        + id + "&method=removeOne\">ɾ��</a>"; //$NON-NLS-1$
        
        
//        System.out.println("requestURI:"+requestURI);
//        System.out.println("URI_Last_Part:"+URI_Last_Part);
//        System.out.println(output);
        return output;
	}
	
	/**
	 * displayTag���������м�¼���༭�޸ĵķ���.
	 * �����γ�  ���� | �鿴 | �༭ | ɾ��.
	 * ע�������������: 
	 *  /user.do   --->��Ӧ���б���ʾ���м�¼��ҳ���������Ϊ /user_ListAll.jsp
	 *  �����user���Ը����������ַ���.
	 *  
	 * ʹ�÷���:
	 * <display:table name="data" 
	 *           decorator="org.displaytag.sample.OperatorWrapper">
	 *     <display:column property="operator"  title="��������" />
	 * </display:table>

	 * @return
	 * Created on 2005-7-27 20:36:02
	 * @author ---Joson Yuan
	 * author comments:
	 * ���Ǻ�����ʱ���á�
	 * @deprecated
	 */
	public String getHref(){
		BaseObject object = (BaseObject) getCurrentRowObject();
        String id = object.getId();
        HttpServletRequest request=(HttpServletRequest)getPageContext().getRequest();
        String requestURI=request.getRequestURI();
        String ContextPath=request.getContextPath();//�õ� /webapplication
        String URI_Part1=requestURI.substring(requestURI.lastIndexOf("/"));//�õ� /user_ListAll.jsp
        
        String URI_Part2=URI_Part1.substring(0,URI_Part1.lastIndexOf("_ListAll.jsp"));//�õ�/user
        String URI_Last_Part=ContextPath+URI_Part2+".do"; //�õ�/webapplication/user.do
        
        String output="<a href=\""+URI_Last_Part+"?&method=preSaveOne\">����</a> | " //$NON-NLS-1$
        	        +"<a href=\""+URI_Last_Part+"?id=" //$NON-NLS-1$
			        + id + "&method=detailOne\">�鿴</a> | " //$NON-NLS-1$
			        + "<a href=\""+URI_Last_Part+"?id=" //$NON-NLS-1$
			        + id + "&method=preUpdateOne\">�༭</a> | " //$NON-NLS-1$
			        + "<a href=\""+URI_Last_Part+"?id=" //$NON-NLS-1$
			        + id + "&method=removeOne\">ɾ��</a>"; //$NON-NLS-1$
        
        
//        System.out.println("requestURI:"+requestURI);
//        System.out.println("URI_Last_Part:"+URI_Last_Part);
//        System.out.println(output);
        return output;
	}
    
	
	/**
	 * �Զ���ķ�񣬷ǳ��á�
	 * @return
	 * Created on 2005-7-28 1:42:25
	 * @author ---Joson Yuan
	 * author comments:
	 *
	 */
	public String getButton(){
		BaseObject object = (BaseObject) getCurrentRowObject();
        String id = object.getId();
		String output="<button onclick=\"common_add();\">����</button>"+
		              "<button onclick=\"common_view('"+id+"');\">�鿴</button>"+
		              "<button onclick=\"common_edit('"+id+"');\">�༭</button>"+
		              "<button onclick=\"common_delete('"+id+"');\">ɾ��</button>";
		return output;
	}
}
