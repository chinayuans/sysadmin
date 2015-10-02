/*
 * project name : equinox-self
 * package name : org.appfuse.service.impl
 * file    name : BaseServiceImpl.java
 * class   name : BaseServiceImpl
 * Created on 2005-7-25 19:30:17
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.service.base.impl;

import java.util.List;

import org.appfuse.dao.base.BaseDAO;
import org.appfuse.model.base.BaseObject;
import org.appfuse.service.base.BaseService;

public class BaseServiceImpl implements BaseService {
    private BaseDAO BaseDAO=null;
	public void setBaseDAO(BaseDAO dao) {
		BaseDAO=dao;	
	}
	public List query(String query_condition) {
		return BaseDAO.query(query_condition);
	}

	public List query(BaseObject object) {
		return BaseDAO.query(object);
	}
	
	public void remove(BaseObject object) {
		try {
			BaseDAO.remove(object);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
	}

	
	
	public void removeSome(BaseObject object,String[] ids) {
		BaseDAO.removeSome(object,ids);
		
	}
	public void removeSome(String remove_condition) {
		BaseDAO.removeSome(remove_condition);		
	}


	public void save(BaseObject object) {
		BaseDAO.save(object);		
	}

	
	public void saveOrUpdate(BaseObject object) {
		BaseDAO.saveOrUpdate(object);				
	}

	
	public void update(BaseObject object) {
		BaseDAO.update(object);
	}
	
	public BaseObject getObject(BaseObject baseObject) {
		return BaseDAO.getObject(baseObject);		
	}
	
	public List getObjects(BaseObject object) {
		
		return BaseDAO.getObjects(object);
	}
	
	public void removeAll(BaseObject object) {
		BaseDAO.removeAll(object);
		
	}


	

}
