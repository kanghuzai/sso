package org.taniwan.study.sso.common.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.taniwan.study.sso.common.mvc.auth.AuthSign;
import org.taniwan.study.sso.common.mvc.bean.ResBody;
import org.taniwan.study.sso.common.mvc.jsop.JsonpSign;

@RestController
public class SsoController {

	//业务站点通过jsonp技术结合sso-web-user授权的token登入
	@RequestMapping(value = "/sso/login", method = RequestMethod.POST)
	@JsonpSign
	public ResBody sign(String token){
		
		return ResBody.buildSucResBody();
	}
	
	//用来检测业务站点是否登入
	@RequestMapping(value = "/sso/isLogin", method = RequestMethod.POST)
	@AuthSign
	public ResBody isLogin(String token){
		
		return ResBody.buildSucResBody();
	}
}
