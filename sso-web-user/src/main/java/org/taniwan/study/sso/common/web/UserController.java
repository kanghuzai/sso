package org.taniwan.study.sso.common.web;

import java.util.List;
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

	@RequestMapping(value = "/user/login", method = {RequestMethod.POST, RequestMethod.GET})
	public ResBody login(int cellphone, String passWd){
		if(StringUtils.isEmpty(cellphone) || StringUtils.isEmpty(passWd)){
			throw new BizException(SysErrorCode.PARAM_ERROR);
		}
		String host = SessionUtil.getRequest().getRemoteHost();
		int port = SessionUtil.getRequest().getLocalPort();
		host = port == 80 ? host : host + ":" + port;
		String ssoToken = UUIDUtil.getUuid();
		String jessionid = UUIDUtil.getUuid();
		// ssoToken 有效期10秒
		redisRepository.set("ssotoken:" + ssoToken, jessionid, 10, TimeUnit.SECONDS);
		// session 有效期1分钟
		redisRepository.set("jsessionid:" + jessionid, cellphone + ":" + passWd, 1, TimeUnit.MINUTES);
		Cookie sessionId = new Cookie("JSESSIONID", jessionid);
		sessionId.setHttpOnly(true);
		sessionId.setPath("/");
		SessionUtil.getResponse().addCookie(sessionId);
		SessionUtil.setSessionUserId(cellphone);
		LoginRes res = new LoginRes();
		res.setSsoToken(ssoToken);
		List<String> domains = DomainGroupConfig.getDomainGroup(host);
		domains.remove(host);
		res.setSsoUrls(domains);
		return ResBody.buildSucResBody(res);
	}
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.POST)
	@AuthSign
	public ResBody logout(){
		redisRepository.set(SessionUtil.getSessionUserId() + "", "");
		return ResBody.buildSucResBody();
	}
	
}
