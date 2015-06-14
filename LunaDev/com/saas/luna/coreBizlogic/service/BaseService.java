/**
 * Project Name:SSHProject
 * File Name:BaseService.java
 * Package Name:com.saas.luna.coreBizlogic.sevice
 * Date:2015年6月9日下午5:32:02
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
 */

package com.saas.luna.coreBizlogic.service;

import org.apache.log4j.Logger;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.WebAuthenticationDetails;
import org.springframework.security.userdetails.UserDetails;

/**
 * ClassName:BaseService Date: 2015年6月9日 下午5:32:02
 *
 * @author Jack.Huang
 * @version V1.0
 * @since JDK 1.8.0_45
 */
public class BaseService {

	private static final Logger logger = Logger.getLogger(BaseService.class);

	private String username = "Unknown User";
	private String clientHostAddress = "Unknown Host";

	protected String getUsername() {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
		}

		return username;
	}

	protected String getClientHostAddress() {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object details = SecurityContextHolder.getContext()
					.getAuthentication().getDetails();
			if (details instanceof WebAuthenticationDetails) {
				clientHostAddress = ((WebAuthenticationDetails) details)
						.getRemoteAddress();
			}
		}

		return clientHostAddress;
	}

	protected void logAuditTrail(String actionDesc) {
		StringBuilder logMessage = new StringBuilder();

		logMessage.append(getUsername());
		logMessage.append(" from ");
		logMessage.append(getClientHostAddress());
		logMessage.append(": ");
		logMessage.append(actionDesc);

		logger.info(logMessage.toString());
	}
}
