/*
 * project name : sysadmin
 * package name : org.appfuse.service.handleServiceExceptionSample.impl
 * file    name : HandleServiceExceptionImpl.java
 * class   name : HandleServiceExceptionImpl
 * Created on 2005-10-19 9:25:45
 * creator ---Joson Yuan
 * author comments:
 * 
 * ��Ҫ������ʾ��δ��������Ӧ���쳣��������Ϊһ��ʾ����
 */
package org.appfuse.service.handleServiceExceptionSample.impl;

import org.apache.log4j.Logger;

import java.util.List;

import org.appfuse.dao.base.BaseDAO;
import org.appfuse.model.base.BaseObject;
import org.appfuse.service.exception.BaseServiceException;
import org.appfuse.service.exception.DisobeyRuleException;
import org.appfuse.service.handleServiceExceptionSample.HandleServiceException;

// error.rule.disobey= <li>{0} Business Rule {1} is not obeyed.</li>
// error.business.error =<li>Some error occurs when performing business
// logic.</li>
// error.system.error =<li>Some system error occurs.</li>

public class HandleServiceExceptionImpl implements HandleServiceException {
	/**
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(HandleServiceExceptionImpl.class);

	public void businessMethod() throws BaseServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("businessMethod() - start");
		}

		BaseServiceException baseException = new BaseServiceException();
		baseException.setMessageKey("error.business.error");

		// ҵ���߼��쳣 1
		DisobeyRuleException ex1 = new DisobeyRuleException();
		ex1.setMessageKey("error.rule.disobey");
		ex1.setMessageArgs(new String[] { "1.", "One" });
		baseException.addException(ex1);
		// ���ֽ���ǣ�1. Business Rule One is not obeyed.

		// ҵ���߼��쳣 2
		DisobeyRuleException ex2 = new DisobeyRuleException();
		ex2.setMessageKey("error.rule.disobey");
		ex2.setMessageArgs(new String[] { "2.", "Two" });
		baseException.addException(ex2);
		// ���ֽ���ǣ�2. Business Rule Two is not obeyed.
		
		

		if (logger.isDebugEnabled()) {
			logger.debug("businessMethod() - end");
		}
		
		throw baseException;
	}

	public void setBaseDAO(BaseDAO dao) {
		// TODO Auto-generated method stub
		
	}

	public void save(BaseObject object) {
		// TODO Auto-generated method stub
		
	}

	public void saveOrUpdate(BaseObject object) {
		// TODO Auto-generated method stub
		
	}

	public void update(BaseObject object) {
		// TODO Auto-generated method stub
		
	}

	public void remove(BaseObject object) {
		// TODO Auto-generated method stub
		
	}

	public void removeAll(BaseObject object) {
		// TODO Auto-generated method stub
		
	}

	public void removeSome(String remove_condition) {
		// TODO Auto-generated method stub
		
	}

	public void removeSome(BaseObject object, String[] ids) {
		// TODO Auto-generated method stub
		
	}

	public BaseObject getObject(BaseObject object) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getObjects(BaseObject object) {
		// TODO Auto-generated method stub
		return null;
	}

	public List query(String query_condition) {
		// TODO Auto-generated method stub
		return null;
	}

	public List query(BaseObject object) {
		// TODO Auto-generated method stub
		return null;
	}

}
