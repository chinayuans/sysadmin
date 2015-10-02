/*
 * project name : equinox-self
 * package name : org.appfuse.dao.impl
 * file    name : UserDAOImpl_Hibernate.java
 * class   name : UserDAOImpl_Hibernate
 * Created on 2005-7-22 11:44:15
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.dao.sysadmin.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.appfuse.dao.base.impl.BaseDAOImpl_Hibernate;
import org.appfuse.dao.sysadmin.MenuItemDAO;
import org.appfuse.model.sysadmin.MenuItem;

public class MenuItemDAOImpl_Hibernate extends BaseDAOImpl_Hibernate implements MenuItemDAO {
	
	private static final Logger logger = Logger
			.getLogger(MenuItemDAOImpl_Hibernate.class);

	public MenuItem getMenuItem(String menuItemid) {

		return (MenuItem) getHibernateTemplate().get(MenuItem.class, menuItemid);
	}

	public List getMenuItems() {

		return getHibernateTemplate().find("from MenuItem");
	}

	
	public List queryStandard(String queryCondition) {
		
		return getHibernateTemplate().find(queryCondition);
	}

	public void saveMenuItem(MenuItem menuItem) {
		getHibernateTemplate().save(menuItem);

		if (logger.isDebugEnabled()) {
			logger.debug("saveMenuItem() is a success.");
		}
	}

	public void saveOrUpdateMenuItem(MenuItem menuItem) {
		getHibernateTemplate().saveOrUpdate(menuItem);

		if (logger.isDebugEnabled()) {
			logger.debug("saveOrUpdateMenuItem() is a success.");
		}
	}

	public void updateMenuItem(MenuItem menuItem) {

		getHibernateTemplate().update(menuItem);

		if (logger.isDebugEnabled()) {
			logger.debug("updateMenuItem() is a success.");
		}
	}

	/**
	 * @deprecated
	 */
	public void updateSomeMenuItems(String updateCondtion) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateSomeMenuItems is a success.");
		}
		
	}

	public void removeMenuItem(String menuItemid) {
		MenuItem menuItem = (MenuItem) getHibernateTemplate().load(MenuItem.class, menuItemid);
		getHibernateTemplate().delete(menuItem);

		if (logger.isDebugEnabled()) {
			logger.debug("removeMenuItem() is a success.");
		}
	}

	public void removeSomeMenuItems(String deleteCondtion) {
		
		getHibernateTemplate().delete(deleteCondtion);
		
		if (logger.isDebugEnabled()) {
			logger.debug("removeSomeMenuItems() is a success.");
		}
	}

	public void removeMenuItems(String[] menuItemids) {

		for (int i = 0; i < menuItemids.length; i++) {
			MenuItem menuItem = (MenuItem) getHibernateTemplate().load(MenuItem.class,
					menuItemids[i]);
			getHibernateTemplate().delete(menuItem);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("removeMenuItems() is a success.");
		}
	}
	
	public void removeAllMenuItems(){
		getHibernateTemplate().delete("from MenuItem");
		if (logger.isDebugEnabled()) {
			logger.debug("removeMenuItems() is a success.");
		}
	}
}
