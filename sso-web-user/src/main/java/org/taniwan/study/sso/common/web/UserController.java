package org.taniwan.study.sso.common.web;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.taniwan.study.sso.common.config.DomainGroupConfig;
import org.taniwan.study.sso.common.exception.BizException;
import org.taniwan.study.sso.common.exception.SysErrorCode;
import org.taniwan.study.sso.common.mvc.auth.AuthSign;
import org.taniwan.study.sso.common.mvc.auth.SessionUtil;
import org.taniwan.study.sso.common.mvc.bean.ResBody;
import org.taniwan.study.sso.common.redis.RedisRepository;
import org.taniwan.study.sso.common.res.LoginRes;
import org.taniwan.study.sso.common.util.UUIDUtil;

@RestController
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private RedisRepository redisRepository;

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ResBody login(int cellphone, String passwd){
		if(StringUtils.isEmpty(cellphone) || StringUtils.isEmpty(passwd)){
			throw new BizException(SysErrorCode.PARAM_ERROR);
		}
		String host = SessionUtil.getRequest().getRemoteHost();
		String uuid = UUIDUtil.getUuid();
		redisRepository.set(uuid, cellphone + ":" + passwd, 10, TimeUnit.SECONDS);
		SessionUtil.setSessionUserId(cellphone);
		Cookie sessionId = new Cookie("JESESSIONID", uuid);
		sessionId.setHttpOnly(true);
		sessionId.setPath("/");
		LoginRes res = new LoginRes();
		res.setSsoToken(uuid);
		res.setSsoUrls(DomainGroupConfig.getDomainGroup(host));
		return ResBody.buildSucResBody(res);
	}
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.POST)
	@AuthSign
	public ResBody logout(){
		redisRepository.set(SessionUtil.getSessionUserId() + "", "");
		return ResBody.buildSucResBody();
	}
	
}
