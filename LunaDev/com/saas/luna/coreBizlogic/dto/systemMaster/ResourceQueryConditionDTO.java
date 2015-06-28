/*
 * ResourceQueryConditionDTo.java
 * com.newzhongmei.pmi.coreBusiness.dto.systemManagement
 *
 * Copyright (c) 2010 Jack Huang Limited. All Rights Reserved.
 */
package com.saas.luna.coreBizlogic.dto.systemMaster;

import com.saas.luna.JackFramework.core.AbstractValueObject;

/**
 * @author <a href="mailto:704401701@qq.com">Jack Huang</a>
 *
 * @version V1.00 2010-5-2 上午09:52:36
 */
public class ResourceQueryConditionDTO extends AbstractValueObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 2209522439019189157L;

	private Long resourceId;
	private String resourceCode;
	private String resourceName;
	private String resourceType;

	private Long roleId;
	private String roleName;

	public ResourceQueryConditionDTO() {
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

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
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
}
