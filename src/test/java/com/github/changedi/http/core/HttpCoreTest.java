package com.github.changedi.http.core;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class HttpCoreTest extends TestCase {

	@Test
	public void testGet() {
		HttpCore hc = new HttpCore();
		hc.init();
		String result = "";
		try {
			result = hc.get("http://www.aliyun.com/");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(result);
	}
}
