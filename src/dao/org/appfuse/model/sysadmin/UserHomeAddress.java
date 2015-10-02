///*
// * project name : sysadmin
// * package name : org.appfuse.model.sysadmin
// * file    name : UserHomeAddress.java
// * class   name : UserHomeAddress
// * Created on 2006-2-15 9:38:59
// * creator ---Joson Yuan
// * author comments:
// * 
// */
//package org.appfuse.model.sysadmin;
//
///**
// * @hibernate.subclass 
// */
//public class UserHomeAddress extends UserAddress {
//	private User user = new User();
//
//	/**
//	 * @hibernate.one-to-one 
//	 * class="org.appfuse.model.sysadmin.User"
//	 * property-ref="homeAddress"
//	 */
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//}
