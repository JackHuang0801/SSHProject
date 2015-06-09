/*
 * Menu.java
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
 * @version V1.00 2010-5-2 上�?�09:37:27
 */
@Entity
@DiscriminatorValue("menu")
public class Menu extends Resource {

	/**
	 *
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -7385548643559958255L;

	private Menu parentMenu;

	private Set<Menu> subMenus= new HashSet<Menu>();

	@ManyToOne
	@JoinColumn(name="parentMenu_FK")
	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	@OneToMany(
			mappedBy="parentMenu",
			cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	public Set<Menu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(Set<Menu> subMenus) {
		this.subMenus = subMenus;
	}

}