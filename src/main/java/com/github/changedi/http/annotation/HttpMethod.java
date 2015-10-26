package com.github.changedi.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.changedi.http.annotation.consts.HttpMethodEnum;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpMethod {
	HttpMethodEnum value() default HttpMethodEnum.GET;
}
