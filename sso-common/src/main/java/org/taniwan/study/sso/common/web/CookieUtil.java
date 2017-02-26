package org.taniwan.study.sso.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taniwan.study.sso.common.mvc.auth.SessionUtil;

import javax.servlet.http.Cookie;

/**
 * Created by LENOVO on 2017/2/25.
 */
public class CookieUtil {

    private static final Logger log = LoggerFactory.getLogger(CookieUtil.class);

    public static void setJeSessionId(String jeSessionId){
        Cookie[] reqCks = SessionUtil.getRequest().getCookies();
        Cookie tc = null;
        if(reqCks != null){
            for(Cookie c: reqCks){
                if("JSESSIONID".equals(c.getName())){
                    tc = c;
                }
                log.info("cookie name: {} value:{} path: {}", c.getName(), c.getValue(), c.getPath());
            }
        }
        Cookie sessionId = null;
        if(tc == null){
            sessionId = new Cookie("JSESSIONID", jeSessionId);
            sessionId.setHttpOnly(true);
            sessionId.setPath("/");
            SessionUtil.getResponse().addCookie(sessionId);
            log.info("set-cookie: JSESSIONID={}", jeSessionId);
        }
        else{
            log.info("update-cookie: old-JSESSIONID={} new-JSESSIONID={}", tc.getValue(), jeSessionId);
            tc.setValue(jeSessionId);
        }
    }

    public static String getJeSessionId(){
        Cookie[] reqCks = SessionUtil.getRequest().getCookies();
        Cookie tc = null;
        if(reqCks != null){
            for(Cookie c: reqCks){
                if("JSESSIONID".equals(c.getName())){
                    tc = c;
                }
                log.info("cookie name: {} value:{} path: {}", c.getName(), c.getValue(), c.getPath());
            }
        }
        if(tc == null){
            return null;
        }
        return tc.getValue();
    }
}
