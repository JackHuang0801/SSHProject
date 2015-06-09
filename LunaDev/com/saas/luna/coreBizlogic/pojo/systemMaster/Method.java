/*
 * Mehtod.java
 * com.newzhongmei.pmi.coreBusiness.entity.systemManagement
 *
 * Copyright (c) 2010 Jack Huang Limited. All Rights Reserved.
 */
package com.saas.luna.coreBizlogic.pojo.systemMaster;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author <a href="mailto:704401701@qq.com">Jack Huang</a>
 *
 * @version V1.00 2010-5-19 下�?�05:00:24
 */
@Entity
@DiscriminatorValue("method")
public class Method extends Resource {

	/**
	 *
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -2741053843178003448L;

	private Url url;

	@ManyToOne
	@JoinColumn(name = "url_FK")
	public Url getUrl() {
		return url;
	}

	public void setUrl(Url url) {
		this.url = url;
	}

	private Method parentMethod;

	private Set<Method> subMethods = new HashSet<Method>();

	@ManyToOne
	@JoinColumn(name = "parentMethod_FK")
	public Method getParentMethod() {
		return parentMethod;
	}

	public void setParentMethod(Method parentMethod) {
		this.parentMethod = parentMethod;
	}

	@OneToMany(mappedBy = "parentMethod", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public Set<Method> getSubMethods() {
		return subMethods;
	}

	public void setSubMethods(Set<Method> subMethods) {
		this.subMethods = subMethods;
	}

}
