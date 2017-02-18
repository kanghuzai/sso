package org.taniwan.study.sso.common.mvc.jsop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * jsonp注解
 * 
 * @author 张超
 * @date 2016年8月29日-上午9:27:46
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonpSign {

}
