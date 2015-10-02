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
	 * ��ȷ��ѯ�� ���ݴ����javabean���󣬰�������������,ֵȡ������ Ȼ��ƴ�ճ� from xxx where xxx=ddd and
	 * fff=ggg ��ʽ��
	 * 
	 * @param object
	 * @return ���ַ��ز��� 1.from + ������ 2.from + ������ + where ����1=xxx and ����2=xxxx
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

		// ��javabean������������ȡ��������ֵ������
		// ƴ�ճ�where ���
		String where_condition = "";
		for (int i = 0; i < fields.length; i++) {
			// ���û����������ֵ��������������
			// �����������Ϊ����һ�Զ����͵�java.util.Set���ͣ�����������
			if (!values[i].equals("") && isValidType(types[i])) {

				String short_className = types[i].substring(types[i]
						.lastIndexOf("."));				
				
				//�γɾ�ȷ��ѯ��
				if (short_className.equals(".String")
						|| short_className.equals(".Date")) {
					where_condition = where_condition + fields[i] + " = '"
							+ values[i] + "'" + " and ";
				} else { // �����ַ�������
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
