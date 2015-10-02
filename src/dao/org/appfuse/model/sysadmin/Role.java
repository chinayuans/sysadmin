/*
 * project name : equinox-self
 * package name : org.appfuse.model.sysadmin
 * file    name : Role.java
 * class   name : Role
 * Created on 2005-8-10 11:30:39
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.model.sysadmin;

import org.appfuse.model.base.BaseObject;

/**
 * @hibernate.class 
 *   table="t_role"
 *
 */

public class Role extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4730872504355575600L;
	private String id;
	private String description;
	private String comments;
	
//	以下字段使用hibernate的"多对多"映射.
//	private Set users=new HashSet();
	
	
	public Role() {
	
	}
	
	/**
	 * 
	 * @hibernate.id  
	 *    generator-class="uuid.hex"
	 *    not-null="true"
	 *    length="32"
	 */
	public String getId() {
		return id;
	}
	/**
	 * @hibernate.property 
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @hibernate.property 
	 */
	public String getDescription() {
		return description;
	}
	
//	/**
//	 * @hibernate.set
//	 *  table="t_user_role"
//	 *  inverse="false"
//	 *  lazy="false"
//	 *  cascade="save-update"
//	 * @hibernate.collection-key 
//	 *  column="role_id"
//	 * @hibernate.collection-many-to-many
//	 *  column="user_id" 
//	 *  class="org.appfuse.model.sysadmin.User"
//	 */
//	public Set getUsers() {
//		return users;
//	}
//	
//	public void setUsers(Set users) {
//		this.users = users;
//	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
