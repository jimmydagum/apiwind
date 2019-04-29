package com.weather.apiwind.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.weather.apiwind.model.Wwind;

// note: please see unconventional test for cache in WindInMemoryCacheTest.java
//       please refer to console cache.size() printing for manual test tracing
public class WindServiceTest {

	@Mock
	private WindService service;

	@InjectMocks
	private WindImp windImp;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetWindNotNull() {
		when(service.getWind(89144L)).thenReturn(new Wwind());
		assertThat(service.getWind(89144L), is(notNullValue()));
	}

	// Throwing an exception from the mocked method
	@Test(expected = RuntimeException.class)
	public void testGetWindThrowsException() {
		when(service.getWind(89144L)).thenThrow(RuntimeException.class);
		service.getWind(89144L);
	}
}
