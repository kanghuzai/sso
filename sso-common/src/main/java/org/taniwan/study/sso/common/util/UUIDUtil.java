package org.taniwan.study.sso.common.util;

import com.fasterxml.uuid.Generators;

public class UUIDUtil {

	public static String getUuid(){
		return Generators.randomBasedGenerator().generate().toString();
	}
}
