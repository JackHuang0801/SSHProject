/**
 * Project Name:SSHProject
 * File Name:ApplicationException.java
 * Package Name:com.saas.luna.JackFramework.core.exception
 * Date:2015年6月2日下午1:50:06
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
 */

package com.saas.luna.JackFramework.core.exception;

/**
 * ClassName:ApplicationException Date: 2015年6月2日 下午1:50:06
 *
 * @author Jack.Huang
 * @version V1.0
 * @since JDK 1.8.0_45
 */
public class ApplicationException extends RuntimeException {
	private String message;
	private String messageDetails;

	public ApplicationException() {
	}

	public ApplicationException(String message) {
		super(message);

		this.message = message;
	}

	public ApplicationException(String message, String messageDetails) {
		super(message);

		this.message = message;
		this.messageDetails = messageDetails;
	}

	public String getMessage() {
		return this.message;
	}

	public String getMessageDetails() {
		return this.messageDetails;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMessageDetails(String messageDetails) {
		this.messageDetails = messageDetails;
	}
}
