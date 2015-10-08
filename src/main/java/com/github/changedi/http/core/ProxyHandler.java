package com.github.changedi.http.core;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import net.sf.cglib.proxy.InvocationHandler;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicHeader;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ProxyHandler implements InvocationHandler {

	private AnnotationHelper helper = AnnotationHelper.getInstance();

	private HttpCore httpCore = HttpCore.getInstance();

	private Gson gson = new GsonBuilder().create();

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

		Map<String, Object> querys = Maps.newHashMap();
		Map<String, Object> paths = Maps.newHashMap();
		Map<String, Object> headersMap = Maps.newHashMap();
		// process query parameters
		processQuery(method, aobj, querys);

		// process path parameters
		processPath(method, aobj, paths);

		// process header parameters
		processHeader(clz, method, aobj, headersMap);

		// process callback
		processCallback(method, aobj, querys, paths, headersMap);

		// form builder and header
		form(clz, method, querys, paths, headersMap, builder, headers);

		HttpParam param = new HttpParam()
				.setURI(builder.build())
				.setRequestConfig(
						RequestConfig.copy(HttpParam.DEFAULT_REQUEST_CONFIG)
								.setSocketTimeout(5000).setConnectTimeout(5000)
								.setConnectionRequestTimeout(5000).build())
				.setHeaders(headers);
		System.out.println(param);
		System.out.println("proxy invoke");
		String response = httpCore.get(param);
		SerializeEnum serialization = processSerialization(clz, method);
		Type returnType = method.getReturnType();
		switch (serialization) {
		case STRING:
			return response;
		case JSON:
			return gson.fromJson(response, returnType);
		case XML:
			throw new UnsupportedOperationException();
		}
		return null;
	}

	private SerializeEnum processSerialization(Class<?> clz, Method method) {
		String serialization = helper.extractAnnotationValue(clz, method, "string",
				Serialization.class, "value");
		if ("json".equalsIgnoreCase(serialization))
			return SerializeEnum.JSON;
		else if ("xml".equalsIgnoreCase(serialization))
			return SerializeEnum.XML;
		return SerializeEnum.STRING;
	}

	private void form(Class<?> clz, Method method, Map<String, Object> querys,
			Map<String, Object> paths, Map<String, Object> headersMap,
			URIBuilder builder, List<Header> headers) {
		for (String key : headersMap.keySet()) {
			headers.add(new BasicHeader(key, headersMap.get(key).toString()));
		}
		for (String key : querys.keySet()) {
			builder.setParameter(key, querys.get(key).toString());
		}
		String pathStr = helper.extractAnnotationValue(clz, method, "",
				Path.class, "value");
		if (StringUtils.isNotEmpty(pathStr)) {
			for (String key : paths.keySet()) {
				pathStr = pathStr.replace("{" + key + "}", paths.get(key)
						.toString());
			}
			builder.setPath(pathStr);
		}
	}

	private void processCallback(Method method, Object[] aobj,
			Map<String, Object> querys, Map<String, Object> paths,
			Map<String, Object> headersMap) {
		for (Object o : aobj) {
			if (o instanceof Callback) {
				((Callback) o).execute(querys, paths, headersMap);
				break;
			}
		}
	}

	private void processHeader(Class<?> clz, Method method, Object[] aobj,
			Map<String, Object> headersMap) {
		Map<String, Object> headers = helper.extractParameterAnnotation(method,
				aobj, HeaderParam.class);
		for (String key : headers.keySet()) {
			headersMap.put(key, headers.get(key).toString());
		}
	}

	private void processPath(Method method, Object[] aobj,
			Map<String, Object> paths) {
		Map<String, Object> paths2 = helper.extractParameterAnnotation(method,
				aobj, PathParam.class);
		for (String key : paths2.keySet()) {
			paths.put(key, paths2.get(key).toString());
		}
	}

	private void processQuery(Method method, Object[] aobj,
			Map<String, Object> querys) {
		Map<String, Object> queries = helper.extractParameterAnnotation(method,
				aobj, QueryParam.class);
		for (String key : queries.keySet()) {
			querys.put(key, queries.get(key).toString());
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
