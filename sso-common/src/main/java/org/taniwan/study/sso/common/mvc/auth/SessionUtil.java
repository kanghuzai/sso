package org.taniwan.study.sso.common.mvc.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUtil {

	private static final Logger log = LoggerFactory.getLogger(SessionUtil.class);
	private static final ThreadLocal<HttpSession> HTTPSESSION_LOCAL = new ThreadLocal<>();
	private static final ThreadLocal<HttpServletRequest> REQUEST_LOCAL = new ThreadLocal<>();
	private static final ThreadLocal<HttpServletResponse> RESPONSE_LOCAL = new ThreadLocal<>();
	public static final String SESSION_USER_ID = "SESSION_USER_ID";

	public static void saveSession(HttpSession httpSession) {
		HTTPSESSION_LOCAL.set(httpSession);
	}
	
	public static HttpSession getSession(){
		return HTTPSESSION_LOCAL.get();
	}
	
	public static void saveRequst(HttpServletRequest request){
		REQUEST_LOCAL.set(request);
	}
	
	public static HttpServletRequest getRequest(){
		return REQUEST_LOCAL.get();
	}
	
	public static void saveResponse(HttpServletResponse response){
		RESPONSE_LOCAL.set(response);
	}
	
	public static HttpServletResponse getResponse(){
		return RESPONSE_LOCAL.get();
	}

	/**
	 * 从session中获取userId
	 * 
	 * @author 张超
	 * @date 2016年8月26日-下午2:15:45
	 * @return
	 */
	public static Integer getSessionUserId() {
		Integer userId = (Integer) HTTPSESSION_LOCAL.get().getAttribute(SESSION_USER_ID);
		return userId;
	}

	/**
	 * 清除当前登入用户的session信息
	 * @author 张超
	 * @date 2016年8月26日-下午2:15:07
	 */
	public static void cleanSessionInfo() {
		HTTPSESSION_LOCAL.get().setAttribute(SESSION_USER_ID, null);
	}

	/**
	 * 保存userid到session中
	 * 
	 * @author 张超
	 * @date 2016年8月26日-下午2:15:07
	 * @param userId
	 */
	public static void setSessionUserId(int userId) {
		HTTPSESSION_LOCAL.get().setAttribute(SESSION_USER_ID, userId);
	}


}
