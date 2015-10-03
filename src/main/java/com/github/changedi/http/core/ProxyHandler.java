package com.github.changedi.http.core;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.InvocationHandler;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.utils.URIBuilder;

public class ProxyHandler implements InvocationHandler {

	private AnnotationHelper helper = new AnnotationHelper();

	@Override
	public Object invoke(Object obj, Method method, Object[] aobj)
			throws Throwable {
		URIBuilder builder = new URIBuilder().setScheme("http").setParameter(
				"cityname", "杭州");

		Class<?> clz = obj.getClass();
		// process scheme
		// add schema
		processScheme(clz, method, builder, "http");
		// add host
		processHost(clz, method, builder, "");

		// host should not be null

		// process query parameters

		// process path parameters

		// process header parameters

		HttpParam param = new HttpParam()
				.setURI(builder.build())
				.setRequestConfig(
						RequestConfig.copy(HttpParam.DEFAULT_REQUEST_CONFIG)
								.setSocketTimeout(5000).setConnectTimeout(5000)
								.setConnectionRequestTimeout(5000).build())
				.addHeader("apikey", "31da01966ee96d06fdd5c2f2c855424e");
		System.out.println(param);
		System.out.println("proxy invoke");
		return null;
	}

	private void processHost(Class<?> clz, Method method, URIBuilder builder,
			String defaultValue) throws HttpConfigException {
		String hostStr = helper.extractAnnotationValue(clz, method,
				defaultValue, Host.class, "value");
		if (StringUtils.isEmpty(hostStr)) {
			throw new HttpConfigException();
		} else {
			builder.setHost(hostStr);
		}
	}

	private void processScheme(Class<?> clz, Method method, URIBuilder builder,
			String defaultValue) {
		String schemeStr = helper.extractAnnotationValue(clz, method,
				defaultValue, Scheme.class, "value");
		builder.setScheme(schemeStr);
	}

}
