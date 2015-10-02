package org.appfuse.dao.sysadmin;

import java.util.List;

import org.appfuse.dao.base.BaseDAO;
import org.appfuse.model.sysadmin.MenuItem;

public interface MenuItemDAO extends BaseDAO {

	void saveMenuItem(MenuItem menuItem);

	void updateMenuItem(MenuItem menuItem);

	/**
	 * @deprecated
	 */
	void updateSomeMenuItems(String updateCondtion);

	void saveOrUpdateMenuItem(MenuItem menuItem);

	void removeSomeMenuItems(String removeCondtion);

	void removeMenuItem(String menuItemid);

	void removeMenuItems(String[] menuItemids);

	void removeAllMenuItems();

	List queryStandard(String queryCondition);

	MenuItem getMenuItem(String menuItemid);

	List getMenuItems();
}
