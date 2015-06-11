/**
 * Project Name:SSHProject
 * File Name:Page.java
 * Package Name:com.saas.luna.JackFramework.util
 * Date:2015年6月11日下午3:38:59
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.JackFramework.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName:Page
 * Date:     2015年6月11日 下午3:38:59
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */
public class Page<E> implements Serializable {
	private static final long serialVersionUID = 7194890424117323504L;
	private static int DEFAULT_PAGE_SIZE = 10;

	private int pageSize = DEFAULT_PAGE_SIZE;
	private int pageNumber;
	private long totalRecordsCount;
	private List<E> objectsList;
	private long totalPagesCount;

	public static final Page EMPTY_PAGE = new Page(0, DEFAULT_PAGE_SIZE, 0L, Collections.emptyList());

	public Page() {
		this.pageNumber = 0;
		this.pageSize = DEFAULT_PAGE_SIZE;
		this.totalRecordsCount = 0L;
		this.objectsList = new ArrayList();
	}

	public Page(int pageNumber, int pageSize, long totalRecordsCount, List<E> objectsList) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalRecordsCount = totalRecordsCount;
		this.objectsList = new ArrayList(objectsList);
	}

	public String toString() {
		StringBuilder toString = new StringBuilder();

		toString.append("\npageNumber = ");
		toString.append(this.pageNumber);

		toString.append("\npageSize = ");
		toString.append(this.pageSize);

		toString.append("\ntotalRecordsCount = ");
		toString.append(this.totalRecordsCount);

		toString.append("\ntotalPagesCount = ");
		toString.append(getTotalPagesCount());

		toString.append("\nhasNextPage = ");
		toString.append(hasNextPage());

		toString.append("\nhasPreviousPage = ");
		toString.append(hasPreviousPage());

		toString.append("\nstartIndex = ");
		toString.append(getStartIndex(this.pageNumber, this.pageSize));

		toString.append("\nobjectsList = ");
		toString.append((this.objectsList == null) ? "No info." : this.objectsList.toString());

		toString.append("\n");

		return new String(toString);
	}

	public long getTotalPagesCount() {
		if (this.totalRecordsCount % this.pageSize > 0L)
			this.totalPagesCount = (this.totalRecordsCount / this.pageSize + 1L);
		else {
			this.totalPagesCount = (this.totalRecordsCount / this.pageSize);
		}
		return this.totalPagesCount;
	}

	public void setTotalPagesCount(int totalPagesCount) {
		this.totalPagesCount = totalPagesCount;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public long getTotalRecordsCount() {
		return this.totalRecordsCount;
	}

	public void setTotalRecordsCount(long totalRecordsCount) {
		this.totalRecordsCount = totalRecordsCount;
	}

	public List<E> getObjectsList() {
		return this.objectsList;
	}

	public void setObjectsList(List<E> objectsList) {
		this.objectsList = objectsList;
	}

	public boolean hasNextPage() {
		return (getPageNumber() < getTotalPagesCount());
	}

	public boolean hasPreviousPage() {
		return (getPageNumber() > 1);
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public static int getStartIndex(int pageNumber, int pageSize) {
		int startIndex = (pageNumber - 1) * pageSize;
		if (startIndex < 0) {
			startIndex = 0;
		}

		return startIndex;
	}

	public boolean isEmpty() {
		return ((this.objectsList == null) || (this.objectsList.isEmpty()));
	}

	public static Page emptyPage() {
		return new Page();
	}
}

