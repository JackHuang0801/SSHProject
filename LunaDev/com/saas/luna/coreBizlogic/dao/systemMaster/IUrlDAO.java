/**
 * Project Name:SSHProject
 * File Name:IUrlDAO.java
 * Package Name:com.saas.luna.coreBizlogic.dao.systemMaster
 * Date:2015年6月27日下午12:27:15
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.coreBizlogic.dao.systemMaster;

import java.util.List;

import com.saas.luna.JackFramework.core.dao.IPojoDAO;
import com.saas.luna.coreBizlogic.dto.systemMaster.ResourceQueryConditionDTO;
import com.saas.luna.coreBizlogic.pojo.systemMaster.Url;

/**
 * ClassName:IUrlDAO
 * Date:     2015年6月27日 下午12:27:15
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */
public interface IUrlDAO extends IPojoDAO<Url> {

	public List<Url> findUrlsAll();

	public List<Url> findUrlsByRoleID(ResourceQueryConditionDTO queryConditionDTO);

	public List<Url> findUrlsByFK(ResourceQueryConditionDTO queryConditionDTO, Long parentUrl_FK);
}


