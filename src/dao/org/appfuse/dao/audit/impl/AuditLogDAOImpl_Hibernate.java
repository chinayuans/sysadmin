/*
 * project name : equinox-self
 * package name : org.appfuse.dao.audit
 * file    name : AuditLog.java
 * class   name : AuditLog
 * Created on 2005-8-3 9:44:29
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.dao.audit.impl;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.appfuse.dao.audit.AuditLogDAO;
import org.appfuse.dao.base.impl.BaseDAOImpl_Hibernate;
import org.appfuse.model.audit.AuditLogRecord;
import org.springframework.orm.hibernate.SessionFactoryUtils;

public class AuditLogDAOImpl_Hibernate extends BaseDAOImpl_Hibernate implements
		AuditLogDAO {
	public void saveAudit(AuditLogRecord log) {
		//getHibernateTemplate().save(log);
		Session session=SessionFactoryUtils.getNewSession(getSessionFactory());
		System.out.println("Session======:"+session);
		try {
			session.save(log);
		} catch (HibernateException e) {
			convertHibernateAccessException(e);
		}
	}
}
