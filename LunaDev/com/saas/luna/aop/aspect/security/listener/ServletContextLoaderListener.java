/**
 * Project Name:SSHProject
 * File Name:ServletContextLoaderListener.java
 * Package Name:com.saas.luna.aop.aspect.security.listener
 * Date:2015年6月9日下午5:26:02
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.aop.aspect.security.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.saas.luna.coreBizlogic.sevice.systemMaster.UserService;

/**
 * ClassName:ServletContextLoaderListener
 * Date:     2015年6月9日 下午5:26:02
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */
public class ServletContextLoaderListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		UserService userService = this.getUserService(servletContext);

		Map<String, String> urlAuthorities = userService.loadUrlAuthorities();
		servletContext.setAttribute("urlAuthorities", urlAuthorities);

//		Map<String, String> methodAuthorities = userService.loadMethodAuthorities();
//		servletContext.setAttribute("methodAuthorities", methodAuthorities);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		servletContextEvent.getServletContext().removeAttribute("urlAuthorities");
//		servletContextEvent.getServletContext().removeAttribute("methodAuthorities");
	}

	/**
	 * Get UserService from ApplicationContext
	 *
	 * @param servletContext
	 * @return
	 */
	protected UserService getUserService(ServletContext servletContext) {
		return (UserService) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean(
				"userService");
	}
}

