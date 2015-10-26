package com.github.changedi.http.core.test;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.github.changedi.http.core.demo.DemoService;

public class DemoServiceTest {

	DemoService demoService = new DemoService();
	
	@Test
	public void test() {		
		Map<String, Object> result = demoService.get();
		System.out.println(result);
		Assert.assertNotNull(result);
	}

	@Test
	public void testPost() {
		Map<String, Object> result = demoService.post();
		System.out.println(result);
		Assert.assertNotNull(result);
	}

	@Test
	public void testPostJson() {
		Map<String, Object> result = demoService.postJson();
		System.out.println(result);
		Assert.assertNotNull(result);
	}
}
