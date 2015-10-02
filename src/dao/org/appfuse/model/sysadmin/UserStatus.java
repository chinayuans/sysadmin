/*
 * project name : equinox-self
 * package name : org.appfuse.model.sysadmin
 * file    name : UserStatus.java
 * class   name : UserStatus
 * Created on 2005-8-10 11:24:34
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.model.sysadmin;

import java.util.HashSet;
import java.util.Set;

import org.appfuse.model.base.BaseObject;

/**
 * @hibernate.class 
 *   table="t_user_status"
 *
 */
public class UserStatus extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3716306741353267881L;
	private String id;
	private String description;
	private String comments;
	
//	以下字段使用hibernate的"一对多"映射. one-many
	private Set users=new HashSet();
	
	public UserStatus() {
	
	}
	
	/**
	 * 
	 * @hibernate.id 
	 *    generator-class="uuid.hex"
	 *    not-null="true"
	 *    length="32"
	 *     代理主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * @hibernate.property 
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @hibernate.property 
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * @hibernate.set 
	 * inverse = "true"
	 * lazy="true"
	 * cascade="save-update"
	 * @hibernate.collection-key 
	 * column = "userstatus_id"
	 * @hibernate.collection-one-to-many 
	 * class="org.appfuse.model.sysadmin.User"
	 */
	public Set getUsers() {
		return users;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public void setUsers(Set users) {
		this.users = users;
	}
	
	
}
