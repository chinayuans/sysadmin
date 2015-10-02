package org.appfuse.service.sysadmin;

import java.util.List;

import org.apache.log4j.Logger;
import org.appfuse.model.sysadmin.MenuItem;
import org.appfuse.service.base.BaseServiceTest;
import org.appfuse.service.sysadmin.MenuItemManagerService;

/*
 * project name : equinox-self
 * package name : 
 * file    name : MenuItemManagerServiceTest.java
 * class   name : MenuItemManagerServiceTest
 * Created on 2005-7-22 16:25:24
 * creator ---Joson Yuan
 * author comments:
 * 
 */

public class MenuItemManagerServiceTest extends BaseServiceTest {

	private static final Logger logger = Logger
			.getLogger(MenuItemManagerServiceTest.class);

	private MenuItem menuItem = null;

	private MenuItemManagerService _service = null;
	
	public MenuItemManagerServiceTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		_service = (MenuItemManagerService) ctx.getBean("menuItemManagerService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		_service = null;
	}

	public void testRemoveAllUses() {
		_service.removeAllMenuItems();
	}

	public void testSaveMenuItem() throws Exception {
		menuItem = new MenuItem();
		menuItem.setName("menu 1");
		menuItem.setTitle("menu1");
		_service.saveMenuItem(menuItem);
		
		assertNotNull(menuItem.getId());
		assertEquals(menuItem.getName(), "menu 1");
		assertNotNull(_service.getMenuItem(menuItem.getId().toString()));
	}

	// update
	public void testUpdateMenuItem() throws Exception {
		menuItem = new MenuItem();
		menuItem.setName("Rod");
		menuItem.setTitle("Johnson");

		_service.saveMenuItem(menuItem);
		assertNotNull("primary key assigned", menuItem.getId());
		logger.info(menuItem);

		menuItem.setTitle("Rod");
		_service.updateMenuItem(menuItem);

	}

	// add, then remove
	public void testRemoveMenuItem() throws Exception {
		menuItem = new MenuItem();
		menuItem.setName("joson");
		
		_service.saveMenuItem(menuItem);
		assertNotNull(menuItem.getId());
		assertEquals(menuItem.getName(), "joson");

		if (logger.isDebugEnabled()) {
			logger.debug("Remove menuItem...");
		}

		_service.removeMenuItem(menuItem.getId().toString());
		assertNull(_service.getMenuItem(menuItem.getId().toString()));
	}

	//增加或者保存.
	public void testSaveOrUpdateMenuItems() throws Exception {
		menuItem = new MenuItem();
		menuItem.setName("chinayuan");
		menuItem.setTitle("hello");


		_service.saveOrUpdateMenuItem(menuItem);
		assertEquals("chinayuan", menuItem.getName());
		assertNotNull(menuItem.getId());

		menuItem.setTitle("chinayuan");
		_service.saveOrUpdateMenuItem(menuItem);

	}

	public void testRemoveSomeMenuItems(){
		String deleteCondition="from MenuItem a where a.id=1";
		_service.removeSomeMenuItems(deleteCondition);

		MenuItem menuItem = _service.getMenuItem("1");
		assertNull(menuItem);
	}
	
	public void testFindAllMenuItems() throws Exception {
		List menuItems = _service.getMenuItems();

		if (logger.isInfoEnabled()) {
			logger.info("===================");
			for (int i = 0; i < menuItems.size(); i++) {
				logger.info(menuItems.get(i));
			}
			logger.info("===================");
		}

	}
	
	public void testQueryCondition() throws Exception {
		String condition="from MenuItem where id <> '1' ";
		List menuItems = _service.queryStandard(condition);

		if (logger.isInfoEnabled()) {
			logger.info("===================");
			for (int i = 0; i < menuItems.size(); i++) {
				logger.info(menuItems.get(i));
			}
			logger.info("===================");
		}

	}
}
