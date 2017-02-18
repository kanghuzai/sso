package org.taniwan.study.sso.common.enums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用在springJdbcDao查询映射赋值
 * @author 张超
 * @date 2016年11月4日-下午4:30:50
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumMapping {

}