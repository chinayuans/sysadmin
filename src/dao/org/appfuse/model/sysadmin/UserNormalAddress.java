/*
 * project name : sysadmin
 * package name : org.appfuse.model.sysadmin
 * file    name : UserNormalAddress.java
 * class   name : UserNormalAddress
 * Created on 2006-2-15 9:13:40
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.model.sysadmin;

import org.appfuse.model.base.BaseObject;

/**
 * @hibernate.class 
 *   table="t_user_normal_address"
 * 
 */
public  class UserNormalAddress extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6055699757005153816L;

	private String id;

	private String country;

	private String province;

	private String city;

	private String street;

	private String zipcode;	
	
	//注意不可以private User user=new User(),
	//否则会导致:nested exception is net.sf.hibernate.InstantiationException: 
	//Could not instantiate entity: org.appfuse.model.sysadmin.User
	private User user;
	
	public UserNormalAddress() {
	
	}
	
	/**
	 * @hibernate.id 
	 *   generator-class="uuid.hex"	
	 *   not-null="true"
	 *   length="32"
	 *
	 */
	public String getId() {
		return id;
	}

	/**
	 * @hibernate.property 
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @hibernate.property 
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @hibernate.property 
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @hibernate.property 
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @hibernate.property 
	 */
	public String getZipcode() {
		return zipcode;
	}
	
	/**
	 * @hibernate.one-to-one 
	 * class="org.appfuse.model.sysadmin.User"
	 * property-ref="normalAddress"
	 */
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	
}
