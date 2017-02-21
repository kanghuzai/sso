package org.taniwan.study.sso.common.mvc.jsop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@ControllerAdvice(annotations = JsonpSign.class)
public class JsonpAdviceHandler extends AbstractJsonpResponseBodyAdvice {

	public JsonpAdviceHandler(){
		super("callback","jsonp","jsonpcallback");
	}
}
