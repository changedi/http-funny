package com.github.changedi.http.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.google.common.collect.Maps;

public class AnnotationHelper {

	private static AnnotationHelper helper;

	private AnnotationHelper() {

	}

	public static AnnotationHelper getInstance() {
		if (helper == null) {
			helper = new AnnotationHelper();
		}
		return helper;
	}

	/**
	 * 
	 * @param clz
	 * @param method
	 * @param defaultValue
	 * @param annotationClass
	 * @param methodName
	 * @return
	 */
	public String extractAnnotationValue(Class<?> clz, Method method,
			String defaultValue, Class annotationClass, String methodName) {
		String hostStr = defaultValue;
		try {
			Annotation annotation = method.getAnnotation(annotationClass);
			if (annotation != null) {
				hostStr = extractAnnotationValueInternal(annotationClass,
						annotation, methodName);
			} else {
				annotation = clz.getAnnotation(annotationClass);
				if (annotation != null) {
					hostStr = extractAnnotationValueInternal(annotationClass,
							annotation, methodName);
				}
			}
		} catch (Exception e) {

		}
		return hostStr;
	}

	/**
	 * 
	 * @param annotationClass
	 * @param annotation
	 * @param methodName
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private String extractAnnotationValueInternal(Class annotationClass,
			Annotation annotation, String methodName)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		String hostStr;
		Object o = annotationClass.cast(annotation);
		Method m = o.getClass().getMethod(methodName);
		hostStr = m.invoke(o).toString();
		return hostStr;
	}

	/**
	 * 
	 * @param method
	 * @param aobj
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> extractParameterAnnotation(Method method,
			Object[] aobj, Class<?> cls) {
		Map<String, Object> paths = Maps.newHashMap();
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		for (int i = 0; i < parameterAnnotations.length; i++) {
			assert (parameterAnnotations[i].length == 1) : "You should have only one annotation on every parameter.";
			if (parameterAnnotations[i][0].annotationType() == cls) {
				paths = (Map<String, Object>) aobj[i];
			}
		}
		return paths;
	}
}
