///*
// * project name : sysadmin
// * package name : org.appfuse.model.sysadmin
// * file    name : UserAddress.java
// * class   name : UserAddress
// * Created on 2006-2-15 9:13:40
// * creator ---Joson Yuan
// * author comments:
// * 
// */
//package org.appfuse.model.sysadmin;
//
//import org.appfuse.model.BaseObject;
//
///**
// * @hibernate.class 
// *   table="t_user_address"
// * 
// */
//public  class UserAddress extends BaseObject {
//	private String id;
//
//	private String country;
//
//	private String province;
//
//	private String city;
//
//	private String street;
//
//	private String zipcode;	
//	
//	/**
//	 * @hibernate.id 
//	 *   generator-class="uuid.hex"	
//	 *   not-null="true"
//	 *   length="32"
//	 *
//	 */
//	public String getId() {
//		return id;
//	}
//
//	/**
//	 * @hibernate.property 
//	 */
//	public String getCity() {
//		return city;
//	}
//
//	/**
//	 * @hibernate.property 
//	 */
//	public String getCountry() {
//		return country;
//	}
//
//	/**
//	 * @hibernate.property 
//	 */
//	public String getProvince() {
//		return province;
//	}
//
//	/**
//	 * @hibernate.property 
//	 */
//	public String getStreet() {
//		return street;
//	}
//
//	/**
//	 * @hibernate.property 
//	 */
//	public String getZipcode() {
//		return zipcode;
//	}
//
//	
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public void setCountry(String country) {
//		this.country = country;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public void setProvince(String province) {
//		this.province = province;
//	}
//
//	public void setStreet(String street) {
//		this.street = street;
//	}
//
//	public void setZipcode(String zipcode) {
//		this.zipcode = zipcode;
//	}
//
//	
//}
