/*
 * project name : equinox-self
 * package name : org.appfuse.dao
 * file    name : BaseDAOTestCase.java
 * class   name : BaseDAOTestCase
 * Created on 2005-7-22 9:28:34
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.dao.base;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.appfuse.model.sysadmin.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * base class for BaseDAO TestCase Created on 2005-7-22 9:30:49
 * 
 * @author ---Joson Yuan author comments:
 * 
 */
public class BaseDAOTest extends TestCase {

	// protected的意思是子类也要log。
	protected static final Logger logger = Logger
			.getLogger(BaseDAOTest.class);

	protected ApplicationContext ctx = null;

	private BaseDAO dao = null;

	private User object = null;

	public BaseDAOTest(String name) {
		super(name);
//		1.Must support fully qualified URLs, e.g. "file:C:/test.dat".
//		2.Must support classpath pseudo-URLs, e.g. "classpath:test.dat","classpath:**/test.dat".
//		3.Should support relative file paths, e.g. "WEB-INF/test.dat", "".
//		4.最广泛的支持："classpath*:META-INF/*-beans.xml"
//		 will find all *-beans.xml files in the class path, be it in "classes" directories
//		 or in JAR files.
		
//    	String[] paths = {"classpath*:**/bean*.xml" }; // 在jar中，还有classes目录下去寻找相关的东西。
//    	String[] paths = {"classpath:/com/sample/Ioc/bean.xml" }; // 只在classpath中去寻找相关的东西。
//    	String[] paths = {"classpath:**/*bean.xml" }; // bad expression。
//    	String[] paths = {"file:D:/**/bean*.xml" };    // good expression
//    	String[] paths = {"/com/sample/Ioc/*bean*.xml" }; // good expression 必须使用确切的路径
//    	String[] paths = {"com/sample/Ioc/*bean*.xml" }; // 与上面相同的表示式，必须使用确切的路径
//    	String[] paths = {"/WEB-INF/*-context.xml"}; // 与上面相同的表示式，必须使用确切的路径
		
		
		// ========================
		// 将测试所用的环境搭建起来。
		// ========================
		String[] paths = {"classpath*:**/test-applicationContext-dao.xml"};
		ctx = new ClassPathXmlApplicationContext(paths);
	}

	protected void setUp() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("setUp() - start");
		}

		// =================================
		// 建立下面每一个testXXX开始的特殊环境
		// =================================
		super.setUp();
		
		//这个地方容易出错，应该把spring相依赖的其他jar包也放入到eclipse的classpath中去，否则会出错
		dao = (BaseDAO) ctx.getBean("baseDAO");

		if (logger.isDebugEnabled()) {
			logger.debug("setUp() - end");
		}
	}

	protected void tearDown() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("tearDown() - start");
		}

		// =================================
		// 将每一个testXXX开始的特殊环境进行销毁。
		// =================================
		super.tearDown();
		dao = null;

		if (logger.isDebugEnabled()) {
			logger.debug("tearDown() - end");
		}
	}

	public void testRemoveAll() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("testRemoveAll() - start");
		}

		object = new User();

		dao.removeAll(object);
		List users = dao.getObjects(object);
		assertEquals(0, users.size());

		if (logger.isDebugEnabled()) {
			logger.debug("testRemoveAll() - end");
		}
	}

	// add
	public void testSave() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("testSave() - start");
		}

		object = new User();
		object.setUser_id("000");
		object.setUser_name("bill joy");
		object.setFirstName("Bill");
		object.setLastName("Joy");
		object.setBirthday(new Date(System.currentTimeMillis()));

		dao.save(object);
		assertNotNull(object.getId());
		assertEquals(object.getFirstName(), "Bill");
		assertNotNull(dao.getObject(object));

		if (logger.isDebugEnabled()) {
			logger.debug("testSave() - end");
		}
	}

	// update
	public void testUpdate() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("testUpdate() - start");
		}

		object = new User();
		object.setUser_id("001");
		object.setUser_name("bill joy");
		object.setFirstName("Rod");
		object.setLastName("Johnson");

		dao.save(object);
		assertNotNull("primary key assigned", object.getId());
		logger.info(object);

		// 以下这句话不可以,因为Id不可以更新.
		// user.setId(new Long(10000));
		object.setBirthday(new Date(System.currentTimeMillis()));
		dao.update(object);

		if (logger.isDebugEnabled()) {
			logger.debug("testUpdate() - end");
		}
	}

	// add, then delete
	public void testSaveAndRemove() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("testSaveAndRemove() - start");
		}

		object = new User();
		object.setUser_id("000");
		object.setUser_name("bill joy");
		object.setFirstName("Bill");
		object.setLastName("Joy");

		dao.save(object);
		assertNotNull(object.getId());
		assertEquals(object.getFirstName(), "Bill");

		if (logger.isDebugEnabled()) {
			logger.debug("Remove user...");
		}

		dao.remove(object);
		assertNull(dao.getObject(object));

		if (logger.isDebugEnabled()) {
			logger.debug("testSaveAndRemove() - end");
		}
	}

	public void testSaveOrUpdate() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("testSaveOrUpdate() - start");
		}

		object = new User();
		object.setUser_id("003");
		object.setUser_name("chinayuan");
		object.setFirstName("chinayuan");
		object.setLastName("hello");

		Calendar calendar = new GregorianCalendar();
		calendar.set(1976, 10, 17);
		object.setBirthday(new Date(calendar.getTimeInMillis()));

		dao.saveOrUpdate(object);
		assertEquals("hello", object.getLastName());
		assertNotNull(object.getId());

		object.setLastName("Joson");
		dao.saveOrUpdate(object);

		if (logger.isDebugEnabled()) {
			logger.debug("testSaveOrUpdate() - end");
		}
	}

	public void testRemoveSome() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("testRemoveSome() - start");
		}

		// 需要id，无法测试
		// String deleteCondition="from User a where a.id='1'";
		// dao.removeSomeUsers(deleteCondition);
		//
		// User user = dao.getUser("1");
		// assertNull(user);

		if (logger.isDebugEnabled()) {
			logger.debug("testRemoveSome() - end");
		}
	}

	public void testFindAll() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("testFindAll() - start");
		}
		
		
		object = new User();
		List users = dao.getObjects(object);

		if (logger.isInfoEnabled()) {
			logger.info("===================");
			for (int i = 0; i < users.size(); i++) {
				logger.info(users.get(i));
			}
			logger.info("===================");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("testFindAll() - end");
		}
	}

	public void testQueryByCondition() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("testQueryByCondition() - start");
		}

		String condition = "from User a where a.id<>'1'";
		List users = dao.query(condition);

		if (logger.isInfoEnabled()) {
			logger.info("===================");
			for (int i = 0; i < users.size(); i++) {
				logger.info(users.get(i));
			}
			logger.info("===================");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("testQueryByCondition() - end");
		}
	}

	public void testQueryByObject() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("testQueryByObject() - start");
		}

		User user = new User();
		user.setFirstName("Bill");
		user.setLastName("Joy");
		List users = dao.query(user);

		if (logger.isInfoEnabled()) {
			logger.info("===================");
			for (int i = 0; i < users.size(); i++) {
				logger.info(users.get(i));
			}
			logger.info("===================");
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("testQueryByObject() - end");
		}
	}

}
