package org.taniwan.study.sso.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.taniwan.study.sso.common.exception.BizException;
import org.taniwan.study.sso.common.exception.SysErrorCode;
import org.taniwan.study.sso.common.mvc.auth.AuthSign;
import org.taniwan.study.sso.common.mvc.auth.SessionUtil;
import org.taniwan.study.sso.common.mvc.bean.ResBody;
import org.taniwan.study.sso.common.mvc.jsop.JsonpSign;
import org.taniwan.study.sso.common.redis.RedisRepository;

@RestController
@JsonpSign
public class SsoController {

	private static final Logger log = LoggerFactory.getLogger(SsoController.class);

	@Autowired
	private RedisRepository redisRepository;

	//业务站点通过jsonp技术结合sso-web-user授权的token登入
	//对请求做http head refence校验，防止别人窃取token
	@RequestMapping(value = "/sso/login", method = RequestMethod.GET)
	public ResBody login(String token){
		String cellphone = redisRepository.get("ssotoken:" + token);
		if(StringUtils.isEmpty(cellphone)){
			throw new BizException(SysErrorCode.PARAM_ERROR);
		}
//		CookieUtil.setJeSessionId(jeSessionId);
//		String userId = redisRepository.get("jsessionid:" + jeSessionId).split(":")[0];
		SessionUtil.setSessionUserId(Integer.parseInt(cellphone));
		return ResBody.buildSucResBody(cellphone);
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
