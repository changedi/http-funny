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

		Map<String, Object> queries = Maps.newHashMap();
		Map<String, Object> paths = Maps.newHashMap();
		Map<String, Object> headersMap = Maps.newHashMap();
		// process query parameters
		processParamAnnotation(clz, method, aobj, queries, QueryParam.class);

		// process path parameters
		processParamAnnotation(clz, method, aobj, paths, PathParam.class);

		// process header parameters
		processParamAnnotation(clz, method, aobj, headersMap, HeaderParam.class);

		// process callback
		processCallback(method, aobj, queries, paths, headersMap);

		// form builder and header
		form(clz, method, queries, paths, headersMap, builder, headers);

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
		SerializationEnum serialization = processSerialization(clz, method);
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

	private SerializationEnum processSerialization(Class<?> clz, Method method) {
		String serialization = helper.extractAnnotationValue(clz, method,
				"string", Serialization.class, "value");
		if ("json".equalsIgnoreCase(serialization))
			return SerializationEnum.JSON;
		else if ("xml".equalsIgnoreCase(serialization))
			return SerializationEnum.XML;
		return SerializationEnum.STRING;
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

	private void processParamAnnotation(Class<?> clz, Method method,
			Object[] aobj, Map<String, Object> paramMap,
			Class paramAnnotationClass) {
		Map<String, Object> params = helper.extractParameterAnnotation(method,
				aobj, paramAnnotationClass);
		for (String key : params.keySet()) {
			paramMap.put(key, params.get(key).toString());
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
