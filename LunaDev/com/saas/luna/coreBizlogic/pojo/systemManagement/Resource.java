/*
 * Res.java
 * com.newzhongmei.pmi.coreBusiness.entity.systemManagement
 * 
 * Copyright (c) 2010 Jack Huang Limited. All Rights Reserved.
 */
package com.saas.luna.coreBizlogic.pojo.systemManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:704401701@qq.com">Jack Huang</a>
 * 
 * @version V1.00 2010-5-2 上�?�09:28:20
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "resourceType", discriminatorType = DiscriminatorType.STRING)
@Table(name = "T_resource")
public class Resource {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4598229086719103918L;

	private Long id;

	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String resourceCode;
	private String resourceName;
	private String resourceType;
	private String value;

	private Set<Role> roles = new HashSet<Role>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "resources", targetEntity = Role.class)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Column(length = 20, nullable = false)
	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	@Column(length = 80, nullable = false)
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(insertable = false, updatable = false)
	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * Get resource's value
	 * 
	 * (1)Menu返回的是类似于 (2)Url返回的是类似于/**或者/pages/user/**等URL字符串
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Get role authorities as string
	 * 
	 * 该方法返回的是类似于ROLE_SUPER或者ROLE_USER,ROLE_ADMIN,ROLE_SUPER等角色(�?��?)组字符串
	 * 
	 * @return
	 */
	@Transient
	public String getRoleAuthorities() {
		List<String> roleAuthorities = new ArrayList<String>();
		for (Role role : roles) {
			roleAuthorities.add(role.getRoleName());
		}
		return StringUtils.join(roleAuthorities, ",");
	}

	protected String description;
	protected String remark;
	protected Date createTime;
	protected String creator;
	protected Date lastUpdateTime;
	protected String lastUpdater;
	protected Integer optimisticLock;

	@Column(length = 255)
	public String getDescription() {
		return this.description;
	}

	@Column(length = 255)
	public String getRemark() {
		return this.remark;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return this.createTime;
	}

	@Column(length = 60)
	public String getCreator() {
		return this.creator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	@Column(length = 60)
	public String getLastUpdater() {
		return this.lastUpdater;
	}

	public Integer getOptimisticLock() {
		return this.optimisticLock;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
	}

	public void setOptimisticLock(Integer optimisticLock) {
		this.optimisticLock = optimisticLock;
	}

	public String toString() {
		StringBuffer toString = new StringBuffer();

		toString.append("\nResource Messages:");

		toString.append("\nid = ");
		toString.append(id);

		toString.append("\nresourceCode = ");
		toString.append(resourceCode);

		toString.append("\nresourcename = ");
		toString.append(resourceName);

		toString.append("\nresourceType = ");
		toString.append(resourceType);

		toString.append("\nvalue = ");
		toString.append(value);

		return new String(toString);
	}
}
