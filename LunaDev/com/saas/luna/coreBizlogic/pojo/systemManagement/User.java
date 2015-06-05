/*
 * User.java
 * com.newzhongmei.pmi.coreBusiness.entity.systemManagement
 *
 * Copyright (c) 2010 Jack Huang Limited. All Rights Reserved.
 */
package com.saas.luna.coreBizlogic.pojo.systemManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;




import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Proxy;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import com.saas.luna.JackFramework.core.pojo.BaseDomainPojo;


/**
 * @author <a href="mailto:704401701@qq.com">Jack Huang</a>
 *
 * @version V1.00 2010-5-2 上午09:36:37
 */
@Entity
@Table(name = "T_USER")
@Proxy(lazy = false)
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseDomainPojo<Long> implements UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = 2546742654373798923L;

	private String userCode;
	private String userName;
	private String password;
	private String trueName;
	private String officePhone;
	private String mobileTelephone;
	private String email;
	private String state;
	private boolean disabled = false;

	private Department department;

	@ManyToOne
	@JoinColumn(name = "departmentId")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	private Set<Role> roles;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Transient
	private Map<String, List<Resource>> roleResources;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	@Transient
	public GrantedAuthority[] getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles.size());
		for (Role role : roles) {
			grantedAuthorities.add(new GrantedAuthorityImpl(role.getRoleName()));
		}
		return grantedAuthorities.toArray(new GrantedAuthority[roles.size()]);
	}

	/**
	 * Returns the authorites string
	 *
	 * 访问当前登录用户�?拥有的权�?,类似于ROLE_SUPER或�?�ROLE_USER,ROLE_ADMIN,ROLE_SUPER等角色(权限)组字符串
	 *
	 * eg.
	 *     downpour --- ROLE_ADMIN,ROLE_USER
	 *     robbin --- ROLE_ADMIN
	 *
	 * @return
	 */
	@Transient
	public String getAuthoritiesString() {
		List<String> authorities = new ArrayList<String>();
		for (GrantedAuthority authority : this.getAuthorities()) {
			authorities.add(authority.getAuthority());
		}
		return StringUtils.join(authorities, ",");
	}

	/**
	 * Get currently user has resources.
	 *
	 * 访问当前登录用户能够访问的资源
	 *
	 * @return the roleResources
	 */
	@Transient
	public Map<String, List<Resource>> getRoleResources() {
		// init roleResources for the first time
		if (this.roleResources == null) {
			this.roleResources = new HashMap<String, List<Resource>>();

			for (Role role : this.roles) {
				String roleName = role.getRoleName();
				Set<Resource> resources = role.getResources();
				for (Resource resource : resources) {
					String key = roleName + "_" + resource.getResourceType();
					if (!this.roleResources.containsKey(key)) {
						this.roleResources.put(key, new ArrayList<Resource>());
					}
					this.roleResources.get(key).add(resource);
				}
			}

		}
		return this.roleResources;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.userdetails.UserDetails#getUsername()
	 */
	@Override
	@Transient
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.userdetails.UserDetails#isAccountNonExpired
	 * ()
	 */
	@Override
	@Transient
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	@Transient
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired
	 * ()
	 */
	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.userdetails.UserDetails#isEnabled()
	 */
	@Override
	@Transient
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return !this.disabled;
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

	// public String getPassword() {
	// return password;
	// }

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

	@Column(nullable = false, columnDefinition = "bit default 0 ")
	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String toString() {
		StringBuffer toString = new StringBuffer();

		toString.append("\nUser Messages:");

		toString.append("\nid = ");
		toString.append(id);

		toString.append("\nuserName = ");
		toString.append(userName);

		toString.append("\npassword = ");
		toString.append(password);

		toString.append("\ntrueName = ");
		toString.append(trueName);

		toString.append("\nofficePhone = ");
		toString.append(officePhone);

		toString.append("\nmobilePhone = ");
		toString.append(mobileTelephone);

		toString.append("\nemail = ");
		toString.append(email);
		return new String(toString);
	}

}
