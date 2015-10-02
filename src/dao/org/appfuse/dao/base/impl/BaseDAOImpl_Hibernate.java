/*
 * project name : equinox-self
 * package name : org.appfuse.dao.impl
 * file    name : BaseDAOImpl_Hibernate.java
 * class   name : BaseDAOImpl_Hibernate
 * Created on 2005-7-25 19:16:03
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.dao.base.impl;

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.expression.Example;

import org.appfuse.dao.base.BaseDAO;
import org.appfuse.model.base.BaseObject;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

public class BaseDAOImpl_Hibernate extends HibernateDaoSupport implements
		BaseDAO {

	public void save(BaseObject object) {
		getHibernateTemplate().save(object);
	}

	public void saveOrUpdate(BaseObject object) {
		getHibernateTemplate().saveOrUpdate(object);
	}

	public void update(BaseObject object) {
		getHibernateTemplate().update(object);
	}

	/**
	 * ���ݸ�����һ�������id��ֵɾ����������Ӧ��һ����¼��
	 *
	 */
	public void remove(BaseObject object) {
		getHibernateTemplate().delete(object);

	}

	/**
	 * ���ݸ�������ɾ��һЩ��¼��
	 *
	 */
	public void removeSome(String remove_condition) {
		getHibernateTemplate().delete(remove_condition);
	}

	public void removeSome(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			BaseObject object = (BaseObject) getHibernateTemplate().load(
					BaseObject.class, ids[i]);
			getHibernateTemplate().delete(object);
		}
	
		if (logger.isDebugEnabled()) {
			logger.debug("removeUsers() is a success.");
		}
	
	}

	/**
	 * ���ݸ��������������Ӧ��һЩ���󣨾���id����ɾ����Щid��Ӧ��һЩ��¼��
	 *
	 */
	public void removeSome(BaseObject object, String[] ids) {
		String qualified_name = object.getClass().getName();
		String name = qualified_name
				.substring(qualified_name.lastIndexOf(".") + 1);
		for (int i = 0; i < ids.length; i++) {
			getHibernateTemplate().delete(
					"from " + name + " a where a.id='" + ids[i] + "'");
		}
	}

	/**
	 * ���ݸ�����һ������ɾ����������Ӧ�����м�¼��
	 *
	 */
	public void removeAll(BaseObject object) {
		
		String qualified_name = object.getClass().getName();
		String name = qualified_name
				.substring(qualified_name.lastIndexOf(".") + 1);
		getHibernateTemplate().delete("from " + name);
	}

	/**
	 * ���ݸ�����һЩ��ѯ�������в�ѯ��
	 *
	 */
	public List query(String query_condition) {
		return getHibernateTemplate().find(query_condition);
	}

	/**
	 * QBE��ѯ��ʽ :query by example
	 * ����javabean�е�һЩ���� ��ֵ��Ȼ���ҳ���������Ӧ��һЩ��¼����
	 */
	public List query(BaseObject object) {
		List list = null;
		
		//�����쳣����ʹ����: �쳣��������
		//ע�������try ,catch ���Բ�����RuntimeException��������Exception��
		//�������Լ��ٴ����е�try,catch.
		try {
			list = getSession().createCriteria(object.getClass()).add(
					Example.create(object)).list();
			//System.out.println("----"+Example.create(object).toString());
			//System.out.println(Example.create(object).toSqlString(null, null, null, null));
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		} 
		return list;
	}

	/**
	 * ���ݸ�����һ�������ҵ���������Ӧ�����м�¼��
	 *
	 */
	public List getObjects(BaseObject object) {
		//����1��
		return getHibernateTemplate().loadAll(object.getClass());
		
		//����2��
//		String qualified_name = object.getClass().getName();
//		String name = qualified_name
//				.substring(qualified_name.lastIndexOf(".") + 1);
//		return getHibernateTemplate().find(" from " + name);
	}

	/**
	 * ���ݸ�����һ�������id��ֵ�ҵ���������Ӧ��һ����¼��
	 *
	 */
	public BaseObject getObject(BaseObject object) {
		// get������load��������
		// get ����null
		// load ����ObjectNotFoundException
	
		return (BaseObject) getHibernateTemplate().get(object.getClass(),
				object.getId());
	}

}
