package com.github.changedi.http.core;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class DemoServiceTest {

	@Test
	public void test() {
		DemoService demoService = new DemoService();
		Map<String, Object> result = demoService.get();
		System.out.println(result);
		Assert.assertNotNull(result);
	}

}
