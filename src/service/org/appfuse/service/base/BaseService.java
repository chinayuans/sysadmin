/*
 * project name : equinox-self
 * package name : org.appfuse.service
 * file    name : BaseService.java
 * class   name : BaseService
 * Created on 2005-7-25 19:29:56
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.service.base;

import java.util.List;

import org.appfuse.dao.base.BaseDAO;
import org.appfuse.model.base.BaseObject;

public interface BaseService {
	void setBaseDAO(BaseDAO dao);

	void save(BaseObject object);

	void saveOrUpdate(BaseObject object);

	void update(BaseObject object);

	void remove(BaseObject object);
	
	void removeAll(BaseObject object);

	void removeSome(String remove_condition);
	
	void removeSome(BaseObject object,String[] ids);
	
	BaseObject getObject(BaseObject object);
	
	List getObjects(BaseObject object);

	List query(String query_condition);
	
	List query(BaseObject object);
	
}
