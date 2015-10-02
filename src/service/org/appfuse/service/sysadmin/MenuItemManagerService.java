/*
 * project name : equinox-self
 * package name : org.appfuse.service
 * file    name : UserManagerService.java
 * class   name : UserManagerService
 * Created on 2005-7-22 16:41:43
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.service.sysadmin;

import java.util.List;

import org.appfuse.dao.sysadmin.MenuItemDAO;
import org.appfuse.model.sysadmin.MenuItem;
import org.appfuse.service.base.BaseService;

public interface MenuItemManagerService extends BaseService {
	void setMenuItemDAO(MenuItemDAO dao);

	MenuItem saveMenuItem(MenuItem menuItem);

	MenuItem saveOrUpdateMenuItem(MenuItem menuItem);

	MenuItem updateMenuItem(MenuItem menuItem);

	void removeMenuItem(String menuItem_id);

	void removeMenuItems(String[] menuItem_ids);

	void removeSomeMenuItems(String delete_condition);

	void removeAllMenuItems();

	List queryStandard(String queryCondition);

	MenuItem getMenuItem(String menuItem_id);

	List getMenuItems();
	
	

}
