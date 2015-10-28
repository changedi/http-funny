package com.github.changedi.http.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 标注处理工具类.
 * 
 * @author zunyuan.jy
 *
 * @since 2015年10月21日
 */
public class AnnotationHelper {

	private static AnnotationHelper helper;

	private AnnotationHelper() {

	}

	/**
	 * 单例.
	 * 
	 * @return
	 */
	public static AnnotationHelper getInstance() {
		if (helper == null) {
			helper = new AnnotationHelper();
		}
		return helper;
	}

	/**
	 * 提取标注值.
	 * 
	 * @param clz
	 *            被标注的类
	 * @param method
	 *            被标注的方法
	 * @param defaultValue
	 *            默认值
	 * @param annotationClass
	 *            标注类
	 * @param methodName
	 *            标注类的方法名
	 * @return
	 */
	public Object extractAnnotationValue(Class<?> clz, Method method,
			Object defaultValue, Class annotationClass, String methodName) {
		Object annotationValue = defaultValue;
		try {
			Annotation annotation = method.getAnnotation(annotationClass);
			if (annotation != null) {
				annotationValue = extractAnnotationValueInternal(
						annotationClass, annotation, methodName);
			} else {
				annotation = clz.getAnnotation(annotationClass);
				if (annotation != null) {
					annotationValue = extractAnnotationValueInternal(
							annotationClass, annotation, methodName);
				}
			}
		} catch (Exception e) {

		}
		return annotationValue;
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
	private Object extractAnnotationValueInternal(Class<?> annotationClass,
			Annotation annotation, String methodName)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Object o = annotationClass.cast(annotation);
		Method m = o.getClass().getMethod(methodName);
		Object value = m.invoke(o);
		return value;
	}

	/**
	 * 提取方法的注解.
	 * 
	 * @param method
	 *            被标注的方法
	 * @param aobj
	 *            方法的参数
	 * @param cls
	 *            标注类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> extractParameterAnnotation(Method method,
			Object[] aobj, Class<?> cls) {
		Map<String, Object> params = Maps.newHashMap();
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		for (int i = 0; i < parameterAnnotations.length; i++) {
			assert (parameterAnnotations[i].length <= 1) : "You should have at most one annotation on every parameter.";
			if (parameterAnnotations[i].length > 0
					&& parameterAnnotations[i][0].annotationType() == cls) {
				params = (Map<String, Object>) aobj[i];
				break;
			}
		}
		return params;
	}

	public Object extractParameterAnnotationValue(Method method,
			Object defaultValue, Class<?> annotationClass, String methodName) {
		Object value = defaultValue;
		try {
			Annotation[][] parameterAnnotations = method
					.getParameterAnnotations();
			for (int i = 0; i < parameterAnnotations.length; i++) {
				assert (parameterAnnotations[i].length <= 1) : "You should have at most one annotation on every parameter.";
				if (parameterAnnotations[i].length > 0
						&& parameterAnnotations[i][0].annotationType() == annotationClass) {
					value = extractAnnotationValueInternal(annotationClass,
							parameterAnnotations[i][0], methodName);
					break;
				}
			}
		} catch (Exception e) {

		}
		return value;
	}

	/**
	 * 方法或类是否被标注.
	 * 
	 * @param clz
	 *            被标注的类
	 * @param method
	 *            被标注的方法
	 * @param annotationClass
	 *            标注类
	 * @return
	 */
	public boolean hasAnnotation(Class<?> clz, Method method,
			Class annotationClass) {
		return method.getAnnotation(annotationClass) == null ? (clz
				.getAnnotation(annotationClass) == null ? false : true) : true;
	}
}
