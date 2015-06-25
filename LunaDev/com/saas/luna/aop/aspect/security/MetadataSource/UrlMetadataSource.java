/**
 * Project Name:SSHProject
 * File Name:UrlMetadataSource.java
 * Package Name:com.saas.luna.aop.aspect.security.MetadataSource
 * Date:2015年6月25日上午10:12:30
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.aop.aspect.security.MetadataSource;

import java.util.Collection;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * ClassName:UrlMetadataSource
 * Date:     2015年6月25日 上午10:12:30
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */
public class UrlMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {

	static {
		System.out.println("\n----------Self Definition Intercepter is Coming which is for Url---------------");
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		/*
		 * 如果返回false会出现下面的错误
		 * Caused by: java.lang.IllegalArgumentException:
		 * 			SecurityMetadataSource does not support secure object class: class org.springframework.security.web.FilterInvocation
		 */

		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

}

