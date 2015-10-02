/*
 * project name : equinox-self
 * package name : org.appfuse.model.sysadmin
 * file    name : UserLoginInfo.java
 * class   name : UserLoginInfo
 * Created on 2005-8-10 12:06:30
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.model.sysadmin;

import java.sql.Timestamp;

import org.appfuse.model.base.BaseObject;

/**
 * @hibernate.class 
 *   table="t_user_login_info"
 *
 */
public class UserLoginInfo extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6547534862531282319L;

	private String id;

	private String computer_ip;

	private String computer_name;

	private Timestamp login_time;

	private String comments;
	
	private User user;
	
	public UserLoginInfo() {
	
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
	public String getComputer_ip() {
		return computer_ip;
	}

	/**
	 * @hibernate.property 
	 */
	public String getComputer_name() {
		return computer_name;
	}

	
	/**
	 * @hibernate.property 
	 */
	public Timestamp getLogin_time() {
		return login_time;
	}

	public User getUser() {
		return user;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setComputer_ip(String computer_ip) {
		this.computer_ip = computer_ip;
	}

	public void setComputer_name(String computer_name) {
		this.computer_name = computer_name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLogin_time(Timestamp login_time) {
		this.login_time = login_time;
	}

	

	public void setUser(User user) {
		this.user = user;
	}

}
