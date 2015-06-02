/**
 * Project Name:SSHProject
 * File Name:AbstractValueObject.java
 * Package Name:com.saas.luna.JackFramework.core
 * Date:2015年6月2日下午1:43:10
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
 */

package com.saas.luna.JackFramework.core;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.saas.luna.JackFramework.core.exception.ApplicationException;

/**
 * ClassName:AbstractValueObject Date: 2015年6月2日 下午1:43:10
 *
 * @author Jack.Huang
 * @version V1.0
 * @since JDK 1.8.0_45
 */
@SuppressWarnings("serial")
public abstract class AbstractValueObject implements IValueObject {

	public String toString() {
		try {
			StringBuffer sb = new StringBuffer();
			PropertyDescriptor[] propDescs = PropertyUtils
					.getPropertyDescriptors(this);
			int i = 0;
			for (int n = propDescs.length; i < n; ++i) {
				String name = propDescs[i].getName();
				if (!(PropertyUtils.isReadable(this, name))) {
					continue;
				}

				Object propValue = PropertyUtils.getProperty(this, name);

				if ((propValue instanceof Collection)
						|| (propValue instanceof Map)) {
					sb.append(name).append("=[more...]");
				} else if (propValue instanceof IValueObject) {
					String className = propValue.getClass().getName();
					sb.append(name).append("=[").append(className).append("]");
				} else {
					sb.append(name).append("=[").append(propValue).append("]");
				}
			}

			return sb.toString();
		} catch (Exception ex) {
			throw new ApplicationException("exception.systemException",
					ex.getMessage());
		}
	}
}
