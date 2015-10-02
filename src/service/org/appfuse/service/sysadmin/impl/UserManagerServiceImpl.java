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
import org.appfuse.dao.sysadmin.UserDAO;
import org.appfuse.model.sysadmin.User;
import org.appfuse.service.base.impl.BaseServiceImpl;
import org.appfuse.service.sysadmin.UserManagerService;

public class UserManagerServiceImpl extends BaseServiceImpl
									implements UserManagerService {
	
	private static final Logger logger = Logger
			.getLogger(UserManagerServiceImpl.class);

	private UserDAO dao;
	
	public void setUserDAO(UserDAO dao){
		this.dao=dao;
	}
	
	public User getUser(String user_id) {
		User user=dao.getUser(user_id);
		if(user==null){
			logger.warn("UserId '"+user_id+"' not found in database");
		}
		return user;
	}

	
	public List getUsers() {
		
		return dao.getUsers();
	}

	
	public List queryStandard(String queryCondition) {
		
		return dao.queryStandard(queryCondition);
	}

	public User saveUser(User user) {
		dao.saveUser(user);
		return user;
	}
	
	public User saveOrUpdateUser(User user) {
		dao.saveOrUpdateUser(user);
		return user;
	}
	
	public User updateUser(User user) {
		dao.updateUser(user);
		return user;
	}

	public void removeUser(String user_id) {
		dao.removeUser(user_id);		
	}
	
	
	
	public void removeUsers(String[] user_ids) {		
		dao.removeUsers(user_ids);		
	}

	
	
	public void removeSomeUsers(String delete_condition) {
		dao.removeSomeUsers(delete_condition);
		
	}

	public void removeAllUsers(){
		dao.removeAllUsers();
	}
}
