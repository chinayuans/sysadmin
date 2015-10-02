///*
// * project name : sysadmin
// * package name : org.appfuse.model.sysadmin
// * file    name : UserCompanyAddress.java
// * class   name : UserCompanyAddress
// * Created on 2006-2-15 9:40:34
// * creator ---Joson Yuan
// * author comments:
// * 
// */
//package org.appfuse.model.sysadmin;
//
///**
// * @hibernate.subclass 
// */
//public class UserCompanyAddress extends UserAddress {
//	private User user=new User();
//
//	/**
//	 * @hibernate.one-to-one 
//	 * class="org.appfuse.model.sysadmin.User"
//	 * property-ref="companyAddress"
//	 */
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//	
//}
