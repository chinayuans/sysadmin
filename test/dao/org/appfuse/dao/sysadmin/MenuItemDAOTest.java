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

import java.util.List;

import org.apache.log4j.Logger;
import org.appfuse.dao.base.BaseDAOTest;
import org.appfuse.dao.sysadmin.MenuItemDAO;
import org.appfuse.model.sysadmin.MenuItem;

public class MenuItemDAOTest extends BaseDAOTest {
	
	private static final Logger logger = Logger.getLogger(MenuItemDAOTest.class);

	private MenuItemDAO dao = null;

	private MenuItem menuItem = null;
	
	public MenuItemDAOTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {

		// =================================
		// ��������ÿһ��testXXX��ʼ�����⻷��
		// =================================
		super.setUp();
		dao = (MenuItemDAO) ctx.getBean("menuItemDAO");
	}

	protected void tearDown() throws Exception {
		// =================================
		// ��ÿһ��testXXX��ʼ�����⻷���������١�
		// =================================
		super.tearDown();
		dao = null;
	}

	public void testRemoveAllMenuItems() throws Exception {
		dao.removeAllMenuItems();

		List menuItems = dao.getMenuItems();
		assertEquals(0, menuItems.size());
	}

	// add
	public void testSaveMenuItem() throws Exception {
		menuItem = new MenuItem();
		menuItem.setName("�˵�1");
		menuItem.setTitle("�˵�1");

		dao.saveMenuItem(menuItem);
		assertNotNull(menuItem.getId());
		assertEquals("�˵�1",menuItem.getName());
		assertNotNull(dao.getMenuItem(menuItem.getId()));
	}

	// update
	public void testUpdateMenuItem() throws Exception {
		menuItem = new MenuItem();
		menuItem.setName("�˵�2");

		dao.saveMenuItem(menuItem);
		assertNotNull("primary key assigned", menuItem.getId());
		logger.info(menuItem);

		// ������仰������,��ΪId�����Ը���.
		// menuItem.setId(new Long(10000));
		menuItem.setTitle("�˵�2");
		dao.updateMenuItem(menuItem);

	}

	// add, then delete
	public void testSaveAndRemoveMenuItems() throws Exception {
		menuItem = new MenuItem();
		menuItem.setName("menu 3");

		dao.saveMenuItem(menuItem);
		assertNotNull(menuItem.getId());
		assertEquals(menuItem.getName(), "menu 3");

		if (logger.isDebugEnabled()) {
			logger.debug("Remove menuItem...");
		}

		dao.removeMenuItem(menuItem.getId());
		assertNull(dao.getMenuItem(menuItem.getId()));
	}

	public void testSaveOrUpdateMenuItems() throws Exception {
		menuItem = new MenuItem();
		menuItem.setName("chinayuan");
		menuItem.setTitle("hello");
		

		dao.saveOrUpdateMenuItem(menuItem);
		assertEquals("hello", menuItem.getTitle());
		assertNotNull(menuItem.getId());

		menuItem.setTitle("chinayuan");
		dao.saveOrUpdateMenuItem(menuItem);


	}
	
	public void testRemoveSomeMenuItems() throws Exception {
		//��Ҫid,�޷�����
//		String deleteCondition="from MenuItem a where a.";
//		dao.removeSomeMenuItems(deleteCondition);
//
//		MenuItem menuItem = dao.getMenuItem("1");
//		assertNull(menuItem);
	}

	public void testFindAllMenuItems() throws Exception {
		List menuItems = dao.getMenuItems();

		if (logger.isInfoEnabled()) {
			logger.info("===================");
			for (int i = 0; i < menuItems.size(); i++) {
				logger.info(menuItems.get(i));
			}
			logger.info("===================");
		}

	}
	
	public void testQueryCondition() throws Exception {
		String condition="from MenuItem a where a.id<>'1' ";
		List menuItems = dao.queryStandard(condition);

		if (logger.isInfoEnabled()) {
			logger.info("===================");
			for (int i = 0; i < menuItems.size(); i++) {
				logger.info(menuItems.get(i));
			}
			logger.info("===================");
		}

	}
}
