/**
 * 
 */
package com.ticketbooking.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ticketbooking.app.AbstractTest;
import com.ticketbooking.model.Entity;

/**
 * Test for ServiceTest
 *
 */
public class ServiceTest extends AbstractTest {
	
	private Service<Entity> service;

	/* (non-Javadoc)
	 * @see com.ticketbooking.app.AbstractTest#setUp()
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		service = new Service<Entity>(){};
	}
	
	public List<Entity> fillList(int size) {
		List<Entity> list = new ArrayList<Entity>();
		for(int i = 0; i < size; i++) {
			list.add(new Entity(){

				@Override
				public long getId() {
					return 0;
				}

				@Override
				public void setId(long id) {
				}});
		}
		return list;
	}

	/**
	 * Test method for {@link com.ticketbooking.service.Service#getPageList(java.util.List, int, int)}.
	 */
	@Test
	public void testGetPageList() {
		List<Entity> list = fillList(30); 
		assertThat(service.getPageList(list, 1, 5).size(), equalTo(5));
		assertThat(service.getPageList(list, 2, 5).size(), equalTo(5));
		assertThat(service.getPageList(list, 3, 5).size(), equalTo(5));
		assertThat(service.getPageList(list, 4, 5).size(), equalTo(5));
		assertThat(service.getPageList(list, 5, 5).size(), equalTo(5));
		assertThat(service.getPageList(list, 6, 5).size(), equalTo(5));
		
		assertThat(service.getPageList(list, 1, 30).size(), equalTo(30));
		assertThat(service.getPageList(list, 1, 45).size(), equalTo(30));
		assertThat(service.getPageList(list, 1, 28).size(), equalTo(28));
		assertThat(service.getPageList(list, 2, 28).size(), equalTo(2));
	}

}
