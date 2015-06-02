/*
 * Department.java
 * com.newzhongmei.pmi.coreBusiness.entity.systemManagement
 * 
 * Copyright (c) 2010 Jack Huang Limited. All Rights Reserved.
 */
package com.saas.luna.coreBizlogic.pojo.systemManagement;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.asiasoft.javaee.core.entity.BaseDomainEntity;

/**
 * @author <a href="mailto:704401701@qq.com">Jack Huang</a>
 * 
 * @version V1.00 2010-5-2 下午10:08:45
 */
@Entity
@Table(name = "T_department")
public class Department extends BaseDomainEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9185200321530821289L;

	private String departmentCode;
	private String departmentName;
	private String phone;
	private String fax;
	private String location;

	private Set<User> users = new HashSet<User>();

	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	private Department parentDepartment;

	/**
	 * Department实体与自身是一对多的树形结构, getParentDepartment()获取父节点.
	 * @return Department
	 */
	@ManyToOne
	@JoinColumn(name="parentDepartment_FK")
	public Department getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(Department parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	private Set<Department> subDepartments = new HashSet<Department>();

	/**
	 * getSubDepartments()获取子节点的集合.
	 * @return Set<Department>
	 */
	@OneToMany(
			mappedBy="parentDepartment",
			cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	public Set<Department> getSubDepartments() {
		return subDepartments;
	}

	public void setSubDepartments(Set<Department> subDepartments) {
		this.subDepartments = subDepartments;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String toString() {
		StringBuffer toString = new StringBuffer();

		toString.append("\nDepartment Messages:");

		toString.append("\nid = ");
		toString.append(id);

		toString.append("\ndepartmentCode = ");
		toString.append(departmentCode);

		toString.append("\ndepartmentName = ");
		toString.append(departmentName);

		toString.append("\nphonet = ");
		toString.append(phone);
		
		toString.append("\nfax");
		toString.append(fax);
		
		toString.append("\nlocation");
		toString.append(location);

		return new String(toString);
	}

}
