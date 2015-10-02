/*
 * project name : equinox-self
 * package name : org.appfuse.web
 * file    name : UserActionTest.java
 * class   name : UserActionTest
 * Created on 2005-7-22 18:44:31
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.web.sysadmin;

import java.util.List;

import org.appfuse.web.base.BaseActionTest;

// 需要struts-config.xml文件，***Action文件，***Form文件。
public class MenuItemActionTest extends BaseActionTest {


	public MenuItemActionTest(String testName) {
		super(testName);
		
	}

	public void setUp() throws Exception {
		super.setUp();
	}

	public void testRemovAll(){
		setRequestPathInfo("/menuItem");
		addRequestParameter("method", "removeAll");
		actionPerform();
		verifyNoActionErrors();
	}
	
	public void testSaveOne() {
		setRequestPathInfo("/menuItem");
		addRequestParameter("method", "saveOne");
		addRequestParameter("menuItem.name", "menu1");
		addRequestParameter("menuItem.title", "菜单1");
		actionPerform();
		verifyNoActionErrors();
		verifyForward("success");
	}

	
	public void testUpdateOne() {
		
		//先增加后更新.
		
		setRequestPathInfo("/menuItem");
		addRequestParameter("method", "saveOne");
		addRequestParameter("menuItem.name", "menu2");
		addRequestParameter("menuItem.title", "Johnson");
		actionPerform();
		
		
		
		setRequestPathInfo("/menuItem");
		addRequestParameter("method", "updateOne");
		addRequestParameter("menuItem.name", "menu2");
		addRequestParameter("menuItem.title", "菜单2");
		actionPerform();
		verifyNoActionErrors();
		
	}

	
	public void testSaveOrUpdateOne() {
		//根据所给的数据,到数据库中去比较,自动判断是增加还是更新数据.
		
		
		setRequestPathInfo("/menuItem");
		addRequestParameter("method", "saveOrUpdateOne");
		addRequestParameter("menuItem.name", "chinayuan");
		addRequestParameter("menuItem.title", "hello");
		actionPerform();
		
		setRequestPathInfo("/menuItem");
		addRequestParameter("method", "saveOrUpdateOne");
		addRequestParameter("menuItem.name", "chinayuan");
		addRequestParameter("menuItem.title", "chinayuan");
		
		actionPerform();
		
	}

	public void testRemoveOne() {
		//删除id=1的记录.
		
//		不好测试,因为没有得到id值.
		
//		setRequestPathInfo("/menuItem");
//		addRequestParameter("method", "removeOne");
//		addRequestParameter("id", "1");
//		actionPerform();
//		verifyNoActionErrors();
		
	}

	// 根据id获得menuItem对象
	public void testDetailOne() {
		
//		不好测试,因为没有得到id值.
//		setRequestPathInfo("/menuItem");
//		addRequestParameter("id", "2");
//		addRequestParameter("method", "detailOne");
//		actionPerform();
//		verifyForward("detailOne");
//		verifyNoActionErrors();
	}
	
	
	public void testQueryStandard() {
		setRequestPathInfo("/menuItem");		
		addRequestParameter("method", "queryStandard");
		getRequest().setAttribute("query_condition",
				"from MenuItem where id<>'1'");
		actionPerform();
		verifyForward("success");
		verifyNoActionErrors();
	}

	// 获得所有的menuItem对象
	public void testQueryAll() {
	
		setRequestPathInfo("/menuItem");
		addRequestParameter("method", "queryAll");
		actionPerform();
		verifyForward("listAll");
		verifyNoActionErrors();
	
		List menuItems = (List) getRequest().getAttribute("data");
		assertNotNull(menuItems);
	
	}

}
