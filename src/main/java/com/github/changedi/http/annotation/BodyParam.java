package com.github.changedi.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.changedi.http.annotation.consts.PostContentEnum;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface BodyParam {
	PostContentEnum value() default PostContentEnum.FORM;
}
