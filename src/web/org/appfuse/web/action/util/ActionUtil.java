/*
 * project name : equinox-self
 * package name : org.appfuse.web
 * file    name : ActionUtil.java
 * class   name : ActionUtil
 * Created on 2005-8-2 18:23:15
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.web.action.util;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.appfuse.model.base.BaseObject;

/**
 * ��xxxAction�������Ĺ����ࡣ Created on 2005-8-2 18:26:00
 * 
 * @author ---Joson Yuan author comments:
 * 
 */
public abstract class ActionUtil {

	private static final Logger logger = Logger.getLogger(ActionUtil.class);

	// template method ���ģʽ��ʹ��
	public abstract String collectFields(String[] fields, String[] values,
			String[] types);

	public String getQueryCondition(BaseObject object)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		if (logger.isDebugEnabled()) {
			logger.debug("getQueryCondition() - start");
		}

		String query_condition = formCondition(object);

		// ��ʾ��Ϣ��ʾ
		if (logger.isDebugEnabled()) {
			logger.debug("getQueryCondition() -  : String query_condition = "
					+ query_condition);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getQueryCondition() - end");
		}

		return query_condition;
	}

	private String formCondition(BaseObject object)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String fullClassName = object.getClass().getName();
		String className = fullClassName.substring(fullClassName
				.lastIndexOf(".") + 1);

		Map properties_map = BeanUtils.describe(object);

		Set properties_set = properties_map.keySet();

		// bean�����ԣ�
		String fields[] = new String[properties_set.size() - 1];
		// bean������ֵ��
		String values[] = new String[properties_set.size() - 1];
		// bean�����Ե����ͣ�
		String types[] = new String[properties_set.size() - 1];

		// ��javabean���е�����ȫ��ȡ�������õ�������ȥ��
		int j = 0;
		for (Iterator iter = properties_set.iterator(); iter.hasNext();) {

			String field = (String) iter.next();
			if (!field.equalsIgnoreCase("class")) {

				// ��õ�bean��������
				fields[j] = field;

				// ��ö�Ӧ���Ե�ֵ
				String value = "";
				if (properties_map.get(field) == null
						|| properties_map.get(field).toString().equals("")) {
					value = "";
					values[j] = value;
				} else {
					value = properties_map.get(field).toString();
					values[j] = value;
				}

				// ��ö�Ӧ���Ե�����
				String type = (PropertyUtils.getPropertyType(object, field))
						.getName();
				types[j] = type;
				j++;
			}

		}

		if (logger.isDebugEnabled()) {
			logger.debug("print bean's properties - start");
			for (int i = 0; i < types.length; i++) {

				logger.debug(fields[i]);

				logger.debug(values[i]);

				logger.debug(types[i]);
			}
			logger.debug("print bean's properties - end");
		}

		// template method ���ģʽ��ʹ��
		String where_condition = collectFields(fields, values, types);

		// where_condition ������� һ��û������������һ��������" xxx and xxx and "��
		String final_condition = "";
		if (!where_condition.equalsIgnoreCase("")) {
			final_condition = " where "
					+ where_condition.substring(0, where_condition.length()
							- " and ".length());
		}

		String query_condition = " from " + className + " " + final_condition;

		if (logger.isDebugEnabled()) {
			logger.debug(query_condition);
		}
		return query_condition;
	}

	// �����������ֻ����javabean������Ϊ��������
	protected boolean isValidType(String type) {
		if (!type.equals("java.util.Set")
				&& !type.equals("java.util.List")
				&& !type.equals("java.util.Map")
				&& (type.equals("java.lang.String")
						|| type.equals("java.util.Date")
						|| type.equals("java.util.Calendar")
						|| type.equals("java.sql.Date")
						|| type.equals("java.sql.Time")
						|| type.equals("java.sql.Timestamp")
						|| type.equals("java.lang.Boolean")
						|| type.equals("java.lang.Byte")
						|| type.equals("java.lang.Character")
						|| type.equals("java.lang.Double")
						|| type.equals("java.lang.Float")
						|| type.equals("java.lang.Integer")
						|| type.equals("java.lang.Long")
						|| type.equals("java.lang.Short")
						|| type.equals("java.lang.Number")
						|| type.equals("java.math.BigDecimal")|| type
						.equals("java.lang.StringBuffer")))
			return true;
		else
			return false;

	}

}
