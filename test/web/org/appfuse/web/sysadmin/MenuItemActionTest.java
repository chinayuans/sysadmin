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

// ��Ҫstruts-config.xml�ļ���***Action�ļ���***Form�ļ���
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
		addRequestParameter("menuItem.title", "�˵�1");
		actionPerform();
		verifyNoActionErrors();
		verifyForward("success");
	}

	
	public void testUpdateOne() {
		
		//�����Ӻ����.
		
		setRequestPathInfo("/menuItem");
		addRequestParameter("method", "saveOne");
		addRequestParameter("menuItem.name", "menu2");
		addRequestParameter("menuItem.title", "Johnson");
		actionPerform();
		
		
		
		setRequestPathInfo("/menuItem");
		addRequestParameter("method", "updateOne");
		addRequestParameter("menuItem.name", "menu2");
		addRequestParameter("menuItem.title", "�˵�2");
		actionPerform();
		verifyNoActionErrors();
		
	}

	
	public void testSaveOrUpdateOne() {
		//��������������,�����ݿ���ȥ�Ƚ�,�Զ��ж������ӻ��Ǹ�������.
		
		
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
		//ɾ��id=1�ļ�¼.
		
//		���ò���,��Ϊû�еõ�idֵ.
		
//		setRequestPathInfo("/menuItem");
//		addRequestParameter("method", "removeOne");
//		addRequestParameter("id", "1");
//		actionPerform();
//		verifyNoActionErrors();
		
	}

	// ����id���menuItem����
	public void testDetailOne() {
		
//		���ò���,��Ϊû�еõ�idֵ.
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

	// ������е�menuItem����
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
