/*
 * project name : equinox-self
 * package name : org.appfuse.dao
 * file    name : UserDAOTest.java
 * class   name : UserDAOTest
 * Created on 2005-7-22 9:37:16
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.dao.sysadmin;

import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import org.appfuse.dao.base.BaseDAOTest;
import org.appfuse.dao.sysadmin.UserDAO;
import org.appfuse.model.sysadmin.Role;
import org.appfuse.model.sysadmin.User;
import org.appfuse.model.sysadmin.UserDetail;
import org.appfuse.model.sysadmin.UserNormalAddress;
import org.appfuse.model.sysadmin.UserStatus;

public class UserDAOTest extends BaseDAOTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserDAOTest.class);

	private UserDAO dao = null;

	private User user = null;

	public UserDAOTest(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {

		// =================================
		// 建立下面每一个testXXX开始的特殊环境
		// =================================
		super.setUp();
		dao = (UserDAO) ctx.getBean("userDAO");
	}

	protected void tearDown() throws Exception {
		// =================================
		// 将每一个testXXX开始的特殊环境进行销毁。
		// =================================
		super.tearDown();
		dao = null;
	}

	public void testRemoveAllUsers() throws Exception {
		dao.removeAllUsers();

		List users = dao.getUsers();
		assertEquals(0, users.size());
	}

	// add
	public void testSaveUser() throws Exception {
		user = new User();
		user.setUser_id("000");
		user.setUser_name("Joy Bill");
		user.setFirstName("Bill");
		user.setLastName("Joy");
		user.setBirthday(new Date(System.currentTimeMillis()));

		Role role1=new Role();
		role1.setDescription("role1");
		role1.setComments("role1");
		
		Role role2=new Role();
		role2.setDescription("role2");
		role2.setComments("role2");
		
//		user.getRoles().add(role1);
//		user.getRoles().add(role2);
		
		UserStatus status=new UserStatus();
		status.setDescription("status1");
		status.setComments("status1");
		
		user.setUserStatus(status);
		
		UserNormalAddress normalAddress=new UserNormalAddress();
		normalAddress.setCity("beijing");
		normalAddress.setCountry("china");
		normalAddress.setStreet("haidian");
		user.setNormalAddress(normalAddress);
		
		UserDetail detail=new UserDetail();
		detail.setDegree("graduate");
		detail.setGendar("male");
		detail.setId_card("4444");
		
		user.setDetail(detail);
		
		dao.saveUser(user);
		assertNotNull(user.getId());
		assertEquals(user.getFirstName(), "Bill");
		assertNotNull(dao.getUser(user.getId()));
	}

	// update
	public void testUpdateUser() throws Exception {
		user = new User();
		user.setUser_id("001");
		user.setUser_name("Johnson Rod");
		user.setFirstName("Rod");
		user.setLastName("Johnson");

		dao.saveUser(user);
		assertNotNull("primary key assigned", user.getId());
		logger.info(user);

		// 以下这句话不可以,因为Id不可以更新.
		// user.setId(new Long(10000));
		user.setBirthday(new Date(System.currentTimeMillis()));
		dao.updateUser(user);

	}

	// add, then delete
	public void testSaveAndRemoveUsers() throws Exception {
		user = new User();
		user.setUser_id("002");
		user.setUser_name("Joy Bill");
		user.setFirstName("Bill");
		user.setLastName("Joy");

		dao.saveUser(user);
		assertNotNull(user.getId());
		assertEquals(user.getFirstName(), "Bill");

		if (logger.isDebugEnabled()) {
			logger.debug("Remove user...");
		}

		dao.removeUser(user.getId());
		assertNull(dao.getUser(user.getId()));
	}

	public void testSaveOrUpdateUsers() throws Exception {
		user = new User();
		user.setUser_id("003");
		user.setUser_name("chinayuan");
		user.setFirstName("chinayuan");
		user.setLastName("hello");

		Calendar calendar = new GregorianCalendar();
		calendar.set(1976, 10, 17);
		user.setBirthday(new Date(calendar.getTimeInMillis()));

		dao.saveOrUpdateUser(user);
		assertEquals("hello", user.getLastName());
		assertNotNull(user.getId());

		user.setLastName("Joson");
		dao.saveOrUpdateUser(user);


	}
	
	public void testRemoveSomeUsers() throws Exception {
		//需要id，无法测试
//		String deleteCondition="from User a where a.id='1'";
//		dao.removeSomeUsers(deleteCondition);
//
//		User user = dao.getUser("1");
//		assertNull(user);
	}

	public void testFindAllUsers() throws Exception {
		List users = dao.getUsers();

		if (logger.isInfoEnabled()) {
			logger.info("===================");
			for (int i = 0; i < users.size(); i++) {
				logger.info(users.get(i));
			}
			logger.info("===================");
		}

	}
	
	public void testFindUser() throws Exception {
//		User user = dao.getUser("8a8a8df7098523f101098523fa4c0001");

		if (logger.isInfoEnabled()) {
			logger.info("===================");			
//				logger.info(user.getRoles());
			logger.info("===================");
		}

	}
	
	public void testQueryCondition() throws Exception {
		String condition="from User a where a.id<>'1'";
		List users = dao.queryStandard(condition);

		if (logger.isInfoEnabled()) {
			logger.info("===================");
			for (int i = 0; i < users.size(); i++) {
				logger.info(users.get(i));
			}
			logger.info("===================");
		}

	}
}
