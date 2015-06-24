/*
 * SecureResourceFilterInvocationDefinitionSource.java
 * com.newzhongmei.pmi.aop.aspect.security.interceptor
 *
 * Copyright (c) 2010 Jack Huang Limited. All Rights Reserved.
 */
package com.saas.luna.aop.aspect.security.DefinitionSource;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
//import org.springframework.security.ConfigAttributeDefinition;
//import org.springframework.security.ConfigAttributeEditor;
//import org.springframework.security.intercept.web.FilterInvocation;
//import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
//import org.springframework.security.util.AntUrlPathMatcher;
//import org.springframework.security.util.RegexUrlPathMatcher;
//import org.springframework.security.util.UrlMatcher;

/**
 * 从数据库获取被访问的资源所需要的权限标识符,即角色名。 这里只处理Url（访问地址）资源。
 *
 * @author <a href="mailto:704401701@qq.com">Jack Huang</a>
 *
 * @version V1.00 2010-5-9 下午05:03:45
 */
public class UrlDefinitionSource implements FilterInvocationDefinitionSource, InitializingBean {

	static {
		System.out.println("\n----------Self Definition Intercepter is Coming which is for Url---------------");
	}

	private UrlMatcher urlMatcher;

	private boolean useAntPath = true;

	private boolean lowercaseComparisons = true;

	/**
	 * @param useAntPath
	 *            the useAntPath to set
	 */
	public void setUseAntPath(boolean useAntPath) {
		this.useAntPath = useAntPath;
	}

	/**
	 * @param lowercaseComparisons
	 */
	public void setLowercaseComparisons(boolean lowercaseComparisons) {
		this.lowercaseComparisons = lowercaseComparisons;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {

		// default url matcher will be RegexUrlPathMatcher
		this.urlMatcher = new RegexUrlPathMatcher();

		if (useAntPath) { // change the implementation if required
			this.urlMatcher = new AntUrlPathMatcher();
		}

		// Only change from the defaults if the attribute has been set
		if ("true".equals(lowercaseComparisons)) {
			if (!this.useAntPath) {
				((RegexUrlPathMatcher) this.urlMatcher).setRequiresLowerCaseUrl(true);
			}
		} else if ("false".equals(lowercaseComparisons)) {
			if (this.useAntPath) {
				((AntUrlPathMatcher) this.urlMatcher).setRequiresLowerCaseUrl(false);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.intercept.ObjectDefinitionSource#getAttributes
	 * (java.lang.Object)
	 */
	public ConfigAttributeDefinition getAttributes(Object filter) throws IllegalArgumentException {
		FilterInvocation filterInvocation = (FilterInvocation) filter;
		String requestURL = filterInvocation.getRequestUrl();

//		System.out.println("\nExecute UrlFilterInvocationDefinitionSource.getAttributes(filter)");
//		System.out.println("请求的URL：" + requestURL);

		Map<String, String> urlAuthorities = this.getUrlAuthorities(filterInvocation);

		String grantedAuthorities = null;
		StringBuilder urls = new StringBuilder();
		for (Iterator<Map.Entry<String, String>> iter = urlAuthorities.entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, String> entry = iter.next();
			String url = entry.getKey();

			if (urlMatcher.pathMatchesUrl(url, requestURL)) {
				grantedAuthorities = entry.getValue();
				urls.append("请求的URL：" + requestURL + "是受限制访问的Url资源！");
				break;
			}
		}

		/**
		 * grantedAuthorities存放了当前请求的URL资源所需要的权限标识符(即角色字符串)
		 * (1)当grantedAuthorities是"",应该理解为该URL资源还没有分配给任何一个角色,所有用户都不能访问该URL资源,
		 *    通过设置grantedAuthorities = "ROLE_NONE",因此角色表中不能有角色名称是ROLE_NONE的角色,所以系统的所有
		 *    用户都不可能拥有ROLE_NONE角色,也就不可能访问该受限制的方法。
		 *
		 * (2)当grantedAuthorities不是"",该URL资源已经分配给相应的角色,只有具有相应角色权限的用户才能访问该URL资源
		 *
		 * @return (ConfigAttributeDefinition) configAttrEditor.getValue()
		 *         组装成[ROLE_USER,ROLE_ADMIN,ROLE_SUPER]类似的字符串
		 */
		if (grantedAuthorities != null) {
			ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
			if (("").equals(grantedAuthorities)) {
				grantedAuthorities = "ROLE_NONE";
				configAttrEditor.setAsText(grantedAuthorities);
			} else {
				configAttrEditor.setAsText(grantedAuthorities);
			}
			urls.append("\n访问的URL：" + requestURL + "必须具有以下角色权限之一：" + configAttrEditor.getValue());
//			System.out.println(urls);
			return (ConfigAttributeDefinition) configAttrEditor.getValue();
		}
//		System.out.println(urls);
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @seeorg.springframework.security.intercept.ObjectDefinitionSource#
	 * getConfigAttributeDefinitions()
	 */
	@SuppressWarnings("unchecked")
	public Collection getConfigAttributeDefinitions() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.intercept.ObjectDefinitionSource#supports
	 * (java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return true;
	}

	/**
	 *
	 * @param filterInvocation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> getUrlAuthorities(FilterInvocation filterInvocation) {
		ServletContext servletContext = filterInvocation.getHttpRequest().getSession().getServletContext();
		return (Map<String, String>) servletContext.getAttribute("urlAuthorities");
	}

}
