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
import org.appfuse.dao.sysadmin.UserDAO;
import org.appfuse.model.sysadmin.User;

public class UserDAOImpl_Hibernate extends BaseDAOImpl_Hibernate implements UserDAO {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UserDAOImpl_Hibernate.class);

	public User getUser(String userid) {
		User user=(User) getHibernateTemplate().get(User.class, userid);
//		for (Iterator iter = user.getRoles().iterator(); iter.hasNext();) {
//			Role element = (Role) iter.next();
//			System.out.println("special:"+element.getDescription());
//		}
//		
//		try {
//			Hibernate.initialize(user.getRoles());
//		} catch (HibernateException e) {
//			
//			e.printStackTrace();
//		}
//		user.getRoles().size();
		return user;
	}

	public List getUsers() {
		return getHibernateTemplate().find("from User");
	}

	
	public List queryStandard(String queryCondition) {
		
		return getHibernateTemplate().find(queryCondition);
	}

	public void saveUser(User user) {
		getHibernateTemplate().save(user);

		if (logger.isDebugEnabled()) {
			logger.debug("saveUser() is a success.");
		}
	}

	public void saveOrUpdateUser(User user) {
		getHibernateTemplate().saveOrUpdate(user);

		if (logger.isDebugEnabled()) {
			logger.debug("saveOrUpdateUser() is a success.");
		}
	}

	public void updateUser(User user) {

		getHibernateTemplate().update(user);

		if (logger.isDebugEnabled()) {
			logger.debug("updateUser() is a success.");
		}
	}

	/**
	 * @deprecated
	 */
	public void updateSomeUsers(String updateCondtion) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateSomeUsers is a success.");
		}
		
	}

	public void removeUser(String userid) {
		User user = (User) getHibernateTemplate().load(User.class, userid);
		getHibernateTemplate().delete(user);

		if (logger.isDebugEnabled()) {
			logger.debug("removeUser() is a success.");
		}
	}

	public void removeSomeUsers(String deleteCondtion) {
		
		getHibernateTemplate().delete(deleteCondtion);
		
		if (logger.isDebugEnabled()) {
			logger.debug("removeSomeUsers() is a success.");
		}
	}

	public void removeUsers(String[] userids) {

		for (int i = 0; i < userids.length; i++) {
			User user = (User) getHibernateTemplate().load(User.class,
					userids[i]);
			getHibernateTemplate().delete(user);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("removeUsers() is a success.");
		}
	}
	
	public void removeAllUsers(){
		getHibernateTemplate().delete("from User");
		if (logger.isDebugEnabled()) {
			logger.debug("removeUsers() is a success.");
		}
	}
}
