/**
 * Project Name:SSHProject
 * File Name:IPojoDAO.java
 * Package Name:com.saas.luna.JackFramework.core.dao
 * Date:2015年6月11日下午3:31:13
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.JackFramework.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * ClassName:IPojoDAO
 * Date:     2015年6月11日 下午3:31:13
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */
public abstract interface IPojoDAO<T> {

	public abstract void save(T paramT);

	public abstract void update(T paramT);

	public abstract void updateAll(Collection<T> paramCollection);

	public abstract Object merge(T paramT);

	public abstract Collection<Object> mergeAll(Collection<T> paramCollection);

	public abstract void delete(T paramT);

	public abstract void deleteAll();

	public abstract void deleteAll(Collection<T> paramCollection);

	public abstract T findById(Serializable paramSerializable);

	public abstract T findById(Serializable paramSerializable, boolean paramBoolean);

	public abstract List<T> findAll();

	public abstract List<T> findByExample(T paramT, String[] paramArrayOfString);

	public abstract List<T> findBy(String paramString, Object paramObject);

	public abstract List<T> findBy(String paramString1, Object paramObject, String paramString2, boolean paramBoolean);

	public abstract T findUniqueBy(String paramString, Object paramObject);

	public abstract boolean isUnique(T paramT, String paramString);
}

