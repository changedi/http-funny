package com.github.changedi.http.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import net.sf.cglib.proxy.Enhancer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.changedi.http.annotation.HttpService;

public abstract class HttpServiceAware {

	protected final Log logger = LogFactory.getLog(getClass());

	private ProxyHandler proxyHandler;

	public HttpServiceAware() {
		proxyHandler = new ProxyHandler();

		Class<? extends HttpServiceAware> clz = getClass();
		Field[] fields = clz.getDeclaredFields();
		assert (fields != null);
		for (Field field : fields) {
			Annotation httpServiceAnnotation = field
					.getAnnotation(HttpService.class);
			if (httpServiceAnnotation != null) {
				Class<?> type = field.getType();
				Enhancer enhancer = new Enhancer();
				enhancer.setSuperclass(type);
				enhancer.setClassLoader(type.getClassLoader());
				enhancer.setCallback(proxyHandler);
				Object object = enhancer.create();
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
		}
	}

}
