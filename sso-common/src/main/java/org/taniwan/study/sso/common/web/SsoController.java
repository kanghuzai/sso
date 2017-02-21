package org.taniwan.study.sso.common.web;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.taniwan.study.sso.common.exception.BizErrorCode;
import org.taniwan.study.sso.common.exception.BizException;
import org.taniwan.study.sso.common.exception.SysErrorCode;
import org.taniwan.study.sso.common.mvc.auth.AuthSign;
import org.taniwan.study.sso.common.mvc.auth.SessionUtil;
import org.taniwan.study.sso.common.mvc.bean.ResBody;
import org.taniwan.study.sso.common.mvc.jsop.JsonpSign;
import org.taniwan.study.sso.common.redis.RedisRepository;
import org.taniwan.study.sso.common.util.UUIDUtil;

@RestController
@JsonpSign
public class SsoController {
	
	@Autowired
	private RedisRepository redisRepository;

	//业务站点通过jsonp技术结合sso-web-user授权的token登入
	@RequestMapping(value = "/sso/login", method = RequestMethod.GET)
	public ResBody login(String token){
		String jessionid = redisRepository.get("ssotoken:" + token);
		Cookie sessionId = new Cookie("JSESSIONID", jessionid);
		sessionId.setHttpOnly(true);
		sessionId.setPath("/");
		SessionUtil.getResponse().addCookie(sessionId);
		return ResBody.buildSucResBody();
	}
	
	//用来检测业务站点是否登入
	@RequestMapping(value = "/sso/isLogin", method = RequestMethod.GET)
	@AuthSign
	public ResBody isLogin(){
		if(SessionUtil.getSessionUserId() == null){
			throw new BizException(SysErrorCode.PERMISSION_EXPIRED);
		}
		return ResBody.buildSucResBody();
	}
}
