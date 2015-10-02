package org.appfuse.dao.base;

import java.util.List;

import org.appfuse.model.base.BaseObject;

public interface BaseDAO {
	void save(BaseObject object);

	void saveOrUpdate(BaseObject object);

	void update(BaseObject object);

	void removeAll(BaseObject object);

	void removeSome(BaseObject object, String[] ids);

	void remove(BaseObject object);

	void removeSome(String remove_condition);

	List query(String query_condition);

	List query(BaseObject object);

	BaseObject getObject(BaseObject object);

	List getObjects(BaseObject object);
}
