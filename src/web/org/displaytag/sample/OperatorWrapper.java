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
	 /*<!-- 我认为button风格比较href要好，因为更加灵活。再javascript中有
     <c:out value='${pageContext.request.requestURL}'/>的使用，
     但是href   user.do写死了。
     -->
     */
     
	/**
	 * displayTag中用来对行记录做编辑修改的方法.
	 * 用来形成   查看 | 编辑 | 删除.
	 * 注意的是命名规则: 
	 *  /user.do   --->对应的列表显示所有记录的页面必须命名为 /user_ListAll.jsp
	 *  上面的user可以更换成任意字符串.
	 *  
	 * 使用方法:
	 * <display:table name="data" 
	 *           decorator="org.displaytag.sample.OperatorWrapper">
	 *     <display:column property="operator"  title="操作类型" />
	 * </display:table>

	 * @return
	 * Created on 2005-7-27 20:36:02
	 * @author ---Joson Yuan
	 * author comments:
	 * 不是很灵活，暂时不用。
	 * @deprecated
	 */
	
	public String getView_Edit_Delete(){
		BaseObject object = (BaseObject) getCurrentRowObject();
        String id = object.getId();
        HttpServletRequest request=(HttpServletRequest)getPageContext().getRequest();
        String requestURI=request.getRequestURI();
        String ContextPath=request.getContextPath();//得到 /webapplication
        String URI_Part1=requestURI.substring(requestURI.lastIndexOf("/"));//得到 /user_ListAll.jsp
        
        String URI_Part2=URI_Part1.substring(0,URI_Part1.lastIndexOf("_ListAll.jsp"));//得到/user
        String URI_Last_Part=ContextPath+URI_Part2+".do"; //得到/webapplication/user.do
        
        String output="<a href=\""+URI_Last_Part+"?id=" //$NON-NLS-1$
			        + id + "&method=detailOne\">查看</a> | " //$NON-NLS-1$
			        + "<a href=\""+URI_Last_Part+"?id=" //$NON-NLS-1$
			        + id + "&method=preUpdateOne\">编辑</a> | " //$NON-NLS-1$
			        + "<a href=\""+URI_Last_Part+"?id=" //$NON-NLS-1$
			        + id + "&method=removeOne\">删除</a>"; //$NON-NLS-1$
        
        
//        System.out.println("requestURI:"+requestURI);
//        System.out.println("URI_Last_Part:"+URI_Last_Part);
//        System.out.println(output);
        return output;
	}
	
	/**
	 * displayTag中用来对行记录做编辑修改的方法.
	 * 用来形成  增加 | 查看 | 编辑 | 删除.
	 * 注意的是命名规则: 
	 *  /user.do   --->对应的列表显示所有记录的页面必须命名为 /user_ListAll.jsp
	 *  上面的user可以更换成任意字符串.
	 *  
	 * 使用方法:
	 * <display:table name="data" 
	 *           decorator="org.displaytag.sample.OperatorWrapper">
	 *     <display:column property="operator"  title="操作类型" />
	 * </display:table>

	 * @return
	 * Created on 2005-7-27 20:36:02
	 * @author ---Joson Yuan
	 * author comments:
	 * 不是很灵活，暂时不用。
	 * @deprecated
	 */
	public String getHref(){
		BaseObject object = (BaseObject) getCurrentRowObject();
        String id = object.getId();
        HttpServletRequest request=(HttpServletRequest)getPageContext().getRequest();
        String requestURI=request.getRequestURI();
        String ContextPath=request.getContextPath();//得到 /webapplication
        String URI_Part1=requestURI.substring(requestURI.lastIndexOf("/"));//得到 /user_ListAll.jsp
        
        String URI_Part2=URI_Part1.substring(0,URI_Part1.lastIndexOf("_ListAll.jsp"));//得到/user
        String URI_Last_Part=ContextPath+URI_Part2+".do"; //得到/webapplication/user.do
        
        String output="<a href=\""+URI_Last_Part+"?&method=preSaveOne\">增加</a> | " //$NON-NLS-1$
        	        +"<a href=\""+URI_Last_Part+"?id=" //$NON-NLS-1$
			        + id + "&method=detailOne\">查看</a> | " //$NON-NLS-1$
			        + "<a href=\""+URI_Last_Part+"?id=" //$NON-NLS-1$
			        + id + "&method=preUpdateOne\">编辑</a> | " //$NON-NLS-1$
			        + "<a href=\""+URI_Last_Part+"?id=" //$NON-NLS-1$
			        + id + "&method=removeOne\">删除</a>"; //$NON-NLS-1$
        
        
//        System.out.println("requestURI:"+requestURI);
//        System.out.println("URI_Last_Part:"+URI_Last_Part);
//        System.out.println(output);
        return output;
	}
    
	
	/**
	 * 自定义的风格，非常好。
	 * @return
	 * Created on 2005-7-28 1:42:25
	 * @author ---Joson Yuan
	 * author comments:
	 *
	 */
	public String getButton(){
		BaseObject object = (BaseObject) getCurrentRowObject();
        String id = object.getId();
		String output="<button onclick=\"common_add();\">增加</button>"+
		              "<button onclick=\"common_view('"+id+"');\">查看</button>"+
		              "<button onclick=\"common_edit('"+id+"');\">编辑</button>"+
		              "<button onclick=\"common_delete('"+id+"');\">删除</button>";
		return output;
	}
}
