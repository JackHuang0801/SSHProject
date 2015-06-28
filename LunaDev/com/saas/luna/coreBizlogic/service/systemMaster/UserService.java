/**
 * Project Name:SSHProject
 * File Name:UserService.java
 * Package Name:com.saas.luna.coreBizlogic.sevice.systemMaster
 * Date:2015年6月9日下午10:23:23
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.coreBizlogic.service.systemMaster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.userdetails.UserDetails;
//import org.springframework.security.userdetails.UserDetailsService;
//import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saas.luna.JackFramework.util.Page;
import com.saas.luna.coreBizlogic.dao.systemMaster.IUrlDAO;
import com.saas.luna.coreBizlogic.dao.systemMaster.IUserDAO;
import com.saas.luna.coreBizlogic.dto.systemMaster.RoleDTO;
import com.saas.luna.coreBizlogic.dto.systemMaster.UserDTO;
import com.saas.luna.coreBizlogic.dto.systemMaster.UserQueryConditionDTO;
import com.saas.luna.coreBizlogic.pojo.systemMaster.Url;
import com.saas.luna.coreBizlogic.pojo.systemMaster.User;

/**
 * ClassName:UserService
 * Date:     2015年6月9日 下午10:23:23
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */
//不建议使用Spring的第三方注解,建议其配置用xml实现
@Service(value = "userService")
public class UserService implements IUserService, UserDetailsService {

	@Resource
	private IUserDAO userDAO;
//	@Resource
//	private RoleDAO roleDAO;
	@Resource
	private IUrlDAO urlDAO;
//	@Resource
//	private MethodDAO methodDAO;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		System.out.println("\nExecute method loadUserByUsername -> " + userName);
		List<User> usersList = userDAO.findUserByUsername(userName);
//		List<User> usersList = null;
		if (usersList.isEmpty()) {
			throw new UsernameNotFoundException("User：" + userName + " has no GrantedAuthority");
		}
		return usersList.get(0);
	}

	public Map<String, String> loadUrlAuthorities() {
		System.out.println("\nExecute method loadUrlAuthorities");
		Map<String, String> urlAuthorities = new HashMap<String, String>();
		List<Url> urlsList = urlDAO.findUrlsAll();
//		List<Url> urlsList = null;
		for (Url url : urlsList) {
			urlAuthorities.put(url.getValue(), url.getRoleAuthorities());
		}
		return urlAuthorities;
	}

	@Override
	public void createUser(UserDTO userDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(UserDTO userDTO, String action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDTO findUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UserDTO> findUsers(UserQueryConditionDTO queryConditionDTO, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleDTO> listUserRoles(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUserRoles(Long userId, List<Long> rolesId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, String> loadMethodAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}

