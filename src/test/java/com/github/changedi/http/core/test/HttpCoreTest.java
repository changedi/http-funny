package com.github.changedi.http.core.test;

import junit.framework.TestCase;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.github.changedi.http.core.HttpCore;
import com.github.changedi.http.core.HttpParam;

public class HttpCoreTest extends TestCase {

	@Test
	public void testGet() {
		HttpCore hc = HttpCore.getInstance();
		String result = "";
		try {
			HttpParam param = new HttpParam().setURI(
					new URIBuilder().setScheme("http")
							.setHost("apis.baidu.com")
							.setPath("/apistore/weatherservice/citylist")
							.setParameter("cityname", "杭州").build())
					.setRequestConfig(
							RequestConfig
									.copy(HttpParam.DEFAULT_REQUEST_CONFIG)
									.setSocketTimeout(5000)
									.setConnectTimeout(5000)
									.setConnectionRequestTimeout(5000).build())
					.addHeader("apikey", "31da01966ee96d06fdd5c2f2c855424e");
			result = hc.get(param);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(result);
	}
}
