/**
 * Project Name:SSHProject
 * File Name:BaseDomainPojo.java
 * Package Name:com.saas.luna.JackFramework.core.pojo
 * Date:2015年6月2日下午1:55:03
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
 */

package com.saas.luna.JackFramework.core.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.asiasoft.javaee.core.AbstractValueObject;

/**
 * ClassName:BaseDomainPojo Date: 2015年6月2日 下午1:55:03
 *
 * @author Jack.Huang
 * @version V1.0
 * @since JDK 1.8.0_45
 */

@MappedSuperclass
public class BaseDomainPojo<ID extends Serializable> extends
		AbstractValueObject {

	private static final long serialVersionUID = 1148750099142971324L;
	protected ID id;
	protected String description;
	protected String remark;
	protected Date createTime;
	protected String creator;
	protected Date lastUpdateTime;
	protected String lastUpdater;
	protected Integer optimisticLock;

	@Id
	@GeneratedValue(generator = "system-increment")
	@GenericGenerator(name = "system-increment", strategy = "increment")
	@Column(nullable = false)
	public ID getId() {
		return this.id;
	}

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

	public void setId(ID id) {
		this.id = id;
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
}
