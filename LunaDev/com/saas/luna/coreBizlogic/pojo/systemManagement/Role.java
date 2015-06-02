/*
 * Role.java
 * com.newzhongmei.pmi.coreBusiness.entity.systemManagement
 * 
 * Copyright (c) 2010 Jack Huang Limited. All Rights Reserved.
 */
package com.saas.luna.coreBizlogic.pojo.systemManagement;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.asiasoft.javaee.core.entity.BaseDomainEntity;

/**
 * @author <a href="mailto:704401701@qq.com">Jack Huang</a>
 * 
 * @version V1.00 2010-5-2 上�?�09:33:38
 */
@Entity
@Table(name = "T_role")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends BaseDomainEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8733162089000020731L;

	private String roleCode;
	private String roleName;
	private String isInnerRole;

	private Set<User> users = new HashSet<User>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "roles", targetEntity = User.class)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	private Set<Resource> resources = new HashSet<Resource>();

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = com.Resource.pmi.coreBusiness.entity.systemManagement.Res.class, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "Role_Resource", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "RESOURCE_ID"))
	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	@Column(length = 20, nullable = false)
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(length = 80, nullable = false)
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

	public String toString() {
		StringBuffer toString = new StringBuffer();

		toString.append("\nRole Messages:");

		toString.append("\nid = ");
		toString.append(id);

		toString.append("\nroleCode = ");
		toString.append(roleCode);

		toString.append("\nroleName = ");
		toString.append(roleName);

		toString.append("\ndescription = ");
		toString.append(description);

		return new String(toString);
	}
}
