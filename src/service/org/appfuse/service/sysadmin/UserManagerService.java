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

import org.appfuse.dao.sysadmin.UserDAO;
import org.appfuse.model.sysadmin.User;
import org.appfuse.service.base.BaseService;

public interface UserManagerService extends BaseService {
	void setUserDAO(UserDAO dao);

	User saveUser(User user);

	User saveOrUpdateUser(User user);

	User updateUser(User user);

	void removeUser(String user_id);

	void removeUsers(String[] user_ids);

	void removeSomeUsers(String delete_condition);

	void removeAllUsers();

	List queryStandard(String queryCondition);

	User getUser(String user_id);

	List getUsers();

}
