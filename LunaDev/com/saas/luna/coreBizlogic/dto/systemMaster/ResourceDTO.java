/*
 * ResourceDTO.java
 * com.newzhongmei.pmi.coreBusiness.dto.systemManagement
 *
 * Copyright (c) 2010 Jack Huang Limited. All Rights Reserved.
 */
package com.saas.luna.coreBizlogic.dto.systemMaster;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

import com.saas.luna.JackFramework.core.exception.ApplicationException;
import com.saas.luna.coreBizlogic.pojo.systemMaster.Method;
import com.saas.luna.coreBizlogic.pojo.systemMaster.Resource;
import com.saas.luna.coreBizlogic.pojo.systemMaster.Url;

/**
 * @author <a href="mailto:704401701@qq.com">Jack Huang</a>
 *
 * @version V1.00 2010-5-2 上午09:51:35
 */
public class ResourceDTO {
	private Long resourceId;
	private String resourceCode;
	private String resourceName;
	private String resType;
	private String value;

	private Long pid;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	// Url父对象
	private Long parentUrl_FK;

	public Long getParentUrl_FK() {
		return parentUrl_FK;
	}

	public void setParentUrl_FK(Long parentUrlFK) {
		parentUrl_FK = parentUrlFK;
	}

	// Method父对象
	private Long parentMethod_FK;

	public Long getParentMethod_FK() {
		return parentMethod_FK;
	}

	public void setParentMethod_FK(Long parentMethodFK) {
		parentMethod_FK = parentMethodFK;
	}

	// method的url外键
	private Long url_FK;

	public Long getUrl_FK() {
		return url_FK;
	}

	public void setUrl_FK(Long urlFK) {
		url_FK = urlFK;
	}

	private String description;
	private String remark;
	private String creator;
	private Date createTime;
	private String lastUpdater;
	private Date lastUpdateTime;

	private Long roleId;
	private String roleName;

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

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public static void entity2DTO(Resource entity, ResourceDTO dto) {
		try {
			BeanUtils.copyProperties(dto, entity);
			if (entity.getId() != null) {
				dto.setResourceId(entity.getId());
			}
		} catch (Exception ex) {
			throw new ApplicationException("Exception caused while converting DTO into Entity: " + ex.getMessage());
		}
	}

	public static void dto2Entity(ResourceDTO dto, Resource entity) {
		try {
			BeanUtils.copyProperties(entity, dto);
			if (dto.getResourceId() != null) {
				entity.setId(dto.getResourceId());
			}
		} catch (Exception ex) {
			throw new ApplicationException("Exception caused while converting DTO into Entity: " + ex.getMessage());
		}
	}

	/**
	 * @param resourceDTO
	 * @param url
	 */
	public static void dto2Url(ResourceDTO resourceDTO, Url url) {
		try {
			BeanUtils.copyProperties(url, resourceDTO);
			if (resourceDTO.getResourceId() != null) {
				url.setId(resourceDTO.getResourceId());
			}
		} catch (Exception ex) {
			throw new ApplicationException("Exception caused while converting DTO into Url: " + ex.getMessage());
		}
	}

	/**
	 * @param resourceDTO
	 * @param Method
	 */
	public static void dto2Method(ResourceDTO resourceDTO, Method method) {
		try {
			BeanUtils.copyProperties(method, resourceDTO);
			if (resourceDTO.getResourceId() != null) {
				method.setId(resourceDTO.getResourceId());
			}
		} catch (Exception ex) {
			throw new ApplicationException("Exception caused while converting DTO into Method: " + ex.getMessage());
		}
	}
}