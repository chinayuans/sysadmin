package org.appfuse.service.base;


import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.appfuse.model.sysadmin.User;
import org.appfuse.service.base.BaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * project name : equinox-self
 * package name : 
 * file    name : BaseServiceTest.java
 * class   name : BaseServiceTest
 * Created on 2005-7-22 16:27:02
 * creator ---Joson Yuan
 * author comments:
 * 
 */

public class BaseServiceTest extends TestCase {
	
	private static final Logger logger = Logger
			.getLogger(BaseServiceTest.class);
	
	protected BaseService _service=null;
	protected ApplicationContext ctx=null;
	protected User user=null;
	
	public BaseServiceTest(String name) {
		super(name);
		
//		1.Must support fully qualified URLs, e.g. "file:C:/test.dat".
//		2.Must support classpath pseudo-URLs, e.g. "classpath:test.dat","classpath:**/test.dat".
//		3.Should support relative file paths, e.g. "WEB-INF/test.dat", "".
//		4.��㷺��֧�֣�"classpath*:META-INF/*-beans.xml"
//		 will find all *-beans.xml files in the class path, be it in "classes" directories
//		 or in JAR files.
		
//    	String[] paths = {"classpath*:**/bean*.xml" }; // ��jar�У�����classesĿ¼��ȥѰ����صĶ�����
//    	String[] paths = {"classpath:/com/sample/Ioc/bean.xml" }; // ֻ��classpath��ȥѰ����صĶ�����
//    	String[] paths = {"classpath:**/*bean.xml" }; // bad expression��
//    	String[] paths = {"file:D:/**/bean*.xml" };    // good expression
//    	String[] paths = {"/com/sample/Ioc/*bean*.xml" }; // good expression ����ʹ��ȷ�е�·��
//    	String[] paths = {"com/sample/Ioc/*bean*.xml" }; // ��������ͬ�ı�ʾʽ������ʹ��ȷ�е�·��
//    	String[] paths = {"/WEB-INF/*-context.xml"}; // ��������ͬ�ı�ʾʽ������ʹ��ȷ�е�·��
		
		//========================
    	//���������õĻ����������
    	//========================
		
		//ʵ���ϰ�����applicationContext-dao.xml
		//��applicationContext-service.xml�����ļ���
		//ͨ��spring���õĺϲ����ܵõ� һ��xml���ļ������ڴ��С�
    	String[] paths={"classpath*:**/test-applicationContext-dao.xml",
    			        "classpath*:**/test-applicationContext-service.xml"};
    	ctx=new ClassPathXmlApplicationContext(paths);
	}
	
	protected void setUp() throws Exception {
		logger.info("service��ƽ̨׼������");
		super.setUp();
		_service = (BaseService) ctx.getBean("baseService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		_service = null;
		logger.info("service��ƽ̨����");
	}

	public void testRemoveAll() {
		user=new User();
		_service.removeAll(user);
	}

	public void testSave() throws Exception {
		user = new User();
		user.setUser_id("000");
		user.setUser_name("Joy Bill");
		user.setFirstName("Bill");
		user.setLastName("Joy");
		//user.setBirthday(new Date(System.currentTimeMillis()));

		_service.save(user);
		assertNotNull(user.getId());
		assertEquals(user.getFirstName(), "Bill");
		assertNotNull(_service.getObject(user));
	}

	// update
	public void testUpdate() throws Exception {
		user = new User();
		user.setUser_id("001");
		user.setUser_name("Johnson Rod");
		user.setFirstName("Rod");
		user.setLastName("Johnson");

		_service.save(user);
		assertNotNull("primary key assigned", user.getId());
		logger.info(user);

		// ������仰������,��ΪId�����Ը���.
		// user.setId(new Long(10000));
		user.setBirthday(new Date(System.currentTimeMillis()));
		_service.update(user);

	}

	// add, then remove
	public void testRemove() throws Exception {
		user = new User();
		user.setUser_id("000");
		user.setUser_name("Joy Bill");
		user.setFirstName("Bill");
		user.setLastName("Joy");

		_service.save(user);
		assertNotNull(user.getId());
		assertEquals(user.getFirstName(), "Bill");

		if (logger.isDebugEnabled()) {
			logger.debug("Remove user...");
		}

		_service.remove(user);
		assertNull(_service.getObject(user));
	}

	//���ӻ��߱���.
	public void testSaveOrUpdate() throws Exception {
		user = new User();
		user.setUser_id("003");
		user.setUser_name("chinayuan");
		user.setFirstName("chinayuan");
		user.setLastName("hello");

		Calendar calendar = new GregorianCalendar();
		calendar.set(1976, 10, 17);
		user.setBirthday(new Date(calendar.getTimeInMillis()));

		_service.saveOrUpdate(user);
		assertEquals("hello", user.getLastName());
		assertNotNull(user.getId());

		user.setLastName("Joson");
		_service.saveOrUpdate(user);

	}

	public void testRemoveSome(){
		
		String deleteCondition="from User a where a.id ='1'";
		
		_service.removeSome(deleteCondition);

		user=new User();
		
		List list= _service.getObjects(user);
		assertEquals(3,list.size());
	}
	
	public void testFindAll() throws Exception {
		user=new User();
		List users = _service.getObjects(user);

		if (logger.isInfoEnabled()) {
			logger.info("===================");
			for (int i = 0; i < users.size(); i++) {
				logger.info(users.get(i));
			}
			logger.info("===================");
		}

	}
	
	public void testQueryByCondition() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("testQueryByCondition() - start");
		}
		
//		String condition="from User where birthday='2005-7-30' ";
		String condition="from User where id <> '1' ";
		List users = _service.query(condition);

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
		User user=new User();
		user.setFirstName("Bill");		
		List users = _service.query(user);

		if (logger.isInfoEnabled()) {
			logger.info("===================");
			for (int i = 0; i < users.size(); i++) {
				logger.info(users.get(i));
			}
			logger.info("===================");
		}

	}
}
