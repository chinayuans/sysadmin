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

// 需要struts-config.xml文件，***Action文件，***Form文件。
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
		
		//先增加后更新.
		
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
		//根据所给的数据,到数据库中去比较,自动判断是增加还是更新数据.
		
		
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
		//删除id=1的记录.
		
//		不好测试,因为没有得到id值.
		
//		setRequestPathInfo("/user");
//		addRequestParameter("method", "removeOne");
//		addRequestParameter("id", "1");
//		actionPerform();
//		verifyNoActionErrors();
		
	}

	// 根据id获得user对象
	public void testDetailOne() {
		
//		不好测试,因为没有得到id值.
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

	// 获得所有的user对象
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
