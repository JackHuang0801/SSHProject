/**
 * Project Name:SSHProject
 * File Name:UserQueryConditionDTO.java
 * Package Name:com.saas.luna.coreBizlogic.dto.systemManagement
 * Date:2015年6月11日下午3:50:12
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.coreBizlogic.dto.systemMaster;
/**
 * ClassName:UserQueryConditionDTO
 * Date:     2015年6月11日 下午3:50:12
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */
public class UserQueryConditionDTO {

	private String userCode;
	private String userName;
	private String state;

	private Long roleId;
	private String roleName;

	private Long departmentId;
	private String departmentName;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}

