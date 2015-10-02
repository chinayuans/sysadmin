/*
 * project name : equinox-self
 * package name : org.appfuse.service.impl
 * file    name : UserManagerServiceImpl.java
 * class   name : UserManagerServiceImpl
 * Created on 2005-7-22 16:44:56
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.service.sysadmin.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.appfuse.dao.sysadmin.MenuItemDAO;
import org.appfuse.model.sysadmin.MenuItem;
import org.appfuse.service.base.impl.BaseServiceImpl;
import org.appfuse.service.sysadmin.MenuItemManagerService;

public class MenuItemManagerServiceImpl extends BaseServiceImpl
									implements MenuItemManagerService {
	
	private static final Logger logger = Logger
			.getLogger(MenuItemManagerServiceImpl.class);

	private MenuItemDAO dao;
	
	public void setMenuItemDAO(MenuItemDAO dao){
		this.dao=dao;
	}
	
	public MenuItem getMenuItem(String menuItem_id) {
		MenuItem menuItem=dao.getMenuItem(menuItem_id);
		if(menuItem==null){
			logger.warn("MenuItemId '"+menuItem_id+"' not found in database");
		}
		return menuItem;
	}

	
	public List getMenuItems() {
		
		return dao.getMenuItems();
	}

	
	public List queryStandard(String queryCondition) {
		
		return dao.queryStandard(queryCondition);
	}

	public MenuItem saveMenuItem(MenuItem menuItem) {
		dao.saveMenuItem(menuItem);
		return menuItem;
	}
	
	public MenuItem saveOrUpdateMenuItem(MenuItem menuItem) {
		dao.saveOrUpdateMenuItem(menuItem);
		return menuItem;
	}
	
	public MenuItem updateMenuItem(MenuItem menuItem) {
		dao.updateMenuItem(menuItem);
		return menuItem;
	}

	public void removeMenuItem(String menuItem_id) {
		dao.removeMenuItem(menuItem_id);		
	}
	
	
	
	public void removeMenuItems(String[] menuItem_ids) {		
		dao.removeMenuItems(menuItem_ids);		
	}

	
	
	public void removeSomeMenuItems(String delete_condition) {
		dao.removeSomeMenuItems(delete_condition);
		
	}

	public void removeAllMenuItems(){
		dao.removeAllMenuItems();
	}
}
