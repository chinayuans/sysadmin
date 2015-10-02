/*
 * project name : equinox-self
 * package name : org.appfuse.dao.audit
 * file    name : AuditLogInterceptor.java
 * class   name : AuditLogInterceptor
 * Created on 2005-8-3 9:55:13
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.dao.audit;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.hibernate.Interceptor;
import net.sf.hibernate.type.Type;

import org.apache.log4j.Logger;
import org.appfuse.model.audit.AuditLogRecord;
import org.appfuse.model.audit.Auditable;

public class AuditLogInterceptor implements Interceptor, Serializable {

	private static final long serialVersionUID = 4977578903591885587L;

	
	private static final Logger logger = Logger
			.getLogger(AuditLogInterceptor.class);

	private AuditLogDAO auditLogDAO;

	private Set inserts = new HashSet();

	private Set updates = new HashSet();

	public AuditLogInterceptor() {
		super();
	}

	public AuditLogDAO getAuditLogDAO() {
		return auditLogDAO;
	}

	public void setAuditLogDAO(AuditLogDAO auditLogDAO) {
		//DAO的插入表明准备进行更新擦作了
		this.auditLogDAO = auditLogDAO;
	}

	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		// do nothing
	}

	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		if (entity instanceof Auditable) {
			updates.add(entity);
		}
		return false;
	}

	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		return false;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {

		if (entity instanceof Auditable) {

			if (logger.isDebugEnabled()) {

				logger.debug("onSave() - before Save ; Saved Object="
						+ entity.getClass().getName());

				// (new AuditLogDAOImpl_Hibernate()).saveLogEvent("insert",
				// (Auditable)entity);
			}

			inserts.add(entity);
		}

		return false;
	}

	public void postFlush(Iterator entities) {
		try {
			Iterator it = updates.iterator();
			while (it.hasNext()) {
				Auditable entity = (Auditable) it.next();
				AuditLogRecord log = new AuditLogRecord();
				log.setEntityId(entity.getId());
				log.setMessage("update");
				auditLogDAO.saveAudit(log);
			}
			it = inserts.iterator();
			while (it.hasNext()) {
				Auditable entity = (Auditable) it.next();
				AuditLogRecord log = new AuditLogRecord();
				log.setEntityId(entity.getId());
				log.setMessage("insert");
				auditLogDAO.saveAudit(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inserts.clear();
			updates.clear();
		}
	}

	public void preFlush(Iterator entities) {
		// do nothing
	}

	public Object instantiate(Class clazz, Serializable id) {
		return null;
	}

	public int[] findDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		return null;
	}

	public Boolean isUnsaved(Object entity) {
		return null;
	}

	
}
