package com.github.changedi.http.core;

import java.lang.reflect.Field;

import net.sf.cglib.proxy.Enhancer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.changedi.http.annotation.HttpService;

/**
 * 所有被标注的Http Service需要extends的抽象类.
 * 
 * @author zunyuan.jy
 *
 * @since 2015年10月23日
 */
public abstract class HttpServiceBase {

	protected final Log logger = LogFactory.getLog(getClass());

	private ProxyHandler proxyHandler;

	public HttpServiceBase() {
		proxyHandler = new ProxyHandler();

		Class<? extends HttpServiceBase> clz = getClass();
		Field[] fields = clz.getDeclaredFields();
		assert (fields != null);
		for (Field field : fields) {
			if (field.getAnnotation(HttpService.class) != null) {
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
					logger.error(
							"HttpServiceAware initialization Error with IllegalArgumentException.",
							e);
				} catch (IllegalAccessException e) {
					logger.error(
							"HttpServiceAware initialization Error with IllegalAccessException",
							e);
				}
			}
		}
	}

}
