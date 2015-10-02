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
	 * 根据给定的一个对象的id的值删除这个对象对应的一条记录。
	 *
	 */
	public void remove(BaseObject object) {
		getHibernateTemplate().delete(object);

	}

	/**
	 * 根据给定条件删除一些记录。
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
	 * 根据给定的类和这个类对应的一些对象（具有id），删除这些id对应的一些记录。
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
	 * 根据给定的一个对象删除这个对象对应的所有记录。
	 *
	 */
	public void removeAll(BaseObject object) {
		
		String qualified_name = object.getClass().getName();
		String name = qualified_name
				.substring(qualified_name.lastIndexOf(".") + 1);
		getHibernateTemplate().delete("from " + name);
	}

	/**
	 * 根据给定的一些查询条件进行查询。
	 *
	 */
	public List query(String query_condition) {
		return getHibernateTemplate().find(query_condition);
	}

	/**
	 * QBE查询方式 :query by example
	 * 赋给javabean中的一些属性 的值，然后找出这个对象对应的一些记录来。
	 */
	public List query(BaseObject object) {
		List list = null;
		
		//以下异常处理使用了: 异常链技术。
		//注意代码中try ,catch 可以不捕获RuntimeException及其子类Exception，
		//这样可以减少代码中的try,catch.
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
	 * 根据给定的一个对象找到这个对象对应的所有记录。
	 *
	 */
	public List getObjects(BaseObject object) {
		//方法1：
		return getHibernateTemplate().loadAll(object.getClass());
		
		//方法2：
//		String qualified_name = object.getClass().getName();
//		String name = qualified_name
//				.substring(qualified_name.lastIndexOf(".") + 1);
//		return getHibernateTemplate().find(" from " + name);
	}

	/**
	 * 根据给定的一个对象的id的值找到这个对象对应的一条记录。
	 *
	 */
	public BaseObject getObject(BaseObject object) {
		// get方法与load方法区别：
		// get 返回null
		// load 返回ObjectNotFoundException
	
		return (BaseObject) getHibernateTemplate().get(object.getClass(),
				object.getId());
	}

}
