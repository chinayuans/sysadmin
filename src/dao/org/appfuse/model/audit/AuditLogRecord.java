/*
 * project name : equinox-self
 * package name : org.appfuse.model.audit
 * file    name : AuditLogRecord.java
 * class   name : AuditLogRecord
 * Created on 2005-8-3 9:22:48
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.model.audit;



import java.sql.Date;

import org.appfuse.model.base.BaseObject;
/**
 * 这个类用来保存审计日志的数据
 * Created on 2005-8-3 9:23:40
 * @author ---Joson Yuan
 * author comments:
 * @hibernate.class 
 *   table="t_audit_logs"
 */
public class AuditLogRecord extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1360789732791306016L;
	private String id;
	private String message;
	private String entityId;
	private String entityClass;
	private Date   created_time;
	

	public AuditLogRecord() {
		super();
	}

	public AuditLogRecord(String message, String id, String class1) {
		this.created_time = new Date(System.currentTimeMillis());
		entityClass = class1;
		entityId = id;
		this.message = message;
	}

	/**
	 * @hibernate.id
	 *   generator-class="uuid.hex"	
	 *   not-null="true"
	 *   length="32"
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @hibernate.property 
	 *  not-null="true"
	 */
	public Date getCreated_time() {
		return created_time;
	}
	
	/**
	 *  @hibernate.property 
	 *  not-null="false"
	 *  length="30"
	 */
	public String getEntityClass() {
		return entityClass;
	}
	
	/**
	 *  @hibernate.property 
	 *  not-null="false"
	 *  length="32"
	 */
	public String getEntityId() {
		return entityId;
	}
	
	/**
	 *  @hibernate.property 
	 *  not-null="false"
	 */
	public String getMessage() {
		return message;
	}
	
	
	
	
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
