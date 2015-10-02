/*
 * project name : equinox-self
 * package name : org.appfuse.model.sysadmin
 * file    name : Department.java
 * class   name : Department
 * Created on 2005-8-10 11:36:19
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.model.sysadmin;

import org.appfuse.model.base.BaseObject;


/**
 * @hibernate.class 
 *   table="t_department"
 *
 */
public class Department extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -676030181791738098L;
	private String id;
	private String name;
	private String comments;
	private Company company=new Company();
	
	public Department(){
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
	 * @hibernate.many-to-one 
	 * column="company_id"
	 * class="org.appfuse.model.sysadmin.Company"
	 * cascade="save-update"
	 * 
	 */
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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
	
}
