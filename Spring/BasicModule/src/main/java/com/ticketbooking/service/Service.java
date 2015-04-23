package com.ticketbooking.service;

import java.util.List;

import com.ticketbooking.model.Entity;

public interface Service<E extends Entity> {
	
	default List<E> getPageList(List<E> list, int pageNum, int pageSize) {
		if (pageSize < list.size()) {
			int startIndex = (pageNum - 1) * pageSize;
			int endIndex = pageNum * pageSize - 1;
			endIndex = endIndex > list.size() ? list.size() : endIndex;
			return list.subList(startIndex, endIndex);
		} else {
			return list;
		}
	}

}
