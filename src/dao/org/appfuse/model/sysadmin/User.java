/*
 * project name : equinox-self
 * package name : org.appfuse.model
 * file    name : User.java
 * class   name : User
 * Created on 2005-7-22 9:55:07
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.model.sysadmin;



import java.sql.Date;

import org.appfuse.model.base.BaseObject;
/**
 * @hibernate.class 
 *   table="t_user"
 *
 */
public class User extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3626635863850315195L;

	private String id;
	private String user_id;
	private String user_name;
	private String user_password;
	private String firstName;
	private String lastName;
	
   
	private Date birthday;
	private String comments;
	
	 //�����ֶ�ʹ��hibernate��"���һ"ӳ��. many-to-one
	private UserStatus userStatus=new UserStatus();
	
	//�����ֶ�ʹ��hibernate��"��Զ�"ӳ��.   many-to-many
//	private Set roles=new HashSet();
	
	//����һ���ֶ�ʹ��hibernate��"component"��ϵ��
	private UserDetail detail =new UserDetail();
	
	//����һ���ֶ�ʹ��hibernate��"һ��һ"ӳ��ֻ��һ���ֶο��Դﵽʵ��һһӳ�䡣  one-to-one
	private UserNormalAddress normalAddress=new UserNormalAddress();
	
	
	//���������ֶ�ʹ��hibernate�ġ�һ��һ������ӳ���еĶ���ֶ�ʵ��һһӳ�䡣������δ�ɹ�
	//����UserAddress<-UserHomeAddress;UserAddress<-UserCompanyAddress; 
	//����UserAddress. UserHomeAddress,UserCompanyAddress��UserAddress�����ࡣ
	//Ҫ��User����UserHomeAddress.UserCompanyAddress���ﵽһһӳ�䡣
	
//	private UserHomeAddress homeAddress=new UserHomeAddress();
//	private UserCompanyAddress companyAddress=new UserCompanyAddress();

	public User() {
	
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
	 *  length="10"
	 *  not-null="true"
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @hibernate.property 
	 *  length="30"
	 *  not-null="true"
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @hibernate.property 
	 * length="10"
	 */
	public String getUser_password() {
		return user_password;
	}
	
	/**
	 * @hibernate.property 
	 * length="30"
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @hibernate.property  
	 * length="30"
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @hibernate.many-to-one 
	 * column = "userstatus_id"
	 * class = "org.appfuse.model.sysadmin.UserStatus"
	 * cascade = "save-update"
	 */
	public UserStatus getUserStatus() {
		return userStatus;
	}
	
	/**
	 * @hibernate.property 
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @hibernate.property 
	 */
	public String getComments() {
		return comments;
	}
	


	/**
	 * @hibernate.component 
	 *  class="org.appfuse.model.sysadmin.UserDetail"
	 */
	public UserDetail getDetail() {
		return detail;
	}


	/**
	 * @hibernate.many-to-one 
	 * 	column="normal_address_id"
	 *  class="org.appfuse.model.sysadmin.UserNormalAddress"
	 *  cascade="all"
	 */
	public UserNormalAddress getNormalAddress() {
		return normalAddress;
	}

	
//	/**
//	 * @hibernate.many-to-one 
//	 * column="company_address_id"
//	 * class="org.appfuse.model.sysadmin.UserCompanyAddress"
//	 * cascade="all"
//	 */
//	public UserCompanyAddress getCompanyAddress() {
//		return companyAddress;
//	}
//
//	/**
//	 * @hibernate.many-to-one 
//	 * column="home_address_id"
//	 * class="org.appfuse.model.sysadmin.UserHomeAddress"
//	 * cascade="all"
//	 */
//	public UserHomeAddress getHomeAddress() {
//		return homeAddress;
//	}
	
//	/**
//	 * @hibernate.set 
//	 *  table="t_user_role"
//	 *  lazy="true"
//	 *  inverse="false"
//	 *  cascade="save-update"
//	 * @hibernate.collection-key 
//	 *  column="user_id"
//	 * @hibernate.collection-many-to-many 
//	 *  column="role_id"
//	 *  class="org.appfuse.model.sysadmin.Role"
//	 */
//	public Set getRoles() {
//		return roles;
//	}
//
//
//	public void setRoles(Set roles) {
//		this.roles = roles;
//	}

	public void setNormalAddress(UserNormalAddress normalAddress) {
		this.normalAddress = normalAddress;
	}




	public void setDetail(UserDetail detail) {
		this.detail = detail;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}	

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}	
		
//	public void setCompanyAddress(UserCompanyAddress companyAddress) {
//	this.companyAddress = companyAddress;
//}
//
//
//public void setHomeAddress(UserHomeAddress homeAddress) {
//	this.homeAddress = homeAddress;
//}
}
