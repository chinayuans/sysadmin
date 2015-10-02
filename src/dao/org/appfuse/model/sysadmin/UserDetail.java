/*
 * project name : sysadmin
 * package name : org.appfuse.model.sysadmin
 * file    name : UserDetail.java
 * class   name : UserDetail
 * Created on 2006-2-15 10:58:52
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.model.sysadmin;

/**
 * Created on 2006-2-15 10:58:52
 * @author ---Joson Yuan
 * author comments:
 */
public class UserDetail {
	private String id_card;
	private String gendar;
	private String born_date;
	private String major;
	private String degree;
	private String e_mail;
	private String homeTelePhone;
	private String mobilePhone;
	private User user;
	
	
	public UserDetail() {
	
	}
	
	/**
	 * @hibernate.property
	 *  not-null="false"
	 */
	public String getBorn_date() {
		return born_date;
	}
	
	/**
	 * @hibernate.property
	 * not-null="false"
	 */
	public String getDegree() {
		return degree;
	}
	
	/**
	 * @hibernate.property
	 * not-null="false"
	 */
	public String getE_mail() {
		return e_mail;
	}
	
	/**
	 * @hibernate.property
	 * not-null="false"
	 */
	public String getGendar() {
		return gendar;
	}
	
	/**
	 * @hibernate.property
	 * not-null="false"
	 */
	public String getHomeTelePhone() {
		return homeTelePhone;
	}

	/**
	 * @hibernate.property
	 * not-null="false"
	 */
	public String getId_card() {
		return id_card;
	}

	/**
	 * @hibernate.property
	 * not-null="false"
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * @hibernate.property
	 * not-null="false"
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	/**
	 *  
	 */
	public User getUser() {
		return user;
	}
	
	public void setBorn_date(String born_date) {
		this.born_date = born_date;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public void setGendar(String gendar) {
		this.gendar = gendar;
	}
	public void setHomeTelePhone(String homeTelePhone) {
		this.homeTelePhone = homeTelePhone;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
}
