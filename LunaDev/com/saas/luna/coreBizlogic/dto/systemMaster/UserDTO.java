/*
 * UserDTO.java
 * com.newzhongmei.pmi.coreBusiness.dto.systemManagement
 *
 * Copyright (c) 2010 Jack Huang Limited. All Rights Reserved.
 */
package com.saas.luna.coreBizlogic.dto.systemMaster;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

import com.saas.luna.JackFramework.core.exception.ApplicationException;
import com.saas.luna.coreBizlogic.pojo.systemMaster.User;

/**
 * @author <a href="mailto:704401701@qq.com">Jack Huang</a>
 *
 * @version V1.00 2010-5-3 上午10:34:48
 */
public class UserDTO {
	private Long userId;
	private String userCode;
	private String userName;
	private String password;
	private String trueName;
	private String officePhone;
	private String mobileTelephone;
	private String email;
	private String state;

	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

	private String description;
	private String remark;
	private String creator;
	private Date createTime;
	private String lastUpdater;
	private Date lastUpdateTime;

	private Long departmentId;
	private String departmentName;

	public static void entity2DTO(User entity, UserDTO dto) {
		try {
			BeanUtils.copyProperties(dto, entity);
			if (entity.getId() != null) {
				dto.setUserId(entity.getId());
			}
		} catch (Exception ex) {
			throw new ApplicationException("Exception caused while converting DTO into Entity: " + ex.getMessage());
		}
	}

	public static void dto2Entity(UserDTO dto, User entity) {
		try {
			BeanUtils.copyProperties(entity, dto);
			if (dto.getUserId() != null) {
				entity.setId(dto.getUserId());
			}
		} catch (Exception ex) {
			throw new ApplicationException("Exception caused while converting DTO into Entity: " + ex.getMessage());
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getMobileTelephone() {
		return mobileTelephone;
	}

	public void setMobileTelephone(String mobileTelephone) {
		this.mobileTelephone = mobileTelephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
