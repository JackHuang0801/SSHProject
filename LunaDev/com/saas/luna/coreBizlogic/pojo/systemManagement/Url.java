/*
 * Operation.java
 * com.newzhongmei.pmi.coreBusiness.entity.systemManagement
 *
 * Copyright (c) 2010 Jack Huang Limited. All Rights Reserved.
 */
package com.saas.luna.coreBizlogic.pojo.systemManagement;

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
 * @version V1.00 2010-5-2 上�?�09:37:35
 */
@Entity
@DiscriminatorValue("url")
public class Url extends Resource {

	/**
	 *
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -5448037937158973523L;

	private Set<Method> methods = new HashSet<Method>();

	@OneToMany(mappedBy = "url", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public Set<Method> getMethods() {
		return methods;
	}

	public void setMethods(Set<Method> methods) {
		this.methods = methods;
	}

	private Url parentUrl;

	private Set<Url> subUrls = new HashSet<Url>();

	@ManyToOne
	@JoinColumn(name = "parentUrl_FK")
	public Url getParentUrl() {
		return parentUrl;
	}

	public void setParentUrl(Url parentUrl) {
		this.parentUrl = parentUrl;
	}

	@OneToMany(mappedBy = "parentUrl", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public Set<Url> getSubUrls() {
		return subUrls;
	}

	public void setSubUrls(Set<Url> subUrls) {
		this.subUrls = subUrls;
	}

}
