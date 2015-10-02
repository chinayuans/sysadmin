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

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.appfuse.web.base.BaseActionTest;

// ��Ҫstruts-config.xml�ļ���***Action�ļ���***Form�ļ���
public class UserActionTest extends BaseActionTest {

	private static final Logger logger = Logger.getLogger(UserActionTest.class);

	public UserActionTest(String testName) {
		super(testName);
		if (logger.isInfoEnabled()) {
			logger.info("UserActionTest()");
		}		
	}

	public void setUp() throws Exception {
		super.setUp();		
	}

	public void testRemovAll(){
		setRequestPathInfo("/user");
		addRequestParameter("method", "removeAll");
		actionPerform();
		verifyNoActionErrors();
	}
	
	public void testSaveOne() {
		setRequestPathInfo("/user");
		addRequestParameter("method", "saveOne");
		addRequestParameter("user.user_id", "000");
		addRequestParameter("user.user_name", "Joy Bill");
		addRequestParameter("user.firstName", "Bill");
		addRequestParameter("user.lastName", "Joy");
		addRequestParameter("user.birthday",DateFormatUtils.format(new Date(System.currentTimeMillis()),"yyyy-MM-dd"));
		actionPerform();
		verifyNoActionErrors();
		verifyForward("success");
	}

	
	public void testUpdateOne() {
		
		//�����Ӻ����.
		
		setRequestPathInfo("/user");
		addRequestParameter("method", "saveOne");
		addRequestParameter("user.user_id", "001");
		addRequestParameter("user.user_name", "Johnson Rod");
		addRequestParameter("user.firstName", "Rod");
		addRequestParameter("user.lastName", "Johnson");
		actionPerform();
		
		
		
		setRequestPathInfo("/user");
		addRequestParameter("method", "updateOne");
		addRequestParameter("user.firstName", "Rod");
		addRequestParameter("user.lastName", "Johnson");
		addRequestParameter("user.birthday", DateFormatUtils.format(new Date(System.currentTimeMillis()),"yyyy-MM-dd"));
		actionPerform();
		verifyNoActionErrors();
		
	}

	
	public void testSaveOrUpdateOne() {
		//��������������,�����ݿ���ȥ�Ƚ�,�Զ��ж������ӻ��Ǹ�������.
		
		
		setRequestPathInfo("/user");
		addRequestParameter("method", "saveOrUpdateOne");
		addRequestParameter("user.user_id", "002");
		addRequestParameter("user.user_name", "chinayuan");
		addRequestParameter("user.firstName", "chinayuan");
		addRequestParameter("user.lastName", "hello");
		actionPerform();
		
		setRequestPathInfo("/user");
		addRequestParameter("method", "saveOrUpdateOne");
		addRequestParameter("user.firstName", "chinayuan");
		addRequestParameter("user.lastName", "Joson");
		
		GregorianCalendar calendar=new GregorianCalendar(1976,10,17);
		Date birthday=new Date(calendar.getTimeInMillis());
		addRequestParameter("user.birthday",DateFormatUtils.format(birthday,"yyyy-MM-dd"));
		actionPerform();
		
	}

	public void testRemoveOne() {
		//ɾ��id=1�ļ�¼.
		
//		���ò���,��Ϊû�еõ�idֵ.
		
//		setRequestPathInfo("/user");
//		addRequestParameter("method", "removeOne");
//		addRequestParameter("id", "1");
//		actionPerform();
//		verifyNoActionErrors();
		
	}

	// ����id���user����
	public void testDetailOne() {
		
//		���ò���,��Ϊû�еõ�idֵ.
//		setRequestPathInfo("/user");
//		addRequestParameter("id", "2");
//		addRequestParameter("method", "detailOne");
//		actionPerform();
//		verifyForward("detailOne");
//		verifyNoActionErrors();
	}
	
	
	public void testQueryStandard() {
		setRequestPathInfo("/user");		
		addRequestParameter("method", "queryStandard");
		getRequest().setAttribute("query_condition",
				"from User where firstName='first name'");
		actionPerform();
		verifyForward("success");
		verifyNoActionErrors();
	}

	// ������е�user����
	public void testQueryAll() {
	
		setRequestPathInfo("/user");
		addRequestParameter("method", "queryAll");
		actionPerform();
		verifyForward("listAll");
		verifyNoActionErrors();
	
		List users = (List) getRequest().getAttribute("data");
		assertNotNull(users);
	
	}

}
