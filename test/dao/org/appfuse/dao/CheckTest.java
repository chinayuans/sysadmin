package org.appfuse.dao;

import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.appfuse.model.base.BaseObject;
import org.appfuse.model.sysadmin.User;

/*
 * project name : equinox-self
 * package name : 
 * file    name : Test.java
 * class   name : Test
 * Created on 2005-7-25 12:59:46
 * creator ---Joson Yuan
 * author comments:
 * 
 */

public class CheckTest extends TestCase {

	public CheckTest(String name) {
		super(name);
	}

	public void testMess() throws Exception {

		GregorianCalendar calendar = new GregorianCalendar(1976, 10, 17);
		Date birthday = new Date(calendar.getTimeInMillis());
		System.out.println(DateFormatUtils.format(birthday, "yyyy-MM-dd"));

		BaseObject user = new User();

		BeanUtils.setProperty(user, "birthday", DateFormatUtils.format(
				birthday, "yyyy-MM-dd"));
		System.out.println(((User) user).getBirthday());
		String qualified_name = user.getClass().getName();
		String name = qualified_name
				.substring(qualified_name.lastIndexOf(".") + 1);
		System.out.println(name);

		// String qualified_name="xxxx.cccc.xxxx.cccc";
		// String
		// name=qualified_name.substring(qualified_name.lastIndexOf(".")+1);
		// System.out.println(name);
		String temp = "  ";
		if (temp.trim().equals("")) {
			System.out.println("xxxx");
		}
	}

}
