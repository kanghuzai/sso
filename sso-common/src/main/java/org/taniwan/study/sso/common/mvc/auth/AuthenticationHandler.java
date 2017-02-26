package org.taniwan.study.sso.common.mvc.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.taniwan.study.sso.common.exception.BizException;
import org.taniwan.study.sso.common.exception.SysErrorCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.HashMap;

/**
 * 权限认证拦截器
 * 
 * @author 张超
 * @date 2015年5月7日 下午3:52:13
 * @since 2.1
 */
public class AuthenticationHandler extends HandlerInterceptorAdapter {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationHandler.class);

	private static final ThreadLocal<HashMap<String, Long>> methodSecMap = new ThreadLocal<HashMap<String, Long>>();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
//			response.addHeader("Access-Control-Allow-Credentials", "true");
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//			response.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, "
//					+ "Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");
//			response.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
			String xid = request.getHeader("x-auth-id");
			if(!StringUtils.isEmpty(xid)){
				request.getSession().setAttribute(SessionUtil.SESSION_USER_ID, Integer.parseInt(xid));
			}
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			String[] methods = handlerMethod.getMethod().getAnnotation(RequestMapping.class).value();
			HashMap<String, Long> hm = new HashMap<>();
			hm.put(methods[0], new Date().getTime());
			methodSecMap.set(hm);
			log.debug("Remote-Host: " + request.getRemoteHost());
			log.debug("User-Agent: " + request.getHeader(HttpHeaders.USER_AGENT));
			boolean isAuth = false;
			//先判断整个controller类是否要认证
			//先从类上找权限注解，类上没有再从方法上找
			Annotation[] annotations = handlerMethod.getBeanType().getAnnotations();
			boolean isAuthTag = false;
			for(Annotation annotation: annotations){
				if(annotation instanceof AuthSign){
					isAuthTag = true;
					if(handlerMethod.getMethod().getAnnotation(NoAuthSign.class) == null){
						isAuth = true;
					}
				}
				else if(annotation instanceof NoAuthSign){
					isAuthTag = true;
					if(handlerMethod.getMethod().getAnnotation(AuthSign.class) != null){
						isAuth = true;
					}
				}
			}
			if(!isAuthTag){
				if(handlerMethod.getMethod().getAnnotation(AuthSign.class) != null){
					isAuth = true;
				}
			}
			SessionUtil.saveSession(request.getSession());
			SessionUtil.saveRequst(request);
			SessionUtil.saveResponse(response);
			if(isAuth){
				if(SessionUtil.getSessionUserId() == null){
					log.info("$$$ sessionId: {} $$$", request.getSession().getId());
					throw new BizException(SysErrorCode.PERMISSION_EXPIRED);
				}
				log.info("$$$ sessionId: {} -> userId: {} $$$", request.getSession().getId(), SessionUtil.getSessionUserId());
			}
			else{
				log.info("$$$ sessionId: {} $$$", request.getSession().getId());
			}
		} catch (ClassCastException e) {
			// 不能强制转换为HandlerMethod类直接通过
//			log.error(e.getMessage(), e);
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		try {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			String[] methods = handlerMethod.getMethod().getAnnotation(RequestMapping.class).value();
			log.info("handler method: {} sec: {} ms", methods[0], new Date().getTime() - methodSecMap.get().get(methods[0]));
		}
		catch (Exception e) {
			if(e instanceof ClassCastException){
				// ingore exection
			}
			else {
				log.error(e.getMessage(), e);
				throw new BizException(SysErrorCode.INSIDE_ERROR);
			}
		}
		if(ex != null){
			if(ex instanceof BizException){
				throw ex;
			}
			else{
				log.error(ex.getMessage(), ex);
				throw new BizException(SysErrorCode.INSIDE_ERROR);
			}
		}
		super.afterCompletion(request, response, handler, ex);
	}

}
