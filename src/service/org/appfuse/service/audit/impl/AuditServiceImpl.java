/*
 * project name : equinox-self
 * package name : org.appfuse.service.audit.impl
 * file    name : AuditServiceImpl.java
 * class   name : AuditServiceImpl
 * Created on 2005-8-3 12:34:53
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.service.audit.impl;

import org.appfuse.dao.audit.AuditLogDAO;
import org.appfuse.model.audit.AuditLogRecord;
import org.appfuse.service.audit.AuditService;
import org.appfuse.service.base.impl.BaseServiceImpl;

public class AuditServiceImpl extends BaseServiceImpl implements AuditService {
	private AuditLogDAO auditDAO=null;
	
	public AuditLogDAO getAuditDAO() {
		return auditDAO;
	}

	public void setAuditDAO(AuditLogDAO auditDAO) {
		this.auditDAO = auditDAO;
	}

	public void saveAudit(AuditLogRecord audit) {
		auditDAO.saveAudit(audit);
	}

}
