/*
 * project name : sysadmin
 * package name : org.appfuse.web.action
 * file    name : ActionUtil_ExactQuery.java
 * class   name : ActionUtil_ExactQuery
 * Created on 2005-10-11 18:43:10
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.web.action.util;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

public class ActionUtil_ExactQuery extends ActionUtil {

	private static final Logger logger = Logger
			.getLogger(ActionUtil_ExactQuery.class);

	/**
	 * 精确查询。 根据传入的javabean对象，把它的所有属性,值取出来。 然后拼凑成 from xxx where xxx=ddd and
	 * fff=ggg 形式。
	 * 
	 * @param object
	 * @return 两种返回参数 1.from + 对象名 2.from + 对象名 + where 属性1=xxx and 属性2=xxxx
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 *             Created on 2005-7-31 9:42:04
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public String collectFields(String[] fields, String[] values, String[] types) {
		if (logger.isDebugEnabled()) {
			logger.debug("collectFields() - start");
		}

		// 从javabean的所有属性中取出有输入值的属性
		// 拼凑成where 语句
		String where_condition = "";
		for (int i = 0; i < fields.length; i++) {
			// 如果没有输入条件值，错过这个条件。
			// 如果属性类型为带有一对多类型的java.util.Set类型，错过这个条件
			if (!values[i].equals("") && isValidType(types[i])) {

				String short_className = types[i].substring(types[i]
						.lastIndexOf("."));				
				
				//形成精确查询。
				if (short_className.equals(".String")
						|| short_className.equals(".Date")) {
					where_condition = where_condition + fields[i] + " = '"
							+ values[i] + "'" + " and ";
				} else { // 不是字符串类型
					where_condition = where_condition + fields[i] + " = "
							+ values[i] + "" + " and ";
				}
				
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("collectFields() - end");
		}
		return where_condition;
	}

	
}
