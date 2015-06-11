/**
 * Project Name:SSHProject
 * File Name:IUserDAO.java
 * Package Name:com.saas.luna.coreBizlogic.dao.systemManagement
 * Date:2015年6月11日下午3:37:07
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.coreBizlogic.dao.systemMaster;

import java.util.List;

import com.saas.luna.JackFramework.core.dao.IPojoDAO;
import com.saas.luna.JackFramework.util.Page;
import com.saas.luna.coreBizlogic.dto.systemMaster.UserQueryConditionDTO;
import com.saas.luna.coreBizlogic.pojo.systemMaster.User;

/**
 * ClassName:IUserDAO
 * Date:     2015年6月11日 下午3:37:07
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */
public interface IUserDAO extends IPojoDAO<User> {

	public Page<User> findUsers(int pageNumber, int pageSize);

	public Page<User> findUsers(UserQueryConditionDTO queryConditionDTO, int pageNumber, int pageSize);

	public List<User> findUserByUsername(String userName);
}

