package com.github.changedi.http.core;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import net.sf.cglib.proxy.InvocationHandler;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicHeader;

import com.google.common.collect.Lists;

public class ProxyHandler implements InvocationHandler {

	private AnnotationHelper helper = AnnotationHelper.getInstance();

	private HttpCore httpCore = HttpCore.getInstance();

	@Override
	public Object invoke(Object obj, Method method, Object[] aobj)
			throws Throwable {
		URIBuilder builder = new URIBuilder();
		List<Header> headers = Lists.newArrayList();

		Class<?> clz = obj.getClass();
		// process scheme
		// add schema
		processScheme(clz, method, builder, "http");
		// add host
		processHost(clz, method, builder, "");

		// process query parameters
		processQuery(method, aobj, builder);

		// process path parameters
		processPath(clz, method, aobj, builder);

		// process header parameters
		processHeader(clz, method, aobj, headers);

		HttpParam param = new HttpParam()
				.setURI(builder.build())
				.setRequestConfig(
						RequestConfig.copy(HttpParam.DEFAULT_REQUEST_CONFIG)
								.setSocketTimeout(5000).setConnectTimeout(5000)
								.setConnectionRequestTimeout(5000).build())
				.setHeaders(headers);
		System.out.println(param);
		System.out.println("proxy invoke");
		return null;
	}

	private void processHeader(Class<?> clz, Method method, Object[] aobj,
			List<Header> list) {
		Map<String, Object> headers = helper.extractParameterAnnotation(method,
				aobj, HeaderParam.class);
		for (String key : headers.keySet()) {
			list.add(new BasicHeader(key, headers.get(key).toString()));
		}
	}

	private void processPath(Class<?> clz, Method method, Object[] aobj,
			URIBuilder builder) throws HttpConfigException {
		String pathStr = helper.extractAnnotationValue(clz, method, "",
				Path.class, "value");
		if (StringUtils.isNotEmpty(pathStr)) {
			Map<String, Object> paths = helper.extractParameterAnnotation(
					method, aobj, PathParam.class);
			for (String key : paths.keySet()) {
				pathStr = pathStr.replace("{" + key + "}", paths.get(key)
						.toString());
			}
			builder.setPath(pathStr);
		}
	}

	private void processQuery(Method method, Object[] aobj, URIBuilder builder) {
		Map<String, Object> querys = helper.extractParameterAnnotation(method,
				aobj, QueryParam.class);
		for (String key : querys.keySet()) {
			builder.setParameter(key, querys.get(key).toString());
		}
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
