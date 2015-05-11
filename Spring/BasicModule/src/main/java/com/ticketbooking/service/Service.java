package com.ticketbooking.service;

import java.util.List;

import com.ticketbooking.model.Entity;

public interface Service<E extends Entity> {
	
	/**
	 * Returns the page-related sublist of entities
	 * 
	 * @param list - the current list
	 * @param pageNum - the page number
	 * @param pageSize - quantities of entities per page
	 * @return - the sublist of entities
	 */
	default List<E> getPageList(List<E> list, int pageNum, int pageSize) {
		if (pageSize < list.size()) {
			int startIndex = (pageNum - 1) * pageSize;
			int endIndex = pageNum * pageSize;
			endIndex = endIndex > list.size() ? list.size() : endIndex;
			return list.subList(startIndex, endIndex);
		} else {
			return list;
		}
	}

}
