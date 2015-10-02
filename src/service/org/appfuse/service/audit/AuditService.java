/*
 * project name : equinox-self
 * package name : org.appfuse.service.audit
 * file    name : AuditService.java
 * class   name : AuditService
 * Created on 2005-8-3 12:32:56
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.service.audit;

import org.appfuse.model.audit.AuditLogRecord;
import org.appfuse.service.base.BaseService;

public interface AuditService extends BaseService {
	public void saveAudit(AuditLogRecord audit);
}
