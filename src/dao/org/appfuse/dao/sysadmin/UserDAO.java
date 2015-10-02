package org.appfuse.dao.sysadmin;

import java.util.List;

import org.appfuse.dao.base.BaseDAO;
import org.appfuse.model.sysadmin.User;

public interface UserDAO extends BaseDAO {

	void saveUser(User user);

	void updateUser(User user);

	/**
	 * @deprecated
	 */
	void updateSomeUsers(String updateCondtion);

	void saveOrUpdateUser(User user);

	void removeSomeUsers(String removeCondtion);

	void removeUser(String userid);

	void removeUsers(String[] userids);

	void removeAllUsers();

	List queryStandard(String queryCondition);

	User getUser(String userid);

	List getUsers();
}
