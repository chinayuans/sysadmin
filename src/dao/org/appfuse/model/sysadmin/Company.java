/*
 * project name : equinox-self
 * package name : org.appfuse.model.sysadmin
 * file    name : Company.java
 * class   name : Company
 * Created on 2005-8-10 11:34:15
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
 *   table="t_company"
 *
 */
public class Company extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7097735187805590243L;
	private String id;
	private String name;
	private String comments;
	private Set departments=new HashSet();
	
	public Company() {
	}
	/**
	 * 
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
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @hibernate.property 
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 *  @hibernate.set 
	 *  inverse="true"
	 *  lazy="true"
	 *  cascade="save-update"
	 *  @hibernate.collection-key 
	 *  column="company_id"
	 *  @hibernate.collection-one-to-many 
	 *  class="org.appfuse.model.sysadmin.Department"
	 */
	public Set getDepartments() {
		return departments;
	}

	
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public void setDepartments(Set departments) {
		this.departments = departments;
	}
	

}
