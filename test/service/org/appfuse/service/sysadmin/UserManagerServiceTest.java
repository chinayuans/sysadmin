package org.appfuse.service.sysadmin;

import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import org.appfuse.model.sysadmin.User;
import org.appfuse.service.base.BaseServiceTest;
import org.appfuse.service.sysadmin.UserManagerService;

/*
 * project name : equinox-self
 * package name : 
 * file    name : UserManagerServiceTest.java
 * class   name : UserManagerServiceTest
 * Created on 2005-7-22 16:25:24
 * creator ---Joson Yuan
 * author comments:
 * 
 */

public class UserManagerServiceTest extends BaseServiceTest {

	private static final Logger logger = Logger
			.getLogger(UserManagerServiceTest.class);

	private User user = null;

	private UserManagerService _service = null;

	public UserManagerServiceTest(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		_service = (UserManagerService) ctx.getBean("userManagerService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		_service = null;
	}

	public void testRemoveAllUses() {
		_service.removeAllUsers();
	}

	public void testSaveUser() throws Exception {
		user = new User();
		user.setUser_id("000");
		user.setUser_name("Joy Bill");
		user.setFirstName("Bill");
		user.setLastName("Joy");
		//user.setBirthday(new Date(System.currentTimeMillis()));

		_service.saveUser(user);
		assertNotNull(user.getId());
		assertEquals(user.getFirstName(), "Bill");
		assertNotNull(_service.getUser(user.getId().toString()));
	}

	// update
	public void testUpdateUser() throws Exception {
		user = new User();
		user.setUser_id("001");
		user.setUser_name("Johnson Rod");
		user.setFirstName("Rod");
		user.setLastName("Johnson");

		_service.saveUser(user);
		assertNotNull("primary key assigned", user.getId());
		logger.info(user);

		// 以下这句话不可以,因为Id不可以更新.
		// user.setId(new Long(10000));
		user.setBirthday(new Date(System.currentTimeMillis()));
		_service.updateUser(user);

	}

	// add, then remove
	public void testRemoveUser() throws Exception {
		user = new User();
		user.setUser_id("002");
		user.setUser_name("Joy Bill");
		user.setFirstName("Bill");
		user.setLastName("Joy");

		_service.saveUser(user);
		assertNotNull(user.getId());
		assertEquals(user.getFirstName(), "Bill");

		if (logger.isDebugEnabled()) {
			logger.debug("Remove user...");
		}

		_service.removeUser(user.getId().toString());
		assertNull(_service.getUser(user.getId().toString()));
	}

	//增加或者保存.
	public void testSaveOrUpdateUsers() throws Exception {
		user = new User();
		user.setUser_id("003");
		user.setUser_name("chinayuan");
		user.setFirstName("chinayuan");
		user.setLastName("hello");

		Calendar calendar = new GregorianCalendar();
		calendar.set(1976, 10, 17);
		user.setBirthday(new Date(calendar.getTimeInMillis()));

		_service.saveOrUpdateUser(user);
		assertEquals("hello", user.getLastName());
		assertNotNull(user.getId());

		user.setLastName("Joson");
		_service.saveOrUpdateUser(user);

	}

	public void testRemoveSomeUsers(){
		String deleteCondition="from User a where a.id=1";
		_service.removeSomeUsers(deleteCondition);

		User user = _service.getUser("1");
		assertNull(user);
	}
	
	public void testFindAllUsers() throws Exception {
		List users = _service.getUsers();

		if (logger.isInfoEnabled()) {
			logger.info("===================");
			for (int i = 0; i < users.size(); i++) {
				logger.info(users.get(i));
			}
			logger.info("===================");
		}

	}
	
	public void testQueryCondition() throws Exception {
		String condition="from User where birthday='2005-7-30' ";
		List users = _service.queryStandard(condition);

		if (logger.isInfoEnabled()) {
			logger.info("===================");
			for (int i = 0; i < users.size(); i++) {
				logger.info(users.get(i));
			}
			logger.info("===================");
		}

	}
}
