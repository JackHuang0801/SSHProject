/**
 * Project Name:SSHProject
 * File Name:HibernateUserDAO.java
 * Package Name:com.saas.luna.coreBizlogic.dao.hibernate
 * Date:2015年6月11日下午3:52:45
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.coreBizlogic.dao.systemMaster.hibernateImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.saas.luna.JackFramework.core.dao.HibernateSpringPojoDAO;
import com.saas.luna.JackFramework.util.Page;
import com.saas.luna.coreBizlogic.dao.systemMaster.IUserDAO;
import com.saas.luna.coreBizlogic.dto.systemMaster.UserQueryConditionDTO;
import com.saas.luna.coreBizlogic.pojo.systemMaster.User;

/**
 * ClassName:HibernateUserDAO
 * Date:     2015年6月11日 下午3:52:45
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */
public class HibernateUserDAO extends HibernateSpringPojoDAO<User> implements IUserDAO {

	@Override
	public Page<User> findUsers(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findUsers(UserQueryConditionDTO queryConditionDTO, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findUserByUsername(String userName) {
		StringBuilder hqlStringBuilder = new StringBuilder();
		hqlStringBuilder.append("FROM User user");
		hqlStringBuilder.append(" WHERE user.userName = :userName");
		// hqlStringBuilder.append(" AND user.disabled = false");
		final String hqlString = hqlStringBuilder.toString();

		List<User> usersList = (List<User>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session hibernateSession) throws HibernateException {
				Query query = hibernateSession.createQuery(hqlString);
				query.setParameter("userName", userName, "");
				return query.list();
			}
		});
		return usersList;
	}
}

