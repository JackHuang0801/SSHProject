/**
 * Project Name:SSHProject
 * File Name:IUserService.java
 * Package Name:com.saas.luna.coreBizlogic.service.systemMaster
 * Date:2015年6月26日下午6:36:32
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.coreBizlogic.service.systemMaster;

import java.util.List;
import java.util.Map;



import org.springframework.security.core.userdetails.UserDetails;

import com.saas.luna.JackFramework.util.Page;
import com.saas.luna.coreBizlogic.dto.systemMaster.RoleDTO;
import com.saas.luna.coreBizlogic.dto.systemMaster.UserDTO;
import com.saas.luna.coreBizlogic.dto.systemMaster.UserQueryConditionDTO;


/**
 * ClassName:IUserService
 * Date:     2015年6月26日 下午6:36:32
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */
public interface IUserService {

	public void createUser(UserDTO userDTO);

	public void updateUser(UserDTO userDTO, String action);

	public void deleteUser(Long userId);

	public UserDTO findUser(Long userId);

	public Page<UserDTO> findUsers(UserQueryConditionDTO queryConditionDTO, int page, int size);

	public List<RoleDTO> listUserRoles(Long userId);

	public void saveUserRoles(Long userId, List<Long> rolesId);

	/**
	 * Spring Security提供了2个认证的接口，分别用于模拟用户和权限，以及读取用户和权限的操作方法。这两个接口分别是：
	 * UserDetails和UserDetailsService。
	 *
	 * 其中UserDetailsService可由DAO、JDBC实现，这里先由Service实现，但是实际读取用户和权限的操作方法，仍然需要调
	 * 用Hibernate的DAO。
	 *
	 * @param userName
	 * @return
	 */
	public UserDetails loadUserByUsername(String userName);

	/**
	 * 由于资源信息对于每个项目来说，相对固定，所以我们可以将他们在系统启动的时候就load到内存作为缓存。这里做法很多，
	 * 我给出的示例是将资源的存放在servletContext中。
	 *
	 * ServletContextLoaderListener需要调用该方法,同上，实际读取完成url资源的读取方法，
	 * 仍然需要调用Hibernate的DAO。
	 *
	 * @return Map<String, String>
	 */
	public Map<String, String> loadUrlAuthorities();

	/**
	 * ServletContextLoaderListener需要调用该方法,同上，实际读取完成method资源的读取方法，
	 * 仍然需要调用Hibernate的DAO。
	 *
	 * @return Map<String, String>
	 */
	public Map<String, String> loadMethodAuthorities();
}

