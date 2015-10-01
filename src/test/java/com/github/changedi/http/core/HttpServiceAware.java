package com.github.changedi.http.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public abstract class HttpServiceAware {

	private ProxyHandler proxyHandler;

	public HttpServiceAware() {
		proxyHandler = new ProxyHandler();

		Class<? extends HttpServiceAware> clz = getClass();
		Field[] fields = clz.getDeclaredFields();
		assert (fields != null);
		for (Field field : fields) {
			System.out.println(field);
			Annotation httpServiceAnnotation = field
					.getAnnotation(HttpService.class);
			if (httpServiceAnnotation != null) {
				Class<?> type = field.getType();
				System.out.println(type);
				Object object = Proxy.newProxyInstance(type.getClassLoader(),
						new Class<?>[] { type }, proxyHandler);
				field.setAccessible(true);
				try {
					field.set(this, object);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(httpServiceAnnotation);
		}
	}

}
