/*
 * RoleDTO.java
 * com.newzhongmei.pmi.coreBusiness.dto.systemManagement
 *
 * Copyright (c) 2010 Jack Huang Limited. All Rights Reserved.
 */
package com.saas.luna.coreBizlogic.dto.systemMaster;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

import com.saas.luna.JackFramework.core.exception.ApplicationException;
import com.saas.luna.coreBizlogic.pojo.systemMaster.Role;

/**
 * @author <a href="mailto:704401701@qq.com">Jack Huang</a>
 *
 * @version V1.00 2010-5-2 上午09:48:50
 */
public class RoleDTO {
	private Long roleId;
	private String roleCode;
	private String roleName;
	private String isInnerRole;

	private String description;
	private String creator;
	private Date createTime;
	private String lastUpdater;
	private Date lastUpdateTime;

	private Long userId;
	private String userName;
	private String userDescription;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getIsInnerRole() {
		return isInnerRole;
	}

	public void setIsInnerRole(String isInnerRole) {
		this.isInnerRole = isInnerRole;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdater() {
		return lastUpdater;
	}

	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public static void entity2DTO(Role entity, RoleDTO dto) {
		try {
			BeanUtils.copyProperties(dto, entity);
			if (entity.getId() != null) {
				dto.setRoleId(entity.getId());
			}
		} catch (Exception ex) {
			throw new ApplicationException("Exception caused while converting DTO into Entity: " + ex.getMessage());
		}
	}

	public static void dto2Entity(RoleDTO dto, Role entity) {
		try {
			BeanUtils.copyProperties(entity, dto);
			if (dto.getRoleId() != null) {
				entity.setId(dto.getRoleId());
			}
		} catch (Exception ex) {
			throw new ApplicationException("Exception caused while converting DTO into Entity: " + ex.getMessage());
		}
	}
}
