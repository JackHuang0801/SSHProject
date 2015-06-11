/**
 * Project Name:SSHProject
 * File Name:HibernateSpringEntityDAO.java
 * Package Name:com.saas.luna.JackFramework.core.dao
 * Date:2015年6月11日下午3:55:33
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.JackFramework.core.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.util.ReflectionUtils;

import com.saas.luna.JackFramework.commons.util.Assert;

/**
 * ClassName:HibernateSpringEntityDAO
 * Date:     2015年6月11日 下午3:55:33
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */
public abstract class HibernateSpringPojoDAO<T> extends HibernateDaoSupport implements IPojoDAO<T> {
	private Class<T> entityClass;

	public HibernateSpringPojoDAO() {
		this.entityClass = ((Class) ((java.lang.reflect.ParameterizedType) super.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0]);
	}

	public void save(T entity) throws DataAccessException {
		getHibernateTemplate().save(entity);
	}

	public void update(T entity) throws DataAccessException {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * Warning, do nothing in this method.
	 *
	 * Spring4 已经将org.springframework.orm.hibernate4.support的HibernateDaoSupport里面saveOrUpdateAll（）删除掉，
	 * Hiberante的saveOrUpdateAll方法是针对每一个对象的保存/更新都开启和关闭事务，这对大量数据操作存在一定的效率问题
	 */
	public void updateAll(Collection<T> entities) throws DataAccessException {
//		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	public Object merge(T entity) throws DataAccessException {
		return getHibernateTemplate().merge(entity);
	}

	public Collection<Object> mergeAll(Collection<T> entities) throws DataAccessException {
		List updatedPersistentInstances = null;
		Iterator i$;
		if ((entities != null) && (entities.size() > 0)) {
			updatedPersistentInstances = new ArrayList();
			for (i$ = entities.iterator(); i$.hasNext();) {
				Object entity = i$.next();
				updatedPersistentInstances.add(getHibernateTemplate().merge(entity));
			}
		}

		return updatedPersistentInstances;
	}

	public void delete(T entity) throws DataAccessException {
		getHibernateTemplate().delete(entity);
	}

	public void deleteAll() throws DataAccessException {
		getHibernateTemplate().deleteAll(findByCriteria(new Criterion[0]));
	}

	public void deleteAll(Collection<T> entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	public T findById(Serializable id) throws DataAccessException {
		return getHibernateTemplate().get(getEntityClass(), id);
	}

	public T findById(Serializable id, boolean lock) throws DataAccessException {
		Object entity;
		if (lock) {
			entity = getHibernateTemplate().get(getEntityClass(), id, LockMode.UPGRADE);
		} else {
			entity = getHibernateTemplate().get(getEntityClass(), id);
		}

		return (T) entity;
	}

	public List<T> findAll() {
		return findByCriteria(new Criterion[0]);
	}

	public List<?> findByExample(T exampleInstance) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		Example example = Example.create(exampleInstance);
		detachedCriteria.add(example);

		return getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	public List<T> findByExample(T exampleInstance, String[] excludedProperties) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		Example example = Example.create(exampleInstance);
		for (String excludedProperty : excludedProperties) {
			example.excludeProperty(excludedProperty);
		}
		detachedCriteria.add(example);

		return (List<T>) getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	public List<T> findBy(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return findByCriteria(new Criterion[] { Restrictions.eq(propertyName, value) });
	}

	public List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc) {
		Assert.hasText(propertyName);
		Assert.hasText(orderBy);
		return findByCriteria(orderBy, isAsc, new Criterion[] { Restrictions.eq(propertyName, value) });
	}

	public T findUniqueBy(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(new Criterion[] { Restrictions.eq(propertyName, value) }).uniqueResult();
	}

	public boolean isUnique(T entity, String uniquePropertyNames) {
		Assert.hasText(uniquePropertyNames);

		Criteria criteria = createCriteria(new Criterion[0]).setProjection(Projections.rowCount());
		String[] namesList = uniquePropertyNames.split(",");

		try {
			for (String name : namesList) {
				criteria.add(Restrictions.eq(name, PropertyUtils.getProperty(entity, name)));

			}

			String idName = getIdName();

			Serializable id = getId(entity);

			if (id != null)
				criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}

		return (((Integer) criteria.uniqueResult()).intValue() == 0);
	}

	protected Serializable getId(T entity) throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Assert.notNull(entity);
		Assert.notNull(this.entityClass);

		return ((Serializable) PropertyUtils.getProperty(entity, getIdName()));
	}

	protected String getIdName() {
		Assert.notNull(getEntityClass());

		ClassMetadata meta = getSessionFactory().getClassMetadata(getEntityClass());
		Assert.notNull(meta, "Class " + getEntityClass() + " not define in hibernate session factory.");
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, getEntityClass().getSimpleName() + " has no identifier property define.");

		return idName;
	}

	protected List<T> findByCriteria(Criterion[] criterions) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		for (Criterion criterion : criterions) {
			detachedCriteria.add(criterion);
		}

		return (List<T>) getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	protected List<T> findByCriteria(String orderBy, boolean isAsc, Criterion[] criterions) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());

		for (Criterion criterion : criterions) {
			detachedCriteria.add(criterion);
		}

		if (isAsc) {
			detachedCriteria.addOrder(Order.asc(orderBy));
		} else {
			detachedCriteria.addOrder(Order.desc(orderBy));
		}

		return (List<T>) getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	protected Criteria createCriteria(Criterion[] criterions) {
//		Criteria criteria = getSession(false).createCriteria(getEntityClass());
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(getEntityClass());
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}

		return criteria;
	}

	protected Criteria createCriteria(String orderBy, boolean isAsc, Criterion[] criterions) {
		Assert.hasText(orderBy);

		Criteria criteria = createCriteria(criterions);

		if (isAsc) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}

		return criteria;
	}

	public Class<T> getEntityClass() {
		return this.entityClass;
	}
}

