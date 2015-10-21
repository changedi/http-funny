package com.github.changedi.http.core.test;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.github.changedi.http.core.demo.DemoService;

public class DemoServiceTest {

	@Test
	public void test() {
		DemoService demoService = new DemoService();
		Map<String, Object> result = demoService.get();
		System.out.println(result);
		Assert.assertNotNull(result);
	}

	@Test
	public void testPost() {
		DemoService demoService = new DemoService();
		Map<String, Object> result = demoService.post();
		System.out.println(result);
		Assert.assertNotNull(result);
	}
}
