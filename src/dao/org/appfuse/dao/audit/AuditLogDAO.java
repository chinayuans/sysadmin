/*
 * project name : equinox-self
 * package name : org.appfuse.dao.audit
 * file    name : AuditLogDAO.java
 * class   name : AuditLogDAO
 * Created on 2005-8-3 11:55:56
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.dao.audit;

import org.appfuse.model.audit.AuditLogRecord;

public interface AuditLogDAO {
	public void saveAudit(AuditLogRecord log);
}
