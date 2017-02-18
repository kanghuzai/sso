package org.taniwan.study.sso.common.mvc.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 非认证标记
 * 
 * @author 张超
 * @date 2015年5月11日 上午10:33:27
 * @since 2.1
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoAuthSign {
}
