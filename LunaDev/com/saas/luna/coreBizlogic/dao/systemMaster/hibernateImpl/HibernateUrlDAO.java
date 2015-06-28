/**
 * Project Name:SSHProject
 * File Name:HibernateUrlDAO.java
 * Package Name:com.saas.luna.coreBizlogic.dao.systemMaster.hibernateImpl
 * Date:2015年6月27日下午12:28:06
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.coreBizlogic.dao.systemMaster.hibernateImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.saas.luna.JackFramework.core.dao.HibernateSpringPojoDAO;
import com.saas.luna.coreBizlogic.dao.systemMaster.IUrlDAO;
import com.saas.luna.coreBizlogic.dto.systemMaster.ResourceQueryConditionDTO;
import com.saas.luna.coreBizlogic.pojo.systemMaster.Url;

/**
 * ClassName:HibernateUrlDAO
 * Date:     2015年6月27日 下午12:28:06
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */
public class HibernateUrlDAO extends HibernateSpringPojoDAO<Url> implements IUrlDAO {

	@Override
	public List<Url> findUrlsAll() {
		StringBuilder hqlStringBuilder = new StringBuilder();
		hqlStringBuilder.append("FROM Url url");
		final String hqlString = hqlStringBuilder.toString();

		List<Url> urlsList = (List<Url>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session hibernateSession) throws HibernateException {
				Query query = hibernateSession.createQuery(hqlString);
				return query.list();
			}
		});
		return urlsList;
	}

	@Override
	public List<Url> findUrlsByRoleID(ResourceQueryConditionDTO queryConditionDTO) {
		StringBuilder hqlStringBuilder = new StringBuilder();
		hqlStringBuilder.append("FROM Url url LEFT OUTER JOIN FETCH url.roles role");
		hqlStringBuilder.append(" WHERE role.id = :roleId");
		hqlStringBuilder.append(" ORDER BY url.id ASC");
		final String hqlString = hqlStringBuilder.toString();

		List<Url> urlsList = (List<Url>) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session hibernateSession) throws HibernateException {
				Query query = hibernateSession.createQuery(hqlString);
				query.setParameter("roleId", queryConditionDTO.getRoleId(),  LongType.INSTANCE);
				return query.list();
			}
		});
		return urlsList;
	}

	@Override
	public List<Url> findUrlsByFK(ResourceQueryConditionDTO queryConditionDTO, Long parentUrl_FK) {
		StringBuilder hqlStringBuilder = new StringBuilder();
		hqlStringBuilder.append("FROM Url url");
		if (parentUrl_FK == 0) {
			hqlStringBuilder.append(" WHERE url.parentUrl.id IS NULL");
		} else {
			hqlStringBuilder.append(" WHERE url.parentUrl.id = :parentUrl_FK");
		}
		hqlStringBuilder.append(" ORDER BY url.id ASC");
		final String hqlString = hqlStringBuilder.toString();

		List<Url> urlsList = (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session hibernateSession) throws HibernateException {
				Query query = hibernateSession.createQuery(hqlString);
				if (parentUrl_FK != 0) {
					query.setParameter("parentUrl_FK", parentUrl_FK, LongType.INSTANCE);
				}
				return query.list();
			}
		});
		// System.out.println("url子节点的数量:" + urlsList.size());
		return urlsList;
	}

}

